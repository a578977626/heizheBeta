package com.heizhe.controller;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.heizhe.constant.ConsTantWx;
import com.heizhe.entity.DailyHotBasic;
import com.heizhe.tools.CommonTools;
import com.heizhe.tools.LogUtil;

public class Temp {
public static void main(String[] args) throws HttpProcessException {
	String like = "11k";
	String ee = like.substring(0, like.length()-1)+"000";
	LogUtil.info(ee);
}}