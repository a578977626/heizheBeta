package com.heizhe.job;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.entity.DailyHotBasic;
import com.heizhe.rep.DailyHotRespository;
import com.heizhe.tools.CommonTools;
import com.heizhe.tools.LogUtil;
import com.heizhe.tools.RuleString;

@Component
public class DailyHotJob {
	public final static long ONE_MINUTE = 60 * 1000;

	public final static long HALF_HOUR = 60 * 32 * 1000;

	public final static long ONE_HOUR = 60 * 60 * 1 * 1000;
	
	public final static long TWO_HOUR = 60 * 60 * 2 * 1000;

	@Autowired
	private DailyHotRespository dailyHotRespository;

	/**
	 * 抓取知乎日度和月度热门题目 此接口每次只能返回5条记录，需要更多则需修改offset参数
	 * 
	 * @throws HttpProcessException,
	 *             FileNotFoundException
	 * 
	 */
	 @Scheduled(fixedDelay=HALF_HOUR)
	public void getDailyHotJob() throws HttpProcessException, FileNotFoundException {
		String pre5Source = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_1_TO_5);
		String pre10Source = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_5_TO_10);
		String pre5SourceMonth = CommonTools.getHotAnswerBasic(ConsTantWx.MONTH_HOTANSTER_1_TO_5);
		String pre10SourceMonth = CommonTools.getHotAnswerBasic(ConsTantWx.MONTH_HOTANSTER_5_TO_10);

		Document pre5Doc = CommonTools.StringToDoc(pre5Source);
		Document pre10Doc = CommonTools.StringToDoc(pre10Source);
		Document pre5DocMonth = CommonTools.StringToDoc(pre5SourceMonth);
		Document pre10DocMonth = CommonTools.StringToDoc(pre10SourceMonth);

		List<DailyHotBasic> pre5List = doForGetDailyHotJob(pre5Doc);
		List<DailyHotBasic> pre10List = doForGetDailyHotJob(pre10Doc);
		List<DailyHotBasic> pre5ListMonth = doForGetDailyHotJob(pre5DocMonth);
		List<DailyHotBasic> pre10ListMonth = doForGetDailyHotJob(pre10DocMonth);
		
		pre10List.addAll(pre5List);
		pre10List.addAll(pre5ListMonth);
		pre10List.addAll(pre10ListMonth);

		/**
		 * 重复的更新点赞数，不重复的直接保存
		 * 关于匹配器的使用，用来做这种多字段的过滤是否存在不是太适合
		 */
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "likedCount", "summary","dateInfo","commentCount","createdDate","imageCount")
				.withIncludeNullValues();
		for (DailyHotBasic hot : pre10List) {
			Example<DailyHotBasic> example = Example.of(hot, matcher);
//			if (!dailyHotRespository.exists(example)) {
//				dailyHotRespository.save(hot);
//			}
			if (dailyHotRespository.exists(example)) {
				dailyHotRespository.updateLikeCountByUrl(hot.getLikedCount(), hot.getAnswerUrl());
			}else{
				int imageCount = CommonTools.getImageCount(hot.getAnswerUrl());
				JSONObject commonJson = (JSONObject) JSON.parse(CommonTools.getHotComment(hot.getAnswerUrl()));
				String fComment = commonJson.getJSONArray("data").getJSONObject(0).getString("content");
				
				hot.setImageCount(imageCount);
				hot.setFirstComment(fComment);
				dailyHotRespository.save(hot);
			}
		}
	}

	private List<DailyHotBasic> doForGetDailyHotJob(Document doc) {
		List<DailyHotBasic> dayList = new ArrayList<DailyHotBasic>();
		Elements items = doc.getElementsByClass("explore-feed feed-item");
		
		for (Element item : items) {
			item.setBaseUri(ConsTantWx.ZHIHU_OFFCIAL_DOMAIN);
			String question 	= item.select("[class=question_link]").first().text();
			String answerUrl 	= item.select("[class=question_link]").first().absUrl("href");
			String author 		= item.select("[class=zm-item-rich-text expandable js-collapse-body]").first().attr("data-author-name");
			String likedCount 	= item.select("[class=zm-item-vote-count js-expand js-vote-count]").first().text();
			String summary 		= item.select("[class=zh-summary summary clearfix]").first().text();// 摘要
			String dateInfo 	= item.select("[class=answer-date-link meta-item]").first().text();// 日期信息
			String commentCount = item.select("[class=meta-item toggle-comment js-toggleCommentBox]").first().text();// 评论数
			String answerType 	= item.select("[class=meta-item copyright]").first().text();// 可转载类型

			DailyHotBasic dayHot = new DailyHotBasic();
			dayHot.setAnswerType(answerType);
			dayHot.setAnswerUrl(answerUrl);
			dayHot.setAuthor(author);
			/**
			 * 当没有评论的时候知乎返回的是‘添加评论’
			 */
			dayHot.setCommentCount(RuleString.returnCommentCount(commentCount));
			dayHot.setLikedCount(RuleString.returnLikeCount(likedCount));
			dayHot.setDateInfo(dateInfo);
			dayHot.setQuestion(question);
			dayHot.setSummary(summary);
			dayHot.setCreatedDate(new Date());
			dayHot.setPlatform(ConsTantWx.WORD_ZHIHU);
			
			dayList.add(dayHot);
		}
		return dayList;
	}

}
