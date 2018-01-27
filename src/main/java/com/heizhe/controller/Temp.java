package com.heizhe.controller;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.tools.CommonTools;

public class Temp {
public static void main(String[] args) throws HttpProcessException {
	String pre5Source  = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_1_TO_5);
	Document pre5Doc = CommonTools.StringToDoc(pre5Source);
	Elements items = pre5Doc.getElementsByClass("explore-feed feed-item");
	for(Element item : items){
		item.setBaseUri(ConsTantWx.ZHIHU_OFFCIAL_DOMAIN);
		String question 	= item.select("[class=question_link]").first().text();
		String answerUrl 	= item.select("[class=question_link]").first().absUrl("href");
		String author 		= item.select("[class=zm-item-rich-text expandable js-collapse-body]").first().attr("data-author-name");
		String likedCount 	= item.select("[class=zm-item-vote-count js-expand js-vote-count]").first().text();
		String summary 		= item.select("[class=zh-summary summary clearfix]").first().text();//摘要
		String dateInfo 	= item.select("[class=answer-date-link meta-item]").first().text();//日期信息
		String commentCount = item.select("[class=meta-item toggle-comment js-toggleCommentBox]").first().text();//评论数
		String answerType  	= item.select("[class=meta-item copyright]").first().text();//可转载类型
		
		
		System.out.println(question);
		System.out.println(author);
		System.out.println(answerUrl);
		System.out.println(likedCount);
		System.out.println(summary);
		System.out.println(dateInfo);
		System.out.println(commentCount);
		System.out.println(answerType);
	}
}
}
