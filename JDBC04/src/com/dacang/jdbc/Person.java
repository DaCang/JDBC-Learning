package com.dacang.jdbc;

public class Person {
	private int id;
	private String name;
	private int age;
	private String description;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(int id, String name, int age, String description) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.description = description;
	}

	public Person(String name, int age, String description) {
		super();
		this.name = name;
		this.age = age;
		this.description = description;
	}

	public int getAge() {
		return age;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", description=" + description + "]";
	}

}
