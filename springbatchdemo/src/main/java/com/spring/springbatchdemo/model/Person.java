package com.spring.springbatchdemo.model;

import java.util.Date;

/**
 * @author VishalCRai
 *
 */
public class Person {

	private long id;
	private String firstName;
	private String lastName;
	private int status;
	private Date dateTime;

	
	public Person() {
	}

	public Person(long id, String firstName, String lastName, int status, Date dateTime) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.dateTime = dateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName + ", status:" + status + ", dateTime:" + dateTime;
	}

    public static void main(String [] args) {
    	System.out.println("Date="+new Date());
    }
    
}
