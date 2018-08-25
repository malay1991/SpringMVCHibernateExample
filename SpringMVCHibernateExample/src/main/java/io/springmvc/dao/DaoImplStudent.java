package io.springmvc.dao;

import io.springmvc.model.StudentOne;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class DaoImplStudent extends DaoAbstract<Integer, StudentOne> implements DaoStudent {

	public StudentOne findById(int id) {
		return byKeyGet(id);
	}

	public void studentSave(StudentOne studentOne) {
		persist(studentOne);
	}
	
	public void saveOrUpdate(StudentOne studentOne){
		super.saveOrUpdate(studentOne);
	}
	
	public void deleteStudentByCode(String code) {
		Query query = getSession().createSQLQuery("delete from StudentOne where code = :code");
		query.setString("code", code);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<StudentOne> findAllStudents() {
		Criteria criteria = createEntityCriteria();
		return (List<StudentOne>) criteria.list();
	}

	public StudentOne findStudentByCode(String code) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
		return (StudentOne) criteria.uniqueResult();
	}
}
