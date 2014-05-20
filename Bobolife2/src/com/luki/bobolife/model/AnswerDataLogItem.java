package com.luki.bobolife.model;

import java.io.Serializable;

public class AnswerDataLogItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String day;

	private Long answerTeaOrCoffeeDate;
	private String answerTeaOrCoffeeValue;
	private Long answerWork1Date;
	private String answerWork1Value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Long getAnswerTeaOrCoffeeDate() {
		return answerTeaOrCoffeeDate;
	}

	public void setAnswerTeaOrCoffeeDate(Long answerTeaOrCoffeeDate) {
		this.answerTeaOrCoffeeDate = answerTeaOrCoffeeDate;
	}

	public String getAnswerTeaOrCoffeeValue() {
		return answerTeaOrCoffeeValue;
	}

	public void setAnswerTeaOrCoffeeValue(String answerTeaOrCoffeeValue) {
		this.answerTeaOrCoffeeValue = answerTeaOrCoffeeValue;
	}

	public Long getAnswerWork1Date() {
		return answerWork1Date;
	}

	public void setAnswerWork1Date(Long answerWork1Date) {
		this.answerWork1Date = answerWork1Date;
	}

	public String getAnswerWork1Value() {
		return answerWork1Value;
	}

	public void setAnswerWork1Value(String answerWork1Value) {
		this.answerWork1Value = answerWork1Value;
	}

	@Override
	public String toString() {
		return this.id + "_" + this.day + "[" + this.answerTeaOrCoffeeValue + "|" + this.answerWork1Value + "]{"
				+ this.answerTeaOrCoffeeDate + "|" + this.answerWork1Date + "}";
	}
}
