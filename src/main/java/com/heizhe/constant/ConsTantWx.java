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
	 * 月度热门前5（参数）{"offset":0,"type":"month"}
	 */
	public static String MONTH_HOTANSTER_1_TO_5 = "{\"offset\":0,\"type\":\"month\"}";
	
	/**
	 * 月度热门前5-10（参数）{"offset":5,"type":"month"}
	 */
	public static String MONTH_HOTANSTER_5_TO_10 = "{\"offset\":5,\"type\":\"month\"}";
	
	
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
	
	/**
	 * 头部求关注进度条部分
	 */
	
	public static String HEAD_TAG_PART = "<section style=\"background-color: rgb(255, 255, 255);box-sizing: border-box;\"><section class=\"\" style=\"box-sizing: border-box;\" powered-by=\"xiumi.us\"><section class=\"\" style=\"margin-top: 8px;margin-right: 0%;margin-left: 30%;line-height: 1em;text-align: center;box-sizing: border-box;\"><section class=\"\" style=\"width: 0px;display: inline-block;vertical-align: bottom;border-bottom: 13px solid rgb(0, 0, 0);border-left: 13px solid transparent !important;border-right: 13px solid transparent !important;box-sizing: border-box;\"></section></section></section><section class=\"\" style=\"box-sizing: border-box;\" powered-by=\"xiumi.us\"><section class=\"\" style=\"margin-right: 0%;margin-left: 0%;box-sizing: border-box;\"><section class=\"\" style=\"display: inline-block;width: 100%;vertical-align: top;background-image: url(&quot;http://mmbiz.qpic.cn/mmbiz_gif/uDbxxbf4KbTEuSLBwW8ArIE20HvbmusZ9HuibhtgPO84ic9SoqQbyMh2Iw9zTHVGHo9U83VtibkcgibJBWibpQ3MYaw/0?wx_fmt=gif&quot;);background-position: 0% 0%;background-repeat: repeat;background-size: 103.933%;background-attachment: scroll;background-color: rgb(254, 255, 255);box-sizing: border-box;\"><section class=\"\" style=\"box-sizing: border-box;\" powered-by=\"xiumi.us\"><section class=\"\" style=\"box-sizing: border-box;\"><section class=\"\" style=\"display: inline-block;vertical-align: top;width: 80%;box-sizing: border-box;\"><section class=\"\" style=\"box-sizing: border-box;\" powered-by=\"xiumi.us\"><section class=\"\" style=\"margin: 5px 0%;box-sizing: border-box;\"><section class=\"\" style=\"color: rgb(255, 255, 255);font-size: 14px;line-height: 1.6;padding-right: 10px;padding-left: 10px;box-sizing: border-box;\"><p style=\"box-sizing: border-box;\">没想到你居然看完了这个求关注进度条</p></section></section></section></section><section class=\"\" style=\"display: inline-block;vertical-align: top;width: 20%;box-sizing: border-box;\"><section class=\"\" style=\"box-sizing: border-box;\" powered-by=\"xiumi.us\"><section class=\"\" style=\"text-align: center;margin-top: 5px;margin-right: 0%;margin-left: 0%;font-size: 15px;box-sizing: border-box;\"><section class=\"\" style=\"max-width: 100%;vertical-align: middle;display: inline-block;width: 90%;overflow: hidden !important;box-sizing: border-box;\"><img data-ratio=\"0.34\" data-src=\"https://mmbiz.qpic.cn/mmbiz_png/uDbxxbf4KbTEuSLBwW8ArIE20HvbmusZjpmBl6hH0SCC5icS58FHKFQN1HA9BFiaNFekfBRSsAvWCVia3aSbfW2lQ/640?wx_fmt=png\" data-type=\"png\" data-w=\"100\" style=\"vertical-align: middle; box-sizing: border-box; width: 100% !important; height: auto !important; visibility: visible !important;\" width=\"100%\" _width=\"100%\" class=\"\" src=\"https://mmbiz.qpic.cn/mmbiz_png/uDbxxbf4KbTEuSLBwW8ArIE20HvbmusZjpmBl6hH0SCC5icS58FHKFQN1HA9BFiaNFekfBRSsAvWCVia3aSbfW2lQ/640?wx_fmt=png&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1\" data-fail=\"0\"></section></section></section></section></section></section></section></section></section></section>";

	/**
	 * cxb's openID
	 */
	public static String CXB_OPENID = "o14cB1mSBkyq4PCk3jLXnrnSui2g";
	
	/**
	 * 查询前三条回答 
	 * SELECT * FROM daily_hot_basic WHERE answer_type <> '禁止转载' AND to_days(created_date) = to_days(now()) ORDER BY liked_count DESC LIMIT 3
	 */
	public final static String PREVIEW_THREE = "SELECT * FROM daily_hot_basic WHERE answer_type <> '禁止转载' AND to_days(created_date) = to_days(now()) ORDER BY liked_count DESC LIMIT 3";
	
	/**
	 * 查询前三条回答 (有2张图以上的)
	 * SELECT * FROM daily_hot_basic WHERE answer_type <> '禁止转载' AND to_days(created_date) = to_days(now()) AND image_count > 2 ORDER BY liked_count DESC LIMIT 3
	 */
	public final static String PREVEIW_THREE_IMAGEOVER2 = "SELECT * FROM daily_hot_basic WHERE answer_type <> '禁止转载' AND to_days(created_date) = to_days(now()) AND image_count > 2 ORDER BY liked_count DESC LIMIT 3";

	/**
	 * 总长:https://www.zhihu.com/api/v4/answers/309785287/comments
	 * 获取评论url前缀
	 */
	public final static String GET_COMMENT_HOT_RUL_PREFIEX = "https://www.zhihu.com/api/v4/answers/";
	/**
	 * 获取评论url后缀
	 */
	public final static String GET_COMMENT_HOT_RUL_SUBFIEX ="/comments";
}

