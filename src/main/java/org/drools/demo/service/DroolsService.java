package org.drools.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.drools.demo.student.DataCollection;
import org.drools.demo.student.Student;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.RuleFlowGroup;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

	private Student student;
	private KieContainer kieContainer;
	private KieSession kieSession;
	private FactHandle factHandle;
	private DataCollection dataCollection;

	public DroolsService(KieContainer kieContainer, DataCollection dataCollection) {
		System.out.println("kieContainer :" + kieContainer);
		this.kieContainer = kieContainer;
		this.dataCollection = dataCollection;
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

	public void dispose() {
		kieSession.dispose();
	}

	public void setStudent(Student student) {

		factHandle = kieSession.insert(student);
	}
}
