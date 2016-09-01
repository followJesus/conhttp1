package com.example.zhangcunli.conhttp1;

import java.util.Date;

/**
 * @author zhangcunli 博客
 */
public class Blogs {
	private int id;
	private int userId;
	private String title;
	private Date date;
	private String diffDate;
	private String content;

	private String touxiang;
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Blogs() {
		super();
	}

	public Blogs(int id, String title, Date date, String touxiang) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.touxiang = touxiang;
	}

	public String getDiffDate() {
		return diffDate;
	}

	public void setDiffDate(String diffDate) {
		this.diffDate = diffDate;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
