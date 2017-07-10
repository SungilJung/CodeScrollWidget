package com.codescroll.widget.progress;

public class StepModel {
	private String stepName;
	private String subName;
	
	public StepModel(String stepName) {
		this(stepName, null);
	}
	
	public StepModel(String stepName, String subName) {
		this.stepName = stepName;
		this.subName = subName;
	}
	
	public String getStepName() {
		return stepName;
	}
	public String getSubName() {
		return subName;
	}
	
	
}
