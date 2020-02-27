package com.easy.data.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.Transient;


@Entity
public class Item {
	
	public static SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String detail;
	private String updateTime;
	private String overTime;
	private String img;
	private Boolean removed = false;
	private String code = "";
	private int buyPoint = 0;
	private int maxNumber = 100;
	private int minNumber = 0;
	private int number = 0;
	private String unit = "";
	
	@Transient
	private boolean over = false;
	
	@OneToMany(mappedBy = "item",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<History> history = new ArrayList<History>();
	
	@OneToMany(mappedBy = "item",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Batch> batch = new ArrayList<Batch>();

	public Boolean getRemoved() {
		return removed;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public List<Batch> getBatch() {
		return batch;
	}

	public void setBatch(List<Batch> batch) {
		this.batch = batch;
	}
	
	public void addBatch(int number, String user, Date overTime) {
		Batch b = new Batch();
		b.setInTime(this.DATA_FORMAT.format(new Date()));
		b.setOverTime(this.DATA_FORMAT.format(overTime));
		b.setItem(this);
		b.setNumber(number);
		this.number += number;
		b.setUser(user);
		this.getBatch().add(b);
		updateOverTime();
	}
	
	public void updateOverTime() {
		overTime = null;
		for (Batch b : this.getBatch()) {
			if (overTime == null) {
				overTime = b.getOverTime();
				continue;
			}
			
			try {
				if (DATA_FORMAT.parse(overTime).after(DATA_FORMAT.parse(b.getOverTime()))) {
					overTime = b.getOverTime();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addHistory(String user, String name) {
		History h = new History();
		h.setItem(this);
		h.setName(History.DELETE);
		h.setUser(user);
		h.setTime(new Date());
		this.getHistory().add(h);
	}

	public int getBuyPoint() {
		return buyPoint;
	}

	public void setBuyPoint(int buyPoint) {
		this.buyPoint = buyPoint;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOverTime() {
		return overTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	@Transient
	public boolean isOver() {
		
		if (overTime == null) {
			if (this.getBatch().size() > 0) {
				updateOverTime();
			}
		}
		
		if (overTime == null) {
			return false;
		}
		
		String  d = DATA_FORMAT.format(new Date());
		
		try {
			int days = (int) ((DATA_FORMAT.parse(d).getTime() - DATA_FORMAT.parse(overTime).getTime()) / (1000*3600*24));
			over = days >= -3;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}
}
