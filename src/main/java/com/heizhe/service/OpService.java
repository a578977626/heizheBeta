package com.heizhe.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.tools.CommonTools;
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

@Service
public class  OpService {

	@Autowired
	private WxMpService wxService;
	
	
	public void uploadMatZhiHu(String url){
		
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
		
		if("figure".equals(ele.tagName())){
			Element noscript = ele.getElementsByTag("noscript").first();
			//https://pic2.zhimg.com/50/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg
			String imgUrl =  noscript.child(0).attr("src");
//			String imgUrl = ele.child(0).child(0).attr("src"); 这里俩个child(0)是一样的道理
//			String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"; //百度logo
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
	
	//Y8Bjr0YiUQJigb3UR2WU-fRwkGZV43Z_2QWnmty47Qk  一个图片的mediaId和url
	//http://mmbiz.qpic.cn/mmbiz_jpg/uDbxxbf4KbS0tibeDSFNo1ibN7w3qgegbce3uBMWx37gTuicUUkFV3QUWiaXZky4I4fblVFLia9UuQskNduayQCM6Dw/0?wx_fmt=jpeg 
	//拼装成功后调用保存图文素材接口，然后再调用发布文章接口
	String wxContentRes = wxContent.toString();
	WxMpMaterialNews wxMpMaterialNewsMultiple = new WxMpMaterialNews();
	WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
	article.setAuthor("author");
	//TODO 缩率图
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
	}}
	
	
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
	
}
