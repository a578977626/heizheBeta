package com.heizhe.tools;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * @author chenxb2
 *
 */
public class LogUtil {
	
	/**
	 * 是否开启debug
	 */
	private static boolean debug = true;
	private static final Logger logger = Logger.getLogger(LogUtil.class);

	/**
	 * 打印消息
	 * 
	 * @param msg
	 */
	public static void info(String msg){
		if(debug){
			logger.info(msg);
		}
	}
	
	/**
	 * 打印消息和异常堆栈
	 * 
	 * @param msg
	 * @param t
	 */
	public static void infoException(String msg, Throwable t){
		if(debug){
			logger.info(msg, t);
		}
	}
	
	/**
	 * 打印错误消息
	 * 
	 * @param msg
	 */
	public static void error(String msg){
		logger.error(msg);
	}
	
	/**
	 * 打印错误消息和异常堆栈
	 * 
	 * @param msg
	 * @param t
	 */
	public static void errorException(String msg, Throwable t){
		logger.error(msg, t);
	}
	
	/**
	 * 打印异常堆栈
	 * 
	 * @param t
	 */
	public static void exception(Throwable t){
		logger.error(t);
	}

	/**
	 * 开启打印日志
	 */
	public static void debug() {
		debug(true);
	}
	/**
	 * 开启或关闭打印日志
	 */
	public static void debug(boolean debug) {
		LogUtil.debug = debug;
	}
}
