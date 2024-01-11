package com.qsp.mtm.bi.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.qsp.mtm.bi.model.Student;
import com.qsp.mtm.bi.model.Subject;

public class StudentController {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgadmin");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void name() {
		
	}
	
	public List<Subject> listOfSubjectsOptedByStudent(int studentId) {
		
		Student student = entityManager.find(Student.class, studentId);
		return student.getSubjects();
	}
}
