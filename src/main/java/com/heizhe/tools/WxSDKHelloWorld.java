package com.heizhe.tools;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;

public class WxSDKHelloWorld {
public static void main(String[] args) {
	WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
	config.setAppId("wx16f32a479bcba04c"); // 设置微信公众号的appid
	config.setSecret("8caecfa027bf79a92a132927f7bb4bfe"); // 设置微信公众号的app corpSecret
	config.setToken("Mg4Kl5Pl5MmpkGltp55llHPhn4pGZJ32"); // 设置微信公众号的token
	config.setAesKey("Oqn7zscCCOpPP8pp7qnO7nL7PoM7MxSoxLPl3Pnpcpn"); // 设置微信公众号的EncodingAESKey

	WxMpService wxService = new WxMpServiceImpl();
	wxService.setWxMpConfigStorage(config);

	// 用户的openid在下面地址获得 
	// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=用户管理&form=获取关注者列表接口%20/user/get 
	
	
	//cxb's openId
	String openid = "o14cB1mSBkyq4PCk3jLXnrnSui2g";
	WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(openid).content("Hello World").build();
	try {
		wxService.getKefuService().sendKefuMessage(message);
	} catch (WxErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void something(){}
}
