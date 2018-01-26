package com.heizhe.job;


import java.io.FileNotFoundException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.tools.CommonTools;


public class DailyHotJob {
    public final static long ONE_MINUTE =  5 * 1000;
    
    public final static long HALF_HOUR =  60 * 30 * 1000;
    
    public final static long TWO_HOUR =  60 * 60 * 2 * 1000;
    
    
    /**
     * 抓取知乎日度热门题目
     * 此接口每次只能返回5条记录，需要更多则需修改offset参数
     * @throws HttpProcessException, FileNotFoundException 
     * 
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getDouYuHostJob() throws HttpProcessException, FileNotFoundException{
    	String pre5Source  = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_1_TO_5);
    	Document pre5Doc = CommonTools.StringToDoc(pre5Source);
    	
    	Elements items = pre5Doc.getElementsByClass("explore-feed feed-item");
    	for(Element item : items){
    		Element ele = item.select("[class=question_link]").first();
    		ele.text();
    	}
    	
    	
    	
    	String pre10Source  = CommonTools.getHotAnswerBasic(ConsTantWx.DAILY_HOTANSTER_5_TO_10);
    	Document pre10Doc = CommonTools.StringToDoc(pre10Source);
    }
    
}
