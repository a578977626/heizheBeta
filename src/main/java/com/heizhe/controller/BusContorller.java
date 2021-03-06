package com.heizhe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.heizhe.entity.DailyHotBasic;
import com.heizhe.rep.DailyHotRespository;
import com.heizhe.service.OpService;

@RestController
@RequestMapping("/wechat/portal")
public class BusContorller {

	@Autowired
	private OpService opService;
	
	@Autowired
   	private DailyHotRespository dailyHotRespository;

	@ResponseBody
	@RequestMapping(value = "/uploadMat001", method = RequestMethod.GET)
	public String uploadMatZhiHu001(String url) {
		opService.uploadMatZhiHu001(url);
		return "123";
	}

	@ResponseBody
	@RequestMapping(value = "/uploadMat", method = RequestMethod.GET)
	public String uploadMatZhiHu(String url) {
		opService.uploadMatZhiHu(url);
		return url;
	}

	/**
	 * 给自己发送信息的接口
	 * 
	 * @param mes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendAMessage", method = RequestMethod.GET)
	public String sendAMessage(String mes) {
		opService.sendAMessage(mes);
		return mes;
	}

	/**
	 * 发送图文推送
	 * 
	 * @param mes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMessageNews", method = RequestMethod.GET)
	public String sendMessageNews(String mediaId) {
		opService.sendMessageNews(mediaId);
		return mediaId;
	}
	
	
	/**
	 * 生成3条文章的预览，并保存mediaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadMatToPreview", method = RequestMethod.GET)
	public String uploadMatToPreview() {
		String mediaId = opService.uploadMatToPreview();
		return mediaId;
	} 
	
	/**
	 * 根据mediaId预览
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/previewByMediaId", method = RequestMethod.GET)
	public String previewByMediaId(String mediaId) {
		 opService.priviewByMediaId(mediaId);
		return "可能成功了吧";
	} 
	
	/**
	 * test 专用方法
	 * @param mediaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testFunc", method = RequestMethod.GET)
	public String saveHotDay(String mediaId) {
		String uuu = opService.uploadtest();
		return uuu;
	}
	

}
