package com.cg.resourcemanager.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AllocationDto {

	private Integer id;
	private Integer itemId;
	private Integer userId;

	private Integer projectId;
	private Date date;

	private Integer poNo;

	private Float poAmount;
	private Date life;

	public AllocationDto() {
	}

	public AllocationDto(@NotNull Integer id, @NotNull Integer itemId, @NotNull Integer userId,
			@NotNull Integer projectId, Date date, @NotNull Integer poNo, @NotNull Float poAmount, Date life) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.userId = userId;
		this.projectId = projectId;
		this.date = date;
		this.poNo = poNo;
		this.poAmount = poAmount;
		this.life = life;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPoNo() {
		return poNo;
	}

	public void setPoNo(Integer poNo) {
		this.poNo = poNo;
	}

	public Float getPoAmount() {
		return poAmount;
	}

	public void setPoAmount(Float poAmount) {
		this.poAmount = poAmount;
	}

	public Date getLife() {
		return life;
	}

	public void setLife(Date life) {
		this.life = life;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((life == null) ? 0 : life.hashCode());
		result = prime * result + ((poAmount == null) ? 0 : poAmount.hashCode());
		result = prime * result + ((poNo == null) ? 0 : poNo.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllocationDto other = (AllocationDto) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (life == null) {
			if (other.life != null)
				return false;
		} else if (!life.equals(other.life))
			return false;
		if (poAmount == null) {
			if (other.poAmount != null)
				return false;
		} else if (!poAmount.equals(other.poAmount))
			return false;
		if (poNo == null) {
			if (other.poNo != null)
				return false;
		} else if (!poNo.equals(other.poNo))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AllocationDto [id=" + id + ", itemId=" + itemId + ", userId=" + userId + ", projectId=" + projectId
				+ ", date=" + date + ", poNo=" + poNo + ", poAmount=" + poAmount + ", life=" + life + "]";
	}

}
