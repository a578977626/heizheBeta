package com.heizhe.tools;
/**
 * 专门处理特殊的String
 * @author chenxb2
 *
 */
public class RuleString {

	/**
	 * 知乎返回评论如果大于1000时返回的是K
	 * @param like
	 * @return
	 */
	public static Integer returnLikeCount(String like){
		Integer res = 0;
		if(like.contains("K")){
			res =  Integer.valueOf(like.substring(0, like.length()-1)+"000");
		}else{
			res = Integer.valueOf(like);
		}
		return res;
	}
	
	
	/**
	 * 当没有评论的时候知乎返回的是‘添加评论’
	 * @param comment
	 * @return
	 */
	public static Integer returnCommentCount(String comment){
		return "添加评论".equals(comment)?0:Integer.valueOf(comment.substring(0, comment.length()-4));
	}
}
