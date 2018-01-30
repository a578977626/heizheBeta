package com.heizhe.constant;

public class ConsTantWx {

	/**
	 * 换行标签
	 */
	public static String BR_TAG = "<p><br></p>";
	
	/**
	 * 普通段落的样式，比原生的间隔要宽点，好看些。
	 */
	public static String P_STYLE_COMMON ="font-size: 15.5px;letter-spacing: 0.3px;text-align: justify;line-height: 1.7em;color: rgb(40, 40, 40);margin-top: 20px !important;margin-bottom: 20px !important;";

	/**
	 * 最普通图片处理（写死版）	prefix	
	 */
	public static String COMMON_IMAGE_PREFIX = "<p><img data-s=\"300,640\"\r\n" + 
			"data-type=\"jpeg\"\r\n" + 
			"data-copyright=\"0\" \r\n" + 
			"style=\"width: auto !important; height: auto !important; visibility: visible !important;\" \r\n" + 
			"class=\"\" \r\n" + 
			"data-ratio=\"1\" \r\n" + 
			"data-w=\"1280\" \r\n" + 
			"src=\"";
	
	/**
	 * 最普通图片处理（写死版）	Suffix	
	 */
	public static String COMMON_IMAGE_Suffix = "\" data-fail=\"0\"></p>";
	
	
	/**
	 * 日度热门回答URL
	 */
	public static String DAILY_HOT_ANSTER_URL = "https://www.zhihu.com/node/ExploreAnswerListV2";
	
	/**
	 * 日度热门前5（参数）{"offset":0,"type":"day"}
	 */
	public static String DAILY_HOTANSTER_1_TO_5 = "{\"offset\":0,\"type\":\"day\"}";
	
	/**
	 * 日度热门前5-10（参数）{"offset":5,"type":"day"}
	 */
	public static String DAILY_HOTANSTER_5_TO_10 = "{\"offset\":5,\"type\":\"day\"}";
	
	
	/**
	 * 知乎域名 https://www.zhihu.com/
	 */
	public static String ZHIHU_OFFCIAL_DOMAIN  = "https://www.zhihu.com/";
	
	
	/**
	 * 拼装装转载部分P1
	 * <blockquote><p><span style="font-size: 10px;"><em>作者：酒宴正经</em></span></p><p><span style="font-size: 10px;"><em>转自：https://www.zhihu.com/question/266230511/answer/30516452<span style="font-size: 12px;"></span>8&nbsp;</em></span></p></blockquote>
	 */
	public static String QOUTE_TAG_P1 = "<blockquote><p><span style=\"font-size: 10px;\"><em>作者：";
	
	/**
	 * 拼装装转载部分P2
	 */
	public static String QUOTE_TAG_P2 = "</em></span></p><p><span style=\"font-size: 10px;\"><em>转自：";
	
	/**
	 * 拼装装转载部分P3
	 */
	public static String QUOTE_TAG_P3 ="<span style=\"font-size: 12px;\"></span>8&nbsp;</em></span></p></blockquote>";
	
	/**
	 * 名词：知乎
	 */
	public static String WORD_ZHIHU = "知乎";
}

