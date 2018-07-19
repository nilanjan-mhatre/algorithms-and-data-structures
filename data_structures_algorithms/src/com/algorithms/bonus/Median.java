package com.algorithms.bonus;

import java.util.ArrayList;
import java.util.List;

import com.data_structures.queue.MyStack;


public class Median {

	int median;
	int medianPosition;
	MyStack firstPosition;
	MyStack secondPosition;
	List<Integer> stream;

	public Median() {
		super();
		stream = new ArrayList<>();
		median = -1;
		firstPosition = new MyStack(100);
		secondPosition = new MyStack(100);
	}

	public void inputNumber(int n) {
		stream.add(n);
		int size = stream.size();
		boolean isEven = stream.size() % 2 == 0;

		if(median < 0) {
			median = n;
			medianPosition = 0;
		} else if(firstPosition .isEmpty()) {
			int firstNumber;
			int secondNumber;
			if(n<=median) {
				firstNumber = 1;
				secondNumber = 0;
			} else {
				secondNumber = 1;
				firstNumber = 0;
			}
			firstPosition.push(firstNumber);
			secondPosition.push(secondNumber);
			median = (firstNumber + secondNumber) / 2;
			medianPosition = -1;
		} else if(!isEven) {
			int firstNumber = stream.get(firstPosition.topElement());
			int secondNumber = stream.get(secondPosition.topElement());
			if(n > secondNumber) {
				median = secondNumber;
				medianPosition = secondPosition.topElement();
			} else if(n < secondNumber && n > firstNumber) {
				median = n;
				medianPosition = size - 1;
			} else if(n < firstNumber) {
				median = firstNumber;
				medianPosition = firstPosition.topElement();
			}
		} else {
			int firstNumber = stream.get(firstPosition.topElement());
			int secondNumber = stream.get(secondPosition.topElement());
			medianPosition = -1;
			if(n < firstNumber) {
				median = (firstNumber + median) / 2;
				secondPosition.push(median);
			} else if(n > firstNumber && n < median) {
				median = (n + median) / 2;
				firstPosition.push(n);
				secondPosition.push(median);
			} else if(n > median && n < secondNumber) {
				median = (n + median) / 2;
				firstPosition.push(median);
				secondPosition.push(n);
			} else if(n > secondNumber) {
				median = (secondNumber + median) / 2;
				medianPosition = firstPosition.topElement();
			}
		}
	}

	public void inputNumberArray(int[] arr) {
		
	}

	public int getMedian() {
		return median;
	}

	public static void main(String[] args) {

	}
}
