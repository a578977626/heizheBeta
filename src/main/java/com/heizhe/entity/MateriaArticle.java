package com.heizhe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 上传的图文详情
 * @author chenxb2
 *
 */
@Entity
@Table(name="materia_article_tb")
public class MateriaArticle implements Serializable{

	private static final long serialVersionUID = -2013081265090341164L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * dailyHotBasicId
	 */
	private Long basicId;
	
	/**
	 * 上传的wxcontent
	 */
	private String articleContent;
	/**
	 * 上传状态
	 */
	private String uploadStatus;
	/**
	 * 返回的mediaId
	 */
	private String mediaId;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * dailyHotSourceUrl 答案的url
	 */
	private String basicUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getBasicUrl() {
		return basicUrl;
	}
	public void setBasicUrl(String basicUrl) {
		this.basicUrl = basicUrl;
	}
	
	
	
}
