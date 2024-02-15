package org.drools.demo;


import java.util.ArrayList;
import java.util.List;

import org.drools.demo.config.DroolsConfig;
import org.drools.demo.service.DroolsService;
import org.drools.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class Main {
	
	
	public static void main(String[] args) {

		
GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DroolsConfig.class);
		
		DroolsService droolsService = ctx.getBean(DroolsService.class);
		droolsService.createSession();
		
		Student student1 = new Student();
		student1.setName("Student1");
		student1.setSurname("Surname1");
		student1.setScore(30);
		
		Student student2 = new Student();
		student2.setName("Student 2");
		student2.setSurname("Surname 2");
		student2.setScore(80);
		
		Student student3 = new Student();
		student3.setName("Student 3");
		student3.setSurname("Surname 3");
		student3.setScore(100);
		
		droolsService.setStudent(student1);
		droolsService.setStudent(student2);
		droolsService.setStudent(student3);

		droolsService.executeRules("high");
		List<Student> students = droolsService.getStudent();
		students.forEach(student->System.out.println("Name:"+student.getName()+"  Surname:"+student.getSurname()+"   Score:"+student.getScore()
		+"   Score:"+student.getDiscountBonus()));
		
		System.out.println("*****Query High Score Students*****");
		List<Student> highScoreStudents = droolsService.getHighScoreStudents();
		
		highScoreStudents.forEach(student->System.out.println("Name:"+student.getName()+"  Surname:"+student.getSurname()+"   Score:"+student.getScore()
		+"   Score:"+student.getDiscountBonus()));
		System.out.println("*****Query Low Score Students*****");
		List<Student> lowScoreStudents = droolsService.getLowScoreStudents();
		
		lowScoreStudents.forEach(student->System.out.println("Name:"+student.getName()+"  Surname:"+student.getSurname()+"   Score:"+student.getScore()
		+"   Score:"+student.getDiscountBonus()));
		
		droolsService.dispose();
		SpringApplication.run(Main.class, args);
	}

}
