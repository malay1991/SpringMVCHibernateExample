package io.springmvc.service;

import io.springmvc.model.StudentOne;

import java.util.List;

public interface ServiceStudent {

	StudentOne findById(int id);
	
	void saveStudent(StudentOne studentOne);
	
	void updateStudent(StudentOne studentOne);
	
	void deleteStudentByCode(String code);

	List<StudentOne> findAllStudents(); 
	
	StudentOne findStudentByCode(String code);

	boolean isStudentCodeUnique(Integer id, String code);
	
}
