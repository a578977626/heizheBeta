package com.heizhe.controller;

import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.entity.DailyHotBasic;
import com.heizhe.rep.DailyHotRespository;
import com.heizhe.tools.CommonTools;
import com.heizhe.tools.LogUtil;

public class Temp {
//	@Autowired
//	private static DailyHotRespository dailyHotRespository;
public static void main(String[] args) throws HttpProcessException {
	String ansUrl = "https://www.zhihu.com/question/264958421/answer/309849646";
	
	String res = CommonTools.getHotComment(ansUrl);
	System.out.println(res);
	JSONObject json = (JSONObject) JSON.parse(res);
	String jjj = json.getJSONArray("data").getJSONObject(0).getString("content");
	System.out.println(jjj);
	
	
	
	
}}