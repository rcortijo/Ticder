package com.mediatic.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mediatic.application.StudentController;
import com.mediatic.application.StudentDTO;
import com.mediatic.domain.Group;
import com.mediatic.util.InvalidParamException;
import com.mediatic.util.NotFoundException;

@RestController
@CrossOrigin
public class StudentRestController {

	StudentController controller = new StudentController();
	
	@PostMapping(value = "/students", produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jStudent) throws InvalidParamException, NotFoundException {

		StudentDTO newStudent = new Gson().fromJson(jStudent, StudentDTO.class);
		StudentDTO student = controller.register(newStudent);
		
		StudentDTO studentCreated = controller.getStudentByName(student.getName());
		return toJson(studentCreated);
	}
	
	@GetMapping(value = "/students", produces = "application/json;charset=UTF-8")
	public String listStudents() throws NotFoundException {

		List<StudentDTO> students = controller.listStudents();

		return toJson(students);
	}
	
	@GetMapping(value = "/students/{idStudent}", produces = "application/json;charset=UTF-8")
	public String getStudent(@PathVariable int idStudent) throws NotFoundException {

		StudentDTO student = controller.getStudentById(idStudent);

		return toJson(student);
	}
	
	@DeleteMapping(value = "/students/{idStudent}", produces = "application/json;charset=UTF-8")
	public String delete(@PathVariable int idStudent) throws InvalidParamException, NotFoundException {

		StudentDTO student = controller.delete(idStudent);
		return toJson(student);
	}
	
	@PostMapping(value = "/students/match", produces = "application/json;charset=UTF-8")
	public String match() throws InvalidParamException, NotFoundException {

		List<Group> groupingCreated = controller.match();
		return toJson(groupingCreated);
	}
	
	private String toJson(Object object){
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
}
