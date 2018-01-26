package com.heizhe.tools;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class CommonTools {
	
	
	
	/**
	 * 获取斗鱼主页所有主播的在线信息
	 */
//	public static List<TopHost> getDouYuListHostDataOnline(String url){
//		Document doc = getDocByUrl(url); 
//		doc.setBaseUri("https://www.douyu.com");
//		
//        Elements links = doc.select("a[data-rid]");  
//  
//        List<TopHost> hostList = new ArrayList<TopHost>();
//        for (Element link : links) {
//        	Elements ss = link.getElementsByClass("dy-num fr");
//        	String liveNum = "0";
//        	if(ss.size()>0){
//        		 liveNum = ss.get(0).text();
//        	}
//        	String herf = link.attr("abs:href");
//        	String name = link.getElementsByClass("dy-name").get(0).text();
//        	String roomId = link.attr("data-rid");
////        	String headPortrait = getDouYuHPByUrl(herf);不需要头像，其实这里需要改造，到时只需要房间id，因为我已经把主播数据都抓下来了
//        	
//        	TopHost topHoost = new TopHost("douyu",null);
//        	topHoost.setAddress(herf);
//        	topHoost.setHostName(name);
////        	topHoost.setHeadPortrait(headPortrait);
//        	topHoost.setRoomId(roomId);
//        	topHoost.setLiveNum(caculateNum(liveNum));
//        	
//        	hostList.add(topHoost);
//        } 
//        return hostList;
//	}
	
	/**
	 * 获取Bilbili主页所有主播的在线信息
	 */
//	public static List<TopHost> getBiLiListHostDataOnline(String url){
//		Document doc = PhantomJsTools.getSouceCodeByPhantomJs(url);
//		doc.setBaseUri("http://live.bilibili.com");
//		
//        Elements links = doc.select("a.move-in-left");  
//  
//        List<TopHost> hostList = new ArrayList<TopHost>();
//        for (Element link : links) {
//        	String liveNum = "0";
//        	if(link.getElementsByClass("viewers-count").size()>0){
//        		liveNum = link.getElementsByClass("viewers-count").get(0).text();
//        	}
//        	String herf = link.attr("abs:href");
//        	String name = link.getElementsByClass("anchor-name").get(0).text();
//        	String roomId = link.attr("href");
////        	String headPortrait = getXiongMaoHPByUrl(herf);//不需要头像
////        	TopHost host = new TopHost("bili",null);
//        	host.setAddress(herf);
//        	host.setHostName(name);
//        	host.setRoomId(roomId);
//        	host.setLiveNum(caculateNum(liveNum));
//        	
//        	hostList.add(host);
//        } 
//        return hostList;
//	
//	}
	
	
	
	/**
	 * 传入房间链接获取斗鱼主播头像
	 * @param url
	 * @return
	 */
	public static String getDouYuHPByUrl(String url) {
		Document doc = getDocByUrl(url);
		Elements ll =null;
		try {
			 ll = doc.getElementsByClass("anchor-pic fl");
		} catch (Exception e) {
			ll = new Elements();
			System.out.println("nabudaotouxiang");
		}
		/*
		  <div class="anchor-pic fl"> 
 				<img src="https://apic.douyucdn.cn/upload/avanew/face/201702/14/18/3d676d572a428d5f699a55b7d2d2b48d_middle.jpg?rltime"> 
			</div>
		 */
		if(ll.size()>0){
			String hd = ll.get(0).getElementsByTag("img").attr("src");
			return hd;
		}
		return null;
	}
	
	/**
	 * 熊猫tv的主播详情页不是静态的html，所以用jsoup不能直接使用。
	 * 用PhantomJs 呵呵哈哈哈
	 * @param url
	 * @return
	 */
	public static String getXiongMaoHPByUrl(String url) {
		Document doc = PhantomJsTools.getSouceCodeByPhantomJs(url);
		Elements ll =null;
		try {
			ll = doc.select(".room-head-info-cover"); 
		} catch (Exception e) {
			ll = new Elements();
			System.out.println("没有头像");
		}
		if(ll.size()>0){
			String hd = ll.get(0).getElementsByTag("img").attr("src");
			System.out.println("the headrait path"+hd);
			return hd;
		}
		System.out.println("the headrait path is  null");
		return null;
	}
	
	public static Document getDocByUrl (String url){
	    Document doc = null;
		try {doc = Jsoup.connect(url).get();} catch (IOException e) {e.printStackTrace();} 
		return doc;
	}
	
	/**
	 * 计算结果
	 * @param liveNum
	 * @return
	 */
	public static Float caculateNum(String liveNum){
		Float last = 0f;
    	if(liveNum.contains("万")){
    		last = Float.valueOf(liveNum.substring(0, liveNum.length()-1))*10000;
    	}else{
    		last = Float.valueOf(liveNum);
    	}
		return  last;
	}

	public static Document getQuestionData(String questionUrl) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
