package com.sad.yardmanagementsystem.controller.dto;

public class MyPojo
{
private String user;
private String password;
private String key;
private String type;
private String timestamp_reg;


public MyPojo() {
	super();
}


public MyPojo(String user, String password, String key, String type, String timestamp_reg) {
	super();
	this.user = user;
	this.password = password;
	this.key = key;
	this.type = type;
	this.timestamp_reg = timestamp_reg;
}


public String getUser() {
	return user;
}


public void setUser(String user) {
	this.user = user;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getKey() {
	return key;
}


public void setKey(String key) {
	this.key = key;
}


public String getType() {
	return type;
}


public void setType(String type) {
	this.type = type;
}


public String getTimestamp_reg() {
	return timestamp_reg;
}


public void setTimestamp_reg(String timestamp_reg) {
	this.timestamp_reg = timestamp_reg;
}


}