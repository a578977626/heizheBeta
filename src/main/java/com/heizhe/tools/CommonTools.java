package com.heizhe.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;


public class CommonTools {
	
	
	
	/**
	 * 用Jsoup获取网页Doc
	 * @param url
	 * @return
	 */
	public static Document getDocByUrl (String url){
	    Document doc = null;
		try {doc = Jsoup.connect(url).get();} catch (IOException e) {e.printStackTrace();} 
		return doc;
	}
	
	
	/**
	 * 根据答案Url获取答案的Html标签
	 * @param url
	 * @return
	 */
	public static Elements getEleByAnswerUrl (String url){
		Document zhihuSource = getDocByUrl(url);
		Elements zhihuConEles  = zhihuSource.getElementsByClass("RichText CopyrightRichText-richText");
		
		Element zhihuConEle = zhihuConEles.first();
		Elements ss = zhihuConEle.children(); 
		return ss;
	}
	
	public static Document getQuestionData(String questionUrl) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * 调用日度热门接口获取热门答案基本信息
	 */
	public static String getHotAnswerBasic(String params) throws HttpProcessException{

		String url = ConsTantWx.DAILY_HOT_ANSTER_URL;
		
		Header[] headers 	= HttpHeader.custom()//此请求头是知乎问题页的接口的请求头
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER")
				.accept("application/json, text/plain, */*")
				.acceptEncoding("*")//不用*会乱码
				.acceptLanguage("zh-CN,zh;q=0.8")
				.connection("keep-alive")
				.cookie("_xsrf=0df2d23wwwww9-0c3c-4871-9c80-23e545515c55")
				.host("www.zhihu.com")
				.referer("https://www.zhihu.com/explore")
				.other("X-Requested-With", "XMLHttpRequest")
				.build();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("params", params);
		
		//插件式配置请求参数（网址、请求参数、编码、client）
		HttpConfig config = HttpConfig.custom()
				.headers(headers)	//设置headers，不需要时则无需设置
				.url(url)					//设置请求的url
				.map(map)			//设置请求参数，没有则无需设置
				.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
//				.client(client)														//如果只是简单使用，无需设置，会自动获取默认的一个client对象
				//.inenc("utf-8") 													//设置请求编码，如果请求返回一直，不需要再单独设置
				//.inenc("utf-8")													//设置返回编码，如果请求返回一直，不需要再单独设置
				//.json("json字符串")												//json方式请求的话，就不用设置map方法，当然二者可以共用。
				//.context(HttpCookies.custom().getContext()) 		//设置cookie，用于完成携带cookie的操作
				//.out(new FileOutputStream("保存地址"))			 	//下载的话，设置这个方法,否则不要设置
				//.files(new String[]{"d:/1.txt","d:/2.txt"})					//上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
				;
		
		
		String result1 = HttpClientUtil.get(config);		//get请求
		System.out.println(params);
		return result1;
	}
	
	/**
	 * String转成Doc
	 * @param html
	 * @return
	 */
	public static Document StringToDoc(String html){
		return Jsoup.parse(html);
	}
	
}
