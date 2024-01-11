package com.qsp.mtm.bi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.qsp.mtm.bi.model.Student;
import com.qsp.mtm.bi.model.Subject;

public class Controller {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgadmin");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

//	1.Insert Data
//	1.1.Insert Student And Subject
	public boolean addStudentAndSubject(Student student, ArrayList<Subject> subjects) {

		if (student != null) {

			if (subjects.isEmpty()) {
//				Insert Only Student Data
				entityTransaction.begin();
				entityManager.persist(student);
				entityTransaction.commit();
				return true;
			}
//			Insert Student Along With Subjects
			entityTransaction.begin();
			entityManager.persist(student);
			for (Subject subject : subjects) {
				entityManager.persist(subject);
			}
			entityTransaction.commit();
			return true;
		}
		return false;
	}

//	1.2.Insert Only Student
	public boolean addStudent(Student student) {

		if (student != null) {
			entityTransaction.begin();
			entityManager.persist(student);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

//	1.3.Insert Subjects For Existing Student
	public boolean addSubject(Student student, ArrayList<Subject> subjects) {

		if (subjects.isEmpty()) {
			return false;
		}
		entityTransaction.begin();
		entityManager.merge(student);
		for (Subject subject : subjects) {
			entityManager.persist(subject);
			entityTransaction.commit();
		}
		return true;
	}

//	2.Fetch Data 
	public Student findStudent(int studentIdToFind) {

		return entityManager.find(Student.class, studentIdToFind);
	}
	public Subject findSubject(int subjectId) {

		return entityManager.find(Subject.class, subjectId);
	}
	public List<Subject> listOfSubjectsOptedByStudent(int studentId) {
		
		return findStudent(studentId).getSubjects();
	}
	public List<Student> listOfStudentOptedSubject(int subjectId) {
		
		return findSubject(subjectId).getStudents();
	}
	
//	4.Delete Data
//	4.1.Delete Subject Data
	public void removeSubject() {

		
	}

//	4.2.Delete Student Data
	public boolean removeStudent(Student studentToRemove) {

		if (studentToRemove != null) {
			entityTransaction.begin();
			entityManager.remove(studentToRemove);
			entityTransaction.commit();
			return true;
		}
	return false;
	}

}
