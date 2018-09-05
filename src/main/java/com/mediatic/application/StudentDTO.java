package com.mediatic.application;

import com.google.gson.annotations.Expose;
import com.mediatic.domain.Student;

public class StudentDTO {

	@Expose
	private Integer idStudent;
	@Expose
	private String name;
	@Expose
	private Boolean grouped;
	
	public StudentDTO(Student student) {
		this.idStudent = student.getIdStudent();
		this.name = student.getName();
		this.grouped = student.getGrouped();
	}

	public Integer getIdStudent() {
		return idStudent;
	}

	public String getName() {
		return name;
	}

	public Boolean getGrouped() {
		return grouped;
	}
	
	
}
