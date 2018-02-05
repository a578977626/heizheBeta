package com.heizhe.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 日度热门实体类
 * @author chenxb2
 *
 */
@Entity
@Table(name="daily_hot_basic")
public class DailyHotBasic implements Serializable{
	private static final long serialVersionUID = 158062076686062854L;
	public DailyHotBasic(){
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String question;
	private String answerUrl;
	private String author;
	private Integer likedCount;
	private String summary;
	private String dateInfo;
	private Integer commentCount;
	private Date createdDate;
	private int imageCount;
	/**
	 * 按热度点赞数第一的评论
	 */
	private String firstComment;
	/**
	 * 可转载类型
	 */
	private String answerType;
	/**
	 * 平台
	 */
	private String platform;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerUrl() {
		return answerUrl;
	}
	public void setAnswerUrl(String answerUrl) {
		this.answerUrl = answerUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDateInfo() {
		return dateInfo;
	}
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getLikedCount() {
		return likedCount;
	}
	public void setLikedCount(Integer likedCount) {
		this.likedCount = likedCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getImageCount() {
		return imageCount;
	}
	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	public String getFirstComment() {
		return firstComment;
	}
	public void setFirstComment(String firstComment) {
		this.firstComment = firstComment;
	}

}
