package io.springmvc.dao;

import io.springmvc.model.StudentOne;

import java.util.List;

public interface DaoStudent {

	StudentOne findById(int id);

	void studentSave(StudentOne studentOne);
	
	public void saveOrUpdate(StudentOne studentOne);
	
	void deleteStudentByCode(String ssn);
	
	List<StudentOne> findAllStudents();

	StudentOne findStudentByCode(String ssn);

}
