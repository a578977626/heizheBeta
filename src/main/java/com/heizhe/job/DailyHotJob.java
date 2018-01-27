package com.heizhe.job;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.entity.DailyHotBasic;
import com.heizhe.rep.DailyHotRespository;
import com.heizhe.tools.CommonTools;

@Component
public class DailyHotJob {
	public final static long ONE_MINUTE = 10 * 1000;

	public final static long HALF_HOUR = 60 * 30 * 1000;

	public final static long TWO_HOUR = 60 * 60 * 2 * 1000;

	@Autowired
	private DailyHotRespository dailyHotRespository;

	/**
	 * 抓取知乎日度热门题目 此接口每次只能返回5条记录，需要更多则需修改offset参数
	 * 
	 * @throws HttpProcessException,
	 *             FileNotFoundException
	 * 
	 */
//	 @Scheduled(fixedDelay=TWO_HOUR)
	public void getDailyHotJob() throws HttpProcessException, FileNotFoundException {
		String pre5Source = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_1_TO_5);
		String pre10Source = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_5_TO_10);

		Document pre5Doc = CommonTools.StringToDoc(pre5Source);
		Document pre10Doc = CommonTools.StringToDoc(pre10Source);

		List<DailyHotBasic> pre5List = doForGetDailyHotJob(pre5Doc);
		List<DailyHotBasic> pre10List = doForGetDailyHotJob(pre10Doc);
		
		pre10List.addAll(pre5List);

		/**
		 * 重复的去掉，不保存
		 * 关于匹配器的使用，用来做这种多字段的过滤是否存在不是太适合
		 */
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "likedCount", "summary","dateInfo","commentCount","createdDate")
				.withIncludeNullValues();
		for (DailyHotBasic hot : pre10List) {
			Example<DailyHotBasic> example = Example.of(hot, matcher);
			if (!dailyHotRespository.exists(example)) {
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
			dayHot.setCommentCount(Integer.valueOf(commentCount.substring(0, commentCount.length()-4)));
			dayHot.setDateInfo(dateInfo);
			dayHot.setLikedCount(Integer.valueOf(likedCount));
			dayHot.setQuestion(question);
			dayHot.setSummary(summary);
			dayHot.setCreatedDate(new Date());
			
			dayList.add(dayHot);
		}
		return dayList;
	}

}
