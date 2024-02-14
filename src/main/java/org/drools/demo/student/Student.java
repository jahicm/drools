package org.drools.demo.student;

import org.springframework.stereotype.Component;

@Component
public class Student {
	
	private String name;
	private String surname;
	private int score;
	private boolean test;
	private int discountBonus;
	
	
	public int getDiscountBonus() {
		return discountBonus;
	}
	public void setDiscountBonus(int discountBonus) {
		this.discountBonus = discountBonus;
	}
	public boolean isTest() {
		return test;
	}
	public void setTest(boolean test) {
		this.test = test;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
