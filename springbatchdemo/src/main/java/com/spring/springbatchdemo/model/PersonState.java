package com.spring.springbatchdemo.model;

import java.util.Date;

/**
 * @author VishalCRai
 *
 */
public class PersonState {
private long id;
private String fullName;
private Date creationDateTime;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public Date getCreationDateTime() {
	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
	this.creationDateTime = creationDateTime;
}


}
