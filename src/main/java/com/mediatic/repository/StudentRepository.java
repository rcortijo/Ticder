package com.mediatic.repository;

import java.util.ArrayList;
import java.util.List;

import com.mediatic.domain.Student;
import com.mediatic.util.InvalidParamException;
import com.mediatic.util.NotFoundException;

public class StudentRepository {

	private static List<Student> listStudent = new ArrayList<>();
	
	public List<Student> getAllStudents(){
		return new ArrayList<>(listStudent);
	}	
	
	public void saveStudent(Student student) throws InvalidParamException, NotFoundException{
		if(student==null) {
	        throw new InvalidParamException();
	    }
		listStudent.add(student);
	}

    public void removeStudent(Student student) throws InvalidParamException, NotFoundException{
		if(student == null) {
		    throw new InvalidParamException();
		}
		listStudent.remove(student);
	}
    
    public Student getStudentId(int idStudent) {
    	for (Student s : listStudent)
    		if (s.getIdStudent() == idStudent)
    			return s;
    	return null;
    }
    public Student getStudentByName(String name) {
    	for (Student s : listStudent)
    		if (s.getName().equals(name))
    			return s;
    	return null;
    }
}
