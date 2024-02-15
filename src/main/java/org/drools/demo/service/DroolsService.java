package org.drools.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.drools.demo.student.DataCollection;
import org.drools.demo.student.Student;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

	private KieContainer kieContainer;
	private KieSession kieSession;
	
	public DroolsService(KieContainer kieContainer, DataCollection dataCollection) {
		System.out.println("kieContainer :" + kieContainer);
		this.kieContainer = kieContainer;
	}

	public void createSession() {
		kieSession = kieContainer.newKieSession();

	}

	public void executeRules(String type)
    {   
    	switch (type)
    	{
    		case "low": 
    			kieSession.getAgenda().getAgendaGroup("low").setFocus();
    			break;
    		case "medium":
    			kieSession.getAgenda().getAgendaGroup("medium").setFocus();
    			break;
    		case "high":
    			kieSession.getAgenda().getAgendaGroup("high").setFocus();
    			break;
    		default:
    			kieSession.getAgenda().getAgendaGroup("low").setFocus();
    			break;
    	}
    	kieSession.fireAllRules();
    }

	public List<Student> getStudent() {
		Collection<Student> filteredFacts;
		ObjectFilter filter = object -> object instanceof Student;
		filteredFacts = (Collection<Student>) kieSession.getObjects(filter);

		return new ArrayList<>(filteredFacts);
	}
	public List<Student> getHighScoreStudents() {
	
		QueryResults results = kieSession.getQueryResults("StudentsWithHighScore");
		List<Student> studentList = new Vector<>();
		for ( QueryResultsRow row : results ) {
		    Student student = ( Student ) row.get("$student");
		    studentList.add(student);
		}
		return studentList;
	}
	public List<Student> getLowScoreStudents() {
		
		QueryResults results = kieSession.getQueryResults("StudentsWithLowScore");
		List<Student> studentList = new Vector<>();
		for ( QueryResultsRow row : results ) {
		    Student student = ( Student ) row.get("$student");
		    studentList.add(student);
		}
		return studentList;
	}
	public void dispose() {
		kieSession.dispose();
	}

	public void setStudent(Student student) {

		kieSession.insert(student);
	}
}
