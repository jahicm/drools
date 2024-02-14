package org.drools.demo.student;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DataCollection {
	
	List<Student> list;

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}
	
}
