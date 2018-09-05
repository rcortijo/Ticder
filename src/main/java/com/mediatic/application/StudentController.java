package com.mediatic.application;

import java.util.ArrayList;
import java.util.List;

import com.mediatic.domain.Group;
import com.mediatic.domain.Student;
import com.mediatic.repository.StudentRepository;
import com.mediatic.util.InvalidParamException;
import com.mediatic.util.NotFoundException;

public class StudentController {

	StudentRepository repository = new StudentRepository();
	
	public StudentDTO register(StudentDTO studentDTO) throws InvalidParamException, NotFoundException {
		
		Student student = new Student(studentDTO.getName());
		repository.saveStudent(student);
		return new StudentDTO(student);
	}
	
	public StudentDTO delete(int idStudent) throws InvalidParamException, NotFoundException {
		
		Student student = repository.getStudentId(idStudent);
		repository.removeStudent(student);
		return new StudentDTO(student);
	}
	
	public StudentDTO getStudentById(int idStudent) throws NotFoundException {
		
		Student student = repository.getStudentId(idStudent);
		return new StudentDTO(student);
	}
	
	public List<StudentDTO> listStudents() throws NotFoundException {
		List<Student> studentList = repository.getAllStudents();
		List<StudentDTO> studentDTOList = new ArrayList<>();

		if (studentList.isEmpty())
			throw new NotFoundException();

		for (Student s : studentList) {
			studentDTOList.add(new StudentDTO(s));
		}
		return studentDTOList;
	}
	
	public StudentDTO getStudentByName(String name) throws InvalidParamException, NotFoundException {

		Student student = repository.getStudentByName(name);
		return new StudentDTO(student);
	}
	
	public List<Group> match() {
		removeGroups();
		
		Group grouping;
		List<Student> groupStudent = new ArrayList<>();
		List<Group> groupList = new ArrayList<>();
		
		int nro_group = 0;
		int aleatorio;
		if (repository.getAllStudents().size() % 2 == 0)
			nro_group = 2;
		else
			nro_group = 3;
	
		do {
			aleatorio = (int) (Math.random()*repository.getAllStudents().size());
			Student student = repository.getStudentId(repository.getAllStudents().get(aleatorio).getIdStudent());
			
			if (!student.getGrouped()) {
				groupStudent.add(student);
				repository.getAllStudents().get(aleatorio).setGrouped(true);
				
				if(groupStudent.size() == nro_group) {
					grouping = new Group(groupStudent); 
					groupList.add(grouping);
					groupStudent = new ArrayList<>();						
				}else {
					if (getCountNotGrouping() == 0) {
						grouping = new Group(groupStudent); 
						groupList.add(grouping);
					}
				}				
			}
				
		}while(!isFinish());
		
		return groupList;
	}
	
	public boolean isFinish() {
		for (Student s: repository.getAllStudents())
			if (!s.getGrouped())
				return false;
		return true;
	}
	
	public void removeGroups() {
		for (Student s: repository.getAllStudents())
			s.setGrouped(false);
	}
	
	public int getCountNotGrouping() {
		int count=0;
		for (Student s: repository.getAllStudents())
			if (!s.getGrouped())
				count++;
		return count;
	}
}
