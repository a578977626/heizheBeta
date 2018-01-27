package com.heizhe.constant;

public class ConsTantWx {

	/**
	 * 换行标签
	 */
	public static String BR_TAG = "<p><br></p>";
	
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
	 * 日度热门前5（参数）
	 */
	public static String DAILY_HOTANSTER_1_TO_5 = "{\"offset\":0,\"type\":\"day\"}";
	
	/**
	 * 日度热门前5-10（参数）
	 */
	public static String DAILY_HOTANSTER_5_TO_10 = "{\"offset\":5,\"type\":\"day\"}";
	
	
	/**
	 * 知乎域名 https://www.zhihu.com/
	 */
	public static String ZHIHU_OFFCIAL_DOMAIN  = "https://www.zhihu.com/";
}

