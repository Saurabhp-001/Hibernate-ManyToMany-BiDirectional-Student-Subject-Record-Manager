package com.qsp.mtm.bi.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.qsp.mtm.bi.controller.Controller;
import com.qsp.mtm.bi.model.Student;
import com.qsp.mtm.bi.model.Subject;

public class View {

	static Scanner sc = new Scanner(System.in);
	static Controller controller = new Controller();
	static Student student = new Student();
	static Subject subject = new Subject();
	static ArrayList<Subject> subjects = new ArrayList<Subject>();
	static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args) {

//		Entry Control For Menu Driven
		do {

			System.out.println("<><><> Student - Subject Manager <><><>");
			System.out.println("\n==================");
			System.out.println("| 1.Insert Data  |");
			System.out.println("| 2.Fetch Data   |");
			System.out.println("| 3.Update Data  |");
			System.out.println("| 4.Delete Data  |");
			System.out.println("| 0.Exit         |");
			System.out.println("==================");
			System.out.print("Enter Number To Select Option : ");
			int userChoice = sc.nextInt();
			sc.nextLine();

			switch (userChoice) {
//		1.Insert Data
			case 1:

				System.out.println("\n-----------------------------------------");
				System.out.println("|             Insert Data               |");
				System.out.println("-----------------------------------------");
				System.out.println("| 1.Insert Student And Subject          |");
//				System.out.println("| 2.Insert Student                      |");
				System.out.println("| 3.Insert Subject For Existing Student |");
				System.out.println("-----------------------------------------");
				System.out.print("Enter Number To Select Option : ");
				int userInsertChoice = sc.nextInt();
				sc.nextLine();

//				Sub Switch For Insert
				switch (userInsertChoice) {
//				1.1.Insert Student And Subject Data	
				case 1:

//					Input Student Details
					System.out.println("\n--- Insert Student Data ---");
					System.out.print("\nEnter Student Id : ");
					student.setStudentId(sc.nextInt());
					sc.nextLine();
					System.out.print("Enter Student Name : ");
					student.setStudentName(sc.nextLine());
					System.out.print("Enter Student Email Id : ");
					student.setStudentEmail(sc.nextLine());
					System.out.print("Enter Student Mobile Number : ");
					student.setStudentMobile(sc.nextLong());

//					Subject Has List Of Students
					students.add(student);
					subject.setStudents(students);
					
					System.out.println("\nWant To Add Subjects For " + student.getStudentName() + " ?");
					System.out.println("-----------------------------------------");
					System.out.println("1.Yes \n2.No");
					int addSubjectChoice = sc.nextInt();
					sc.nextLine();

//					1.Add Subjects To Student
					if (addSubjectChoice == 1) {

//						Add Subjects
						boolean addMore = false;
						do {
							System.out.println("\n--- Insert Subject Data ---");
							System.out.print("\nEnter Subject Id : ");
							subject.setSubjectId(sc.nextInt());
							sc.nextLine();
							System.out.print("Enter Subject Name : ");
							subject.setSubjectName(sc.nextLine());
							System.out.print("Enter Subject Description : ");
							subject.setSubjectDesc(sc.nextLine());
							System.out.print("Enter Subject Trainer : ");
							subject.setSubjectTrainer(sc.nextLine());
//							Add Subject To List Of Subjects
							subjects.add(subject);

//							Want To Add More Subjects ?
							System.out.println("\n---------------------");
							System.out.println("\nAdd More Subjects...?");
							System.out.println("1.Yes \n2.No");
							System.out.println("---------------------");
							System.out.print("Enter Number To Select : ");
							int insertSubjectChoice = sc.nextInt();
							sc.nextLine();
							if (insertSubjectChoice == 1) {
								addMore = true;
							} else if (insertSubjectChoice == 2) {
								addMore = false;
							}

						} while (addMore);
//						Set List Of Subjects To Student
						student.setSubjects(subjects);
					}

//					2.Don't Add Subjects To Students
					else if (addSubjectChoice == 2) {

						student.setSubjects(null);

					}

//					Call Add Method
					if (controller.addStudentAndSubject(student, subjects)) {
						System.out.println("\nData Inserted");
					}

					else {
						System.err.println("\nData Insertion Failed...!");
					}
					break;

////			1.2.Insert Student Data
//				case 2:
//
//					System.out.println("\n--- Insert Student Data ---");
//					System.out.print("\nEnter Student Id : ");
//					student.setStudentId(sc.nextInt());
//					sc.nextLine();
//					System.out.print("Enter Student Name : ");
//					student.setStudentName(sc.nextLine());
//					System.out.print("Enter Student Email Id : ");
//					student.setStudentEmail(sc.nextLine());
//					System.out.print("Enter Student Mobile Number : ");
//					student.setStudentMobile(sc.nextLong());
//					if (controller.addStudent(student)) {
//						System.out.println("\nData Inserted");
//					} else {
//						System.err.println("\nData Insertion Failed...!");
//					}
//					break;

//				1.3.Insert Subject Data To Existing Student
				case 3:

					System.out.println("\n--- Insert Subject For Existing Student ---");
					System.out.println("\nEnter Student Id To Insert Subject : ");
					int studentIdForSubject = sc.nextInt();
					sc.nextLine();

					Student studentForSubject = controller.findStudent(studentIdForSubject);
					if (studentForSubject != null) {
//						Do While To Add Multiple Subjects
						boolean addMoreSubject = false;
						do {
							System.out.println("\n--- Insert Subject Data ---");
							System.out.print("\nEnter Subject Id : ");
							subject.setSubjectId(sc.nextInt());
							sc.nextLine();
							System.out.print("Enter Subject Name : ");
							subject.setSubjectName(sc.nextLine());
							System.out.print("Enter Subject Description : ");
							subject.setSubjectDesc(sc.nextLine());
							System.out.print("Enter Subject Trainer : ");
							subject.setSubjectTrainer(sc.nextLine());
//							Add Subject To List Of Subjects
							subjects.add(subject);
//							Want To Add More Subjects ?
							System.out.println("\n------------------");
							System.out.println("Add More Subjects ?");
							System.out.println("1.Yes \n2.No");
							System.out.println("------------------");
							System.out.println("Enter Number To Select : ");
							int insertSubjectChoice = sc.nextInt();
							sc.nextLine();

							if (insertSubjectChoice == 1) {
								addMoreSubject = true;
							} else if (insertSubjectChoice == 2) {
								addMoreSubject = false;
							}

						} while (addMoreSubject);

						studentForSubject.setSubjects(subjects);

						if (controller.addSubject(studentForSubject, subjects)) {
							System.out.println("Data Inserted...");
						} else {
							System.out.println("Data Insertion Failed...!");
						}
					}
					System.err.println("\nStudent Id- " + studentIdForSubject + " Not Found...!");
					break;

				default:
					System.err.println("Invalid Choice...!");
					break;
				}
				break;
//		End of Case 1: Insert Data

//		2.Fetch Data
			case 2:

				System.out.println("\n--- Fetch Data ---");
				System.out.println("\nEnter Student Id : ");
				int studentIdToFind = sc.nextInt();
				sc.nextLine();

				Student foundStudent = controller.findStudent(studentIdToFind);
				if (foundStudent != null) {

					System.out.println("\n======== Student Data ===========");
					System.out.println("\nStudent Id : " + foundStudent.getStudentId());
					System.out.println("Student Name : " + foundStudent.getStudentName());
					System.out.println("Student Email Id : " + foundStudent.getStudentEmail());
					System.out.println("Student Mobile Number : " + foundStudent.getStudentMobile());

					List<Subject> foundSubjects = foundStudent.getSubjects();

					System.out.println("======== Subject Data ===========");
					for (Subject subject : foundSubjects) {
						System.out.println("\nSubject Id : " + subject.getSubjectId());
						System.out.println("Subject Name : " + subject.getSubjectName());
						System.out.println("Subject Description : " + subject.getSubjectDesc());
						System.out.println("Subject Trainer : " + subject.getSubjectTrainer());
					}
					System.out.println("=================================");
				} else {
					System.err.println("Student Id : " + studentIdToFind + " Not Found...!");
				}
				break;

//		3.Update Data
			case 3:

				break;

//		4.Delete Data
			case 4:

				System.out.println("Enter Student Id To Deleta :");
				int studentIdToRemove = sc.nextInt();
				sc.nextLine();
				Student studentToRemove = controller.findStudent(studentIdToRemove);
				
				if (studentToRemove != null) {
					controller.removeStudent(studentToRemove);
				}
				else {
					System.err.println("Student Not Found !");
				}
				
				break;

			default:
				break;
			}

		} while (true);
	}

}
