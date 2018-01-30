package com.heizhe.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.entity.DailyHotBasic;
import com.heizhe.entity.MateriaArticle;
import com.heizhe.rep.DailyHotRespository;
import com.heizhe.rep.MaterialArticleRespository;
import com.heizhe.tools.CommonTools;
import com.heizhe.tools.LogUtil;
import com.heizhe.tools.UrlTools;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

/**
 * 操作类
 * @author chenxb2
 *
 */
@Service
public class  OpService {

	@Autowired
	private WxMpService wxService;
	
	@Autowired
	private DailyHotRespository dailyHotRespository;
	
	@Autowired
	MaterialArticleRespository matArtRespository;
	
	/**
	 * 根据URL生成素材
	 * @param url 回答的URL
	 */
	public void uploadMatZhiHu(String url){
	
	/********** 获取到zhihu答案拼装后的WxContent*********/
	Elements ss = CommonTools.getEleByAnswerUrl(url);
	String wxContentRes = combineWxMat(ss);
	
	
	/********** 获取作者名字*********/
//	String author = 
	/********** 获取问题的标题*********/
	
	
	
	//拼装成功后调用保存图文素材接口
	
	WxMpMaterialNews wxMpMaterialNewsMultiple = new WxMpMaterialNews();
	WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
	article.setAuthor("author");
	//TODO 缩率图
	//Y8Bjr0YiUQJigb3UR2WU-fRwkGZV43Z_2QWnmty47Qk  一个图片的mediaId和url
	//http://mmbiz.qpic.cn/mmbiz_jpg/uDbxxbf4KbS0tibeDSFNo1ibN7w3qgegbce3uBMWx37gTuicUUkFV3QUWiaXZky4I4fblVFLia9UuQskNduayQCM6Dw/0?wx_fmt=jpeg 
	article.setThumbMediaId("Y8Bjr0YiUQJigb3UR2WU-fRwkGZV43Z_2QWnmty47Qk");//这里有个缩率图TODO
	article.setTitle("测试标题");
	article.setContent(wxContentRes);
//	article.setContentSourceUrl("www");
	article.setShowCoverPic(true);
//	article.setDigest("文中大概");
	article.setShowCoverPic(false);
	wxMpMaterialNewsMultiple.addArticle(article);
	try {
		WxMpMaterialUploadResult suRes = wxService.getMaterialService().materialNewsUpload(wxMpMaterialNewsMultiple);
		System.out.println(suRes.getErrMsg());
		System.out.println(suRes.getErrCode());
		System.out.println(suRes.getMediaId());
	} catch (WxErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public String uploadtest(){
		Elements ss = CommonTools.getEleByAnswerUrl("https://www.zhihu.com/question/28326061");
		return "00";
	}
	
	/**
	 * 批量操作。
	 * 生成3条文章的预览，并保存mediaId
	 */
	public String uploadMatToPreview(){
		List<DailyHotBasic> list = dailyHotRespository.listBySQL("SELECT\r\n" + 
				"	*\r\n" + 
				"FROM\r\n" + 
				"	daily_hot_basic\r\n" + 
				"WHERE\r\n" + 
				"	answer_type <> '禁止转载'\r\n" + 
				"AND to_days(created_date) = to_days(now())\r\n" + 
				"ORDER BY\r\n" + 
				"	liked_count DESC\r\n" + 
				"LIMIT 3",DailyHotBasic.class);
//		LogUtil.info(list.get(0).getId().toString());
//		return list.get(0).getAnswerUrl();
		String mediaId = uploadMatZhiHuV2(list);
		return mediaId;
		
	}
	
	/**
	 * 批量上传素材到微信
	 * @param url
	 */
	public String uploadMatZhiHuV2(List<DailyHotBasic> list){
		WxMpMaterialNews wxMpMaterialNewsMultiple = new WxMpMaterialNews();

		for(int i = 0; i < list.size(); i++){
			LogUtil.info("查询到的raw资源有"+String.valueOf(list.size()));
			DailyHotBasic basic = list.get(i);
			/********** 获取到zhihu答案拼装后的WxContent*********/
			Elements ss = CommonTools.getEleByAnswerUrl(basic.getAnswerUrl());
			if(ss.size()<1){
				LogUtil.info("有一条不合格,访问要登录");
				continue;
			}
			String wxContentRes = combineWxMat(ss);
			saveMateriaArticle(wxContentRes,basic);
			/************暂时不支持视频素材***********************/
			if(wxContentRes.equals("vedio")){
				//TODO
				LogUtil.info("有一条不合格,有视频内容");
				continue;
			}
			/************暂时不支持视频素材***********************/
			
			/********** 获取作者名字*********/
			String author = basic.getAuthor();
			/********** 获取问题的标题*********/
			String title  = basic.getQuestion();
			/********** 获取问题的summary*********/
//			String summary = basic.getSummary();
			
			/********** 最后的content拼装底部引用部分*********/
			wxContentRes = wxContentRes + ConsTantWx.QOUTE_TAG_P1 + author + ConsTantWx.QUOTE_TAG_P2 + basic.getAnswerUrl() +ConsTantWx.QUOTE_TAG_P3;
			
			WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
			article.setAuthor(author);
			//第一篇要用16:9封面图
			if(i>0){
				article.setThumbMediaId("Y8Bjr0YiUQJigb3UR2WU-a9A5agn7F6OB2xrAFktuek");//1:1图
			}else{
				article.setThumbMediaId("Y8Bjr0YiUQJigb3UR2WU-cMgt0WWlDc9irMsUSxyFs0");//16:9图
			}
			article.setTitle(title);
			article.setContent(wxContentRes);
//			article.setContentSourceUrl("www");
			article.setShowCoverPic(true);
//			article.setDigest(summary);
			article.setShowCoverPic(false);
			wxMpMaterialNewsMultiple.addArticle(article);
		}
		
		
		//拼装成功后调用保存图文素材接口
		try {
			WxMpMaterialUploadResult suRes = wxService.getMaterialService().materialNewsUpload(wxMpMaterialNewsMultiple);
			System.out.println(suRes.getErrMsg());
			System.out.println(suRes.getErrCode());
			System.out.println(suRes.getMediaId());
			if(!StringUtil.isBlank(suRes.getMediaId())){
				updateMatrialArticleStatus(list,suRes.getMediaId());
				return suRes.getMediaId();
			}
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//01-28 mediaId Y8Bjr0YiUQJigb3UR2WU-WEmUQC5kgKLvYcb7nan0Pk
		//01-29 mediaId Y8Bjr0YiUQJigb3UR2WU-duf3alY1hWIMvul3sWkACY
		return "00";
		}
	

	/**
	 * 测试抓取数据
	 * @param url
	 */
	public void uploadMatZhiHu001(String url){
		Document zhihuSource = CommonTools.getDocByUrl(url);
		Elements zhihuConEles  = zhihuSource.getElementsByClass("RichText CopyrightRichText-richText");
		
		Element zhihuConEle = zhihuConEles.first();
		/**********     这个是生产的数据了         *********/
		Elements ss = zhihuConEle.children(); 
		
		
		StringBuffer wxContent = new StringBuffer();
		
		for(Element ele : ss){
			/**********     如果是图片就要下载下来并且拼装         *********/
			if("p".equals(ele.tagName())){
				String pTag = ele.toString();
				System.out.println("段落"+ele.toString());
				wxContent.append(pTag);
			}
		}
		
	}
	
	/**
	 * 给自己发送信息
	 * @param mes 信息内容
	 */
	public void sendAMessage(String mes){
		//cxb's openId
		String openid = "o14cB1mSBkyq4PCk3jLXnrnSui2g";
		WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(openid).content(mes).build();
		try {
			wxService.getKefuService().sendKefuMessage(message);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
	
	/**
	 * 发布要推送的文章
	 * @param mediaId
	 */
	public void sendMessageNews(String mediaId){
	
	WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
    massMessage.setMsgType(WxConsts.MASS_MSG_NEWS);
    massMessage.setSendIgnoreReprint(true);//文章被判定为转载时，继续进行群发操作
    massMessage.setMediaId(mediaId);
    
    //如果不设置则就意味着发给所有用户
	//massMessage.setTagId(wxService.getUserTagService().tagGet().get(0).getId());
	
    try {
		WxMpMassSendResult massResult = wxService.getMassMessageService()
		  .massGroupMessageSend(massMessage);
	} catch (WxErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	
	
	/**
	 * 拼装微信Content
	 * @param ss
	 * @return
	 */
	private String combineWxMat(Elements ss){
		StringBuffer wxContent = new StringBuffer();
		
		for(Element ele : ss){
			/************暂时不支持视频素材***********************/
			if("div".equals(ele.tagName())){
				return "veido";
			}
			/************暂时不支持视频素材***********************/
			
			/**********     如果是图片就要下载下来并且拼装         *********/
			if("p".equals(ele.tagName())){
				ele.attr("style", ConsTantWx.P_STYLE_COMMON);
				String pTag = ele.toString();
//				System.out.println("段落"+ele.toString());
				wxContent.append(pTag);
			}
			
			if("figure".equals(ele.tagName())){
				Element noscript = ele.getElementsByTag("noscript").first();
				//https://pic2.zhimg.com/50/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg
				String imgUrl =  noscript.child(0).attr("src");
//				String imgUrl = ele.child(0).child(0).attr("src"); 这里俩个child(0)是一样的道理
//				String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"; //百度logo
				String imageName = UrlTools.getTheImageName(imgUrl);
				System.out.println("download Image:"+imgUrl);
				//下载图片到本地
				File file = new File("f:/zhihu/"+imageName);
				if (!file.exists()) {
					try {
						HttpClientUtil.down(HttpConfig.custom().url(imgUrl).out(new FileOutputStream(file)));
					} catch (FileNotFoundException | HttpProcessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//上传到微信并获取URL
				String wxUrl = "";
				try {
					WxMediaImgUploadResult imageRes = wxService.getMaterialService().mediaImgUpload(file);
					wxUrl = imageRes.getUrl();
					System.out.println("upload to WeChat"+wxUrl);
				} catch (WxErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!("".equals(wxUrl))){
					wxContent.append(ConsTantWx.COMMON_IMAGE_PREFIX+wxUrl+ConsTantWx.COMMON_IMAGE_Suffix);
				}
			}
		}
		return wxContent.toString();
	}
	
	
	/**
	 * 保存上传图文内容（此时未正式上传成功）
	 * @param wxContent 上传的微信图文content内容
	 * @param basic 
	 */
	private void saveMateriaArticle(String wxContent, DailyHotBasic basic){
		MateriaArticle mA = new MateriaArticle();
		mA.setArticleContent(wxContent);
		mA.setBasicId(basic.getId());
		mA.setBasicUrl(basic.getAnswerUrl());
		mA.setCreated(new Date());
		mA.setUploadStatus("0");
		mA.setPlatform(ConsTantWx.WORD_ZHIHU);
		
		matArtRespository.save(mA);
		LogUtil.info("保存MatArt成功");
	}
	
	/**
	 * 正确上传后更新matArt表状态与meidiaId
	 * @param list
	 * @param mediaId
	 */
	private void updateMatrialArticleStatus(List<DailyHotBasic> list, String mediaId) {
		for(DailyHotBasic basic : list){
			 matArtRespository.updateStatusAndMediaByUrl("1", mediaId, basic.getId());
			 LogUtil.info("上传并Update成功");
		}
	}
	
}
