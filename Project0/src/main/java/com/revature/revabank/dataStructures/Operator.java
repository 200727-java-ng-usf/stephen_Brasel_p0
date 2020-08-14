package com.revature.revabank.dataStructures;

public enum Operator {
	ADDITION("+"){
		@Override public double execute(double x1, double x2){
			return x1+x2;
		}
	},
	SUBTRACTION("-"){
		@Override public double execute(double x1, double x2){
			return x1 - x2;
		}
	}
	;

	private final String text;

	private Operator(String text){
		this.text = text;
	}

	public abstract double execute(double x1, double x2);

	@Override public String toString(){
		return text;
	}
}
