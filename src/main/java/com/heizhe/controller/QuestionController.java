package com.heizhe.controller;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.SSLs.SSLProtocolVersion;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.tools.CommonTools;

@Controller
@RequestMapping("/topHost")
public class QuestionController {
	
	@ResponseBody
	@RequestMapping(value = "/getTopHosts", method = RequestMethod.GET)
	public String readersBooks() {
			String dayHotTop1To5  = "https://www.zhihu.com/node/ExploreAnswerListV2?params={\"offset\":0,\"type\":\"day\"}";
			String dayHotTop6To10 = "https://www.zhihu.com/node/ExploreAnswerListV2?params={\"offset\":5,\"type\":\"day\"}";
	        Document source = CommonTools.getDocByUrl(dayHotTop1To5);
	        Elements quesLinks = source.select("link[href]");
	        for(Element ele :quesLinks){
	        	//https://www.zhihu.com/question/61456355/answer/290331626 本题推荐的热门
	        	String hotHerf = ele.attr("abs:href");
	        	System.out.println(hotHerf);
	        	
	        	String hotCreated = hotHerf.substring(0, hotHerf.lastIndexOf("answer"))+"answers/created";
	        	
	        	System.out.println(hotCreated);
	        	
	        	
	        }
	        
		return "return LIVE!! IS BEAtiful!!";
		/**
		 * https://www.zhihu.com/question/61456355/answers/created 本题目最新
		 * https://www.zhihu.com/question/61456355/answer/290331626 本题推荐的热门
		 * 
		 */
	}

	public static void waitInfo()throws HttpProcessException, FileNotFoundException {
//	public static void main(String[] args)throws HttpProcessException, FileNotFoundException {
		String  url= "https://www.zhihu.com/api/v4/questions/265247240/answers";
		//最简单的使用：
//		String yure = "https://www.zhihu.com/question/265247240/answer/291331359";
//		String html = HttpClientUtil.get(HttpConfig.custom().url(yure).headers(headers1).encoding("utf-8"));
//		System.out.println(html);
		
		//---------------------------------
		//			【详细说明】
		//--------------------------------
		
		//插件式配置Header（各种header信息、自定义header）
		Header[] headers 	= HttpHeader.custom()//此请求头是知乎问题页的接口的请求头
											.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER")
											.accept("application/json, text/plain, */*")
//											.acceptEncoding("gzip, deflate, sdch, br")
											.acceptEncoding("*")//如果用上面几个词http工具会返回乱码
											.acceptLanguage("zh-CN,zh;q=0.8")
											.authorization("oauth c3cef7c66a1843f8b3a9e6a1e3160e20")//point
											.connection("keep-alive")
											.cookie("_xsrf=0df2d239-0c3c-4871-9c80-23e545515c55")
											.host("www.zhihu.com")
											.referer("https://www.zhihu.com/question/265247240/answer/291331359")//这里必须是个变量
											.other("x-uuid", "ABDC9JaIZAuPThAdFhwCKf0Iu-cdFqfyOYY=")
											.build();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("include", "data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,upvoted_followees;data[*].mark_infos[*].url;data[*].author.follower_count,badge[?(type=best_answerer)].topics");
		map.put("offset", "0");
		map.put("limit", "2");
		map.put("sort_by", "default");//or created
		
		//插件式配置请求参数（网址、请求参数、编码、client）
		HttpConfig config = HttpConfig.custom()
											.headers(headers)	//设置headers，不需要时则无需设置
											.url(url)					//设置请求的url
											.map(map)			//设置请求参数，没有则无需设置
											.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
											//.client(client)														//如果只是简单使用，无需设置，会自动获取默认的一个client对象
											//.inenc("utf-8") 													//设置请求编码，如果请求返回一直，不需要再单独设置
											//.inenc("utf-8")													//设置返回编码，如果请求返回一直，不需要再单独设置
											//.json("json字符串")												//json方式请求的话，就不用设置map方法，当然二者可以共用。
											//.context(HttpCookies.custom().getContext()) 		//设置cookie，用于完成携带cookie的操作
											//.out(new FileOutputStream("保存地址"))			 	//下载的话，设置这个方法,否则不要设置
											//.files(new String[]{"d:/1.txt","d:/2.txt"})					//上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
											;
		
		
		//使用方式：
		String result1 = HttpClientUtil.get(config);		//get请求
		System.out.println(result1);
//		String result2 = HttpClientUtil.post(config);	//post请求
//		System.out.println(result2);
		
		//HttpClientUtil.down(config);							//下载，需要调用config.out(fileOutputStream对象)
		//HttpClientUtil.upload(config);						//上传，需要调用config.files(文件路径数组)
		
		//如果指向看是否访问正常
		//String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
		//int statusCode = HttpClientUtil.status(config);//返回状态码
											
	}
	
	
	//拼装content
	public void conStructContent(){
		String questrionUrl = "https://www.zhihu.com/question/42031377/answer/299171507";
		
		Document zhihuSource = CommonTools.getDocByUrl(questrionUrl);
		Elements zhihuContent  = zhihuSource.getElementsByClass("RichText CopyrightRichText-richText");
		
		System.out.print(zhihuContent.toString());
		
		
		
	
		
	}
	
	
	
	
	
	
	public void htppClinetTest() throws HttpProcessException, FileNotFoundException{
		String url = "https://www.zhihu.com/question/265247240/answer/291331359";
		
		//最简单的使用：
//		String html = HttpClientUtil.get(HttpConfig.custom().url(url));
//		System.out.println(html);
		
		//---------------------------------
		//			【详细说明】
		//--------------------------------
		
		//插件式配置Header（各种header信息、自定义header）
		Header[] headers 	= HttpHeader.custom()//此请求头是知乎问题页的接口的请求头
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER")
				.accept("application/json, text/plain, */*")
				.acceptEncoding("gzip, deflate, sdch, br")
				.acceptLanguage("zh-CN,zh;q=0.8")
				.authorization("oauth c3cef7c66a1843f8b3a9e6a1e3160e20")//point
				.connection("keep-alive")
				.cookie("_xsrf=0df2d239-0c3c-4871-9c80-23e545515c55")
				.host("www.zhihu.com")
				.referer("https://www.zhihu.com/question/265247240/answer/291331359")//这里必须是个变量
				.other("x-uuid", "ABDC9JaIZAuPThAdFhwCKf0Iu-cdFqfyOYY=")
				.build();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("include", "data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,upvoted_followees;data[*].mark_infos[*].url;data[*].author.follower_count,badge[?(type=best_answerer)].topics");
		map.put("offset", "0");
		map.put("limit", "2");
		map.put("sort_by", "default");//or created
		
		//插件式配置请求参数（网址、请求参数、编码、client）
		HttpConfig config = HttpConfig.custom()
				.headers(headers)	//设置headers，不需要时则无需设置
				.url(url)					//设置请求的url
				.map(map)			//设置请求参数，没有则无需设置
				.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
				//.client(client)														//如果只是简单使用，无需设置，会自动获取默认的一个client对象
				//.inenc("utf-8") 													//设置请求编码，如果请求返回一直，不需要再单独设置
				//.inenc("utf-8")													//设置返回编码，如果请求返回一直，不需要再单独设置
				//.json("json字符串")												//json方式请求的话，就不用设置map方法，当然二者可以共用。
				//.context(HttpCookies.custom().getContext()) 		//设置cookie，用于完成携带cookie的操作
				//.out(new FileOutputStream("保存地址"))			 	//下载的话，设置这个方法,否则不要设置
				//.files(new String[]{"d:/1.txt","d:/2.txt"})					//上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
				;
		
		
		//使用方式：
		String result1 = HttpClientUtil.get(config);		//get请求
//		String result2 = HttpClientUtil.post(config);	//post请求
		System.out.println(result1);
//		System.out.println(result2);
		
		//HttpClientUtil.down(config);							//下载，需要调用config.out(fileOutputStream对象)
		//HttpClientUtil.upload(config);						//上传，需要调用config.files(文件路径数组)
		
		//如果指向看是否访问正常
		//String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
		//int statusCode = HttpClientUtil.status(config);//返回状态码
		
	}
	
	
	
	
	
	
	
}
