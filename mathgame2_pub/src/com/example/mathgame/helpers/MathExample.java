package com.example.mathgame.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MathExample {

	public static final Integer maxCountValue = 18;

	public static void main(String[] args) {
		MathExample e = new MathExample();

		for (int i = 0; i < 100; i++) {
			e.init2();
			System.out.println(e.toString() + "\t" + e.resultCorrect);
		}
	}

	public enum Operator {
		// PLUS("+"), MINUS("-"), MULTIPLE("*"), DIVIDE("/");
		PLUS("+"), MINUS("-");
		public String value;

		private Operator(String value) {
			this.value = value;
		}
	}

	Random r = new Random();

	public Integer num1;
	public Integer num2;

	public Operator operation;

	public Integer resultCorrect;

	public MathExample() {
		this.init();
	}

	/**
	 * Generate Example the way the result is always [1;maxCountValue] and
	 * num1,num2 [0;result]
	 */
	public void init() {

		int v1 = r.nextInt((MathExample.maxCountValue - 1)) + 1;
		int v2 = r.nextInt(v1);

		operation = this.randomOperator();

		if (operation == Operator.PLUS) {
			resultCorrect = Math.max(v1, v2);
			num1 = Math.min(v1, v2);
			num2 = resultCorrect - num1;
		} else if (operation == Operator.MINUS) {
			resultCorrect = Math.min(v1, v2);
			num1 = Math.max(v1, v2);
			num2 = num1 - resultCorrect;
		}

		// resultCorrect = this.calculate(operation, num1, num2);
		// correct result
	}

	/**
	 * Generate Example the way that the num1, nume is always [1;9]
	 */
	public void init2() {
		int v1 = r.nextInt(9) + 1;
		int v2 = r.nextInt(9) + 1;

		operation = this.randomOperator();

		if (operation == Operator.PLUS) {
			resultCorrect = v1 + v2;
			num1 = v1;
			num2 = v2;
		} else if (operation == Operator.MINUS) {
			resultCorrect = v1 + v2;
			num1 = Math.max(v1, v2);
			num2 = Math.min(v1, v2);
		}

	}

	private final List<Operator> availableOperators = Collections.unmodifiableList(Arrays.asList(Operator.values()));

	public Operator randomOperator() {
		return availableOperators.get(r.nextInt(availableOperators.size()));
	}

	public Integer calculate(Operator operation, Integer num1, Integer num2) {
		Integer result = null;
		switch (operation) {
		case PLUS:
			result = num1 + num2;
			break;
		case MINUS:
			result = num1 - num2;
			break;
		// case MULTIPLE:
		// result = num1 * num2;
		// break;
		// case DIVIDE:
		// result = num1 / num2;
		}

		return result;
	}

	public String toString() {
		return num1.toString() + " " + operation.value + " " + num2.toString() + " = ?";
	}
}
