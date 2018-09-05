package com.mediatic.domain;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Group {
	@Expose
	private List<Student> studentList;

	public Group(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	
	
}
