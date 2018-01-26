package com.heizhe.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.tools.CommonTools;
import com.heizhe.tools.UrlTools;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;


public class ConstructTemp {

	
	
	public static void main(String[] args) {
		
		
		//******************************手动注入WXService****************
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId("wx16f32a479bcba04c"); // 设置微信公众号的appid
		config.setSecret("8caecfa027bf79a92a132927f7bb4bfe"); // 设置微信公众号的app corpSecret
		config.setToken("Mg4Kl5Pl5MmpkGltp55llHPhn4pGZJ32"); // 设置微信公众号的token
		config.setAesKey("Oqn7zscCCOpPP8pp7qnO7nL7PoM7MxSoxLPl3Pnpcpn"); // 设置微信公众号的EncodingAESKey

		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);
		
		//******************************手动注入WXService****************
		
		
		//******************************上传文中图片****************//temp 注释
//		File ttFile  = new File("F:/zhihu/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg");
//		try {
//			WxMediaImgUploadResult tRes = wxService.getMaterialService().mediaImgUpload(ttFile);
//		} catch (WxErrorException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		//******************************上传文中图片****************
		      
		      
		      
		
		//拼装content

		String questrionUrl = "https://www.zhihu.com/question/42031377/answer/299171507";
		
//		Document zhihuSource = CommonTools.getDocByUrl(questrionUrl);
//		Elements zhihuConEles  = zhihuSource.getElementsByClass("RichText CopyrightRichText-richText");
		
		
//		Element zhihuConEle = zhihuConEles.first();
//		Elements ss = zhihuConEle.children(); 这个是生产的数据了
	
		String tempHtml  = "<p>不知道大家还记不记得，曾经有个90女生，因为抑郁症自杀。去世后，她的微博通过定时时光机发了一条：</p>\r\n" + 
				"<p><b>“我有抑郁症，所以就去死一死，没什么重要的原因，大家不必在意我的离开。拜拜啦。”</b></p>\r\n" + 
				"<figure>\r\n" + 
				" <noscript>\r\n" + 
				"  <img data-rawheight=\"337\" src=\"https://pic2.zhimg.com/50/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg\" data-size=\"normal\" data-rawwidth=\"1299\" class=\"origin_image zh-lightbox-thumb\" width=\"1299\" data-original=\"https://pic2.zhimg.com/v2-1a77e6e876ef5f24d421c9e43190d3a6_r.jpg\">\r\n" + 
				" </noscript>\r\n" + 
				" <img data-rawheight=\"337\" src=\"data:image/svg+xml;utf8,<svg%20xmlns='http://www.w3.org/2000/svg'%20width='1299'%20height='337'></svg>\" data-size=\"normal\" data-rawwidth=\"1299\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1299\" data-original=\"https://pic2.zhimg.com/v2-1a77e6e876ef5f24d421c9e43190d3a6_r.jpg\" data-actualsrc=\"https://pic2.zhimg.com/50/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg\">\r\n" + 
				"</figure>\r\n" + 
				"<p>她叫走饭。</p>\r\n" + 
				"<p>这个在外人看来只是“少言寡语、性格内向”的女生，她在学校有个绰号叫“冷面笑神”，但她一直孤独的活着自己的世界，孤独的写着自己的话，直到死后才引起大家的关注。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>她说——</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>躲了一辈子的雨，雨会不会很伤心。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>最怕事干到一半就没兴趣了，比如发这条微博。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>最近自言自语到魔障了，刚才我一边把右手拿着的吃完早餐的塑料袋递到左手上，一边嘴里冒了一句“给你”。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>饼干在朝我微笑，香蕉在朝我微笑，酸奶在朝我微笑，松枣饼子在朝我微笑，我不回家它们就感受不到活着的意义，我欣慰我再一次拯救苍生，微笑。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>心是被什么拽住的，为什么感受不到它的重量。如果我移植了一颗心，我会感受到它比之前的更轻还是更重吗。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>意义这种东西，有意义吗？</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>你用我用来安慰你的句子来安慰我是没用的，为什么你不能明白这一点，我好心又天真的姑娘。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>每天害怕你讨厌我，已经占据我一大半的生活了，哪里还有工夫指望你喜欢我呀</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>我的右边脑壳里面，有一颗圆形的纽扣。我说真的，有纽扣才能造成这种痛感，它在用身上的洞洞筛我的脑浆。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>我是这么想的，给自己一年的期限，把想吃的东西都吃个遍，就可以自杀了。每次都跟自己说，因为还有什么什么没吃到而拖着不去死，不觉得浪费时间吗？</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>跳楼死的人比跳崖死的人多，是因为爬到山顶真他妈没劲跳了啊，就想坐着</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>觉得孤单的时候，可以在床上躺一个小时，然后爬下来去撒尿。回来钻进被窝，跟自己说，好温暖，好温暖，谁帮我暖的被子。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>你们吵架，我装死。掀我被子，我装死。饿了，我装死。蚊子咬我，我装死。然后，我真的死了。哦耶</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>谨记：看书时不能把手臂放在书上，要不然我会开始数自己的毛孔。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>人生本身就是一个自杀的过程。</p>\r\n" + 
				"<hr>\r\n" + 
				"<p>这就是抑郁症，这些句子看了让人想哭。</p>\r\n" + 
				"<p>但我们永远无法体会那些得了抑郁症的人，写下这些句子时的心情。</p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p>一如张国荣自杀时说的那句话：</p>\r\n" + 
				"<p><b>我一生无做坏事，为何会这样？？？</b></p>";
		Document tempDoc = Jsoup.parse(tempHtml);//先用这个做测试数据，调试阶段不频繁访问知乎了
		
		StringBuffer wxContent = new StringBuffer();
		
		Elements tempEles = tempDoc.body().children();
		for(Element ele : tempEles){
			/*
			 * 如果是图片就要下载下来
			 */
			if("p".equals(ele.tagName())){
				String pTag = ele.toString();
//				System.out.println(ele.toString());
				wxContent.append(pTag);
			}
			
			/*
			 * 
			 */
			if("figure".equals(ele.tagName())){
				Element noscript = ele.getElementsByTag("noscript").first();
				//https://pic2.zhimg.com/50/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg
//				String imgUrl =  noscript.child(0).attr("src");
//				String imgUrl = ele.child(0).child(0).attr("src"); 这里俩个child(0)是一样的道理
				String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"; //百度logo
				String imageName = UrlTools.getTheImageName(imgUrl);
				System.out.println(imgUrl);
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
					System.out.println(wxUrl);
				} catch (WxErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!("".equals(wxUrl))){
					wxContent.append(ConsTantWx.COMMON_IMAGE_PREFIX+wxUrl+ConsTantWx.COMMON_IMAGE_Suffix);
				}
			}
		}
		String wxContentRes = wxContent.toString();
		
		//拼装成功后调用保存图文素材接口，然后再调用发布文章接口
		//Y8Bjr0YiUQJigb3UR2WU-fRwkGZV43Z_2QWnmty47Qk  一个图片的mediaId和url
		//http://mmbiz.qpic.cn/mmbiz_jpg/uDbxxbf4KbS0tibeDSFNo1ibN7w3qgegbce3uBMWx37gTuicUUkFV3QUWiaXZky4I4fblVFLia9UuQskNduayQCM6Dw/0?wx_fmt=jpeg 
		WxMpMaterialNews wxMpMaterialNewsSingle = new WxMpMaterialNews();
	    WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
	    article.setAuthor("author");
	    //TODO
	    article.setThumbMediaId("Y8Bjr0YiUQJigb3UR2WU-fRwkGZV43Z_2QWnmty47Qk");//这里有个缩率图TO
	    article.setTitle("single title");
	    article.setContent(wxContentRes);
	    article.setContentSourceUrl("content url");
	    article.setShowCoverPic(true);
	    article.setDigest("ddddddddddddddddddddddddddddddddddddddddd");
	    wxMpMaterialNewsSingle.addArticle(article);
		try {
			WxMpMaterialUploadResult suRes = wxService.getMaterialService().materialNewsUpload(wxMpMaterialNewsSingle);
			System.out.println(suRes.getErrMsg());
			System.out.println(suRes.getErrCode());
			System.out.println(suRes.getMediaId());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
