package com.mediatic.domain;

import com.google.gson.annotations.Expose;

public class Student {

	@Expose
	private Integer idStudent;
	@Expose
	private String name;
	@Expose
	private Boolean grouped;
	private static int COUNT = 1;
	
	public Student(String name) {
		this.name = name;
		this.grouped = false;
		this.idStudent = COUNT;
		COUNT++;
	}
	public Integer getIdStudent() {
		return idStudent;
	}
	public String getName() {
		return name;
	}
	public static int getCOUNT() {
		return COUNT;
	}
	public Boolean getGrouped() {
		return grouped;
	}
	public void setGrouped(Boolean grouped) {
		this.grouped = grouped;
	}
	
	
}
