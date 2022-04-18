package com.androidclass.simpleapp;

import java.util.Date;

public class Item {
	private String wishString;
	private Date date;
	public Item(String wishString, Date date) {
		super();
		this.wishString = wishString;
		this.date = date;
	}
	public String getWishString() {
		return wishString;
	}
	public void setWishString(String wishString) {
		this.wishString = wishString;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
