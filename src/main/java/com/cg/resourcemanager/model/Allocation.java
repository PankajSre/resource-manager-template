package com.cg.resourcemanager.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Allocation {

	private Integer id;

	private int itemId;

	private int userId;

	private int projectId;

	private Date date;

	private int poNo;

	private float poAmount;

	private Date life;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPoNo() {
		return poNo;
	}

	public void setPoNo(int poNo) {
		this.poNo = poNo;
	}

	public float getPoAmount() {
		return poAmount;
	}

	public void setPoAmount(float poAmount) {
		this.poAmount = poAmount;
	}

	public Date getLife() {
		return life;
	}

	public void setLife(Date life) {
		this.life = life;
	}

}
