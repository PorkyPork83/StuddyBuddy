package eu.dtc.studybuddy.entries;

import java.util.ArrayList;

public class Set {
	private String parent;
	private String name;
	private ArrayList<QuestionAndAnswers> qAndA;
	
	public String getParent() {
		return parent;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<QuestionAndAnswers> getQAndA() {
		return qAndA;
	}
	
	public void addQAndA(String question, String answer1, String answer2, String answer3, String answer4, String solution) {
		QuestionAndAnswers qa = new QuestionAndAnswers();
		qa.setQuestion(question);
		qa.setAnswer1(answer1);
		qa.setAnswer2(answer2);
		qa.setAnswer3(answer3);
		qa.setAnswer4(answer4);
		qa.setSolution(solution);
		qAndA.add(qa);
	}
}
