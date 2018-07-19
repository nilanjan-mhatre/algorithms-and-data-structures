package com.data_structures.queue;

public class MyStack {

	int a[];
	int top;

	public MyStack() {
		super();
		initializeQueue(11);
	}

	public MyStack(int size) {
		super();
		if(size <= 0) {
			System.out.println("Size should be greater than 0");
			return;
		}
		initializeQueue(size);
	}

	private void initializeQueue(int size) {
		top = -1;
		a = new int[size];
	}
	
	public void push(int value) {

		if(top == a.length - 1) {
			System.out.println("Stack is full");
			return;
		}
		a[++top] = value;
	}
	
	public int pop() {

		if(isEmpty()) {
			System.out.println("Stack is empty");
			return -1;
		}
		return a[top--];
	}
	
	public int topElement() {
		if(isEmpty()) {
			return -1;
		}
		return a[top];
	}
	
	public boolean isEmpty() {

		return top == -1;
	}
	
	public int size() {

		return top + 1;
	}
	
	public void display() {

		System.out.print("Stack: ");
		for(int i = 0; i<=top; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		System.out.println("Size: " + size());
	}
	
	public static void main(String[] args) {
		MyStack stack = new MyStack(5);

		stack.push(1);
		stack.display();
		stack.push(2);
		stack.display();
		stack.push(3);
		stack.display();
		stack.push(4);
		stack.display();
		stack.push(5);
		stack.display();
		stack.pop();
		stack.display();
		stack.push(6);
		stack.display();
	}
}
