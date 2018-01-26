package com.heizhe.tools;

public class UrlTools {
	
	/**
	 * 传入URL 返回图片名称
	 * @param url
	 */
	public static String getTheImageName(String url){
//		String url ="https://pic2.zhimg.com/50/v2-1a77e6e876ef5f24d421c9e43190d3a6_hd.jpg"; 
		int end = url.lastIndexOf("/"); 
		String name = url.substring(end+1,url.length());
		return name;
	}
	

}
