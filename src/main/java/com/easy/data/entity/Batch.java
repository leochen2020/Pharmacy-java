package com.easy.data.entity;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Batch implements Comparable<Batch> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String inTime;
	private String overTime;
	private int number;
	private String user;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	@Override
	public int compareTo(Batch o) {
		// TODO Auto-generated method stub
		if (this.getOverTime().equals(o.getOverTime())) {
			return this.getId().compareTo(o.getId());
		}
		
		try {
			return Item.DATA_FORMAT.parse(o.getOverTime()).compareTo(Item.DATA_FORMAT.parse(this.getOverTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return this.getId().compareTo(o.getId());
		}
	}
}
