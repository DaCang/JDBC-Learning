package com.vae.jdbc;

/**
 * @author Administrator
 *
 */

public class Person {
	private Integer pId;
	private String pName;
	private Integer pAge;
	private String pDescription;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(Integer pId, String pName, Integer pAge, String pDescription) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pAge = pAge;
		this.pDescription = pDescription;
	}

	public Integer getpAge() {
		return pAge;
	}

	public String getpDescription() {
		return pDescription;
	}

	public Integer getpId() {
		return pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpAge(Integer pAge) {
		this.pAge = pAge;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

}
