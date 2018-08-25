package io.springmvc.service;

import io.springmvc.dao.DaoStudent;
import io.springmvc.model.StudentOne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional
public class ServiceImplStudent implements ServiceStudent {

	@Autowired
	private DaoStudent dao;
	
	public StudentOne findById(int id) {
		return dao.findById(id);
	}

	public void saveStudent(StudentOne studentOne) {
		dao.studentSave(studentOne);
	}
	
	public void updateStudent(StudentOne studentOne) {
		StudentOne entity = dao.findById(studentOne.getId());
		if(entity!=null){
			entity.setName(studentOne.getName());
			entity.setEnteringDate(studentOne.getEnteringDate());
			entity.setNationality(studentOne.getNationality());
			entity.setCode(studentOne.getCode());
			//dao.saveOrUpdate(student);
		}
	}

	public void deleteStudentByCode(String ssn) {
		dao.deleteStudentByCode(ssn);
	}
	
	public List<StudentOne> findAllStudents() {
		return dao.findAllStudents();
	}

	public StudentOne findStudentByCode(String ssn) {
		return dao.findStudentByCode(ssn);
	}

	public boolean isStudentCodeUnique(Integer id, String ssn) {
		StudentOne studentOne = findStudentByCode(ssn);
		return ( studentOne == null || ((id != null) && (studentOne.getId() == id)));
	}
	
}
