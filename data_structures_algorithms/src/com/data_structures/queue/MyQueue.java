package com.data_structures.queue;

public class MyQueue {

	int a[];
	int r;
	int f;

	public MyQueue() {
		super();
		initializeQueue(11);
	}

	public MyQueue(int size) {
		super();
		if(size <= 0) {
			System.out.println("Size should be greater than 0");
			return;
		}
		initializeQueue(size + 1);
	}

	private void initializeQueue(int size) {
		r = 0;
		f = 0;
		a = new int[size];
	}
	
	public void enqueue(int value) {
		if(a == null || size() == a.length - 1) {
			System.out.println("Queue is full");
			return;
		}
		a[r] = value;
		r = (r+1) % a.length;
	}
	
	public int dequeue() {
		if(isEmpty()) {
			System.out.println("Queue is empty");
			return -1;
		}
		int value = a[f];
		f = (f+1) % a.length;
		return value;
	}
	
	public int size() {
		int size = 0;
		if(a != null) {
			if(r >= f) {
				size = r - f;
			} else {
				size = a.length + r - f;
			}
		}
		return size;
	}
	
	public boolean isEmpty() {
		return r == f;
	}
	
	public void display() {

		if(a != null) {
			System.out.print("Queue");
			for(int i=0; i<a.length; i++) {
				if(f == i) {
					System.out.print("f");
				}
				if(r == i) {
					System.out.print("r");
				}
				System.out.print(a[i] + " ");
			}
			System.out.println();
			System.out.println("Size: " + size());
		}
	}
	
	public void displayQueue() {

		if(a != null) {
			System.out.print("Queue");
			for(int i=f; i<r; i=(i+1)%a.length) {
				if(f == i) {
					System.out.print("f");
				}
				if(r == i) {
					System.out.print("r");
				}
				System.out.print(a[i] + " ");
			}
			System.out.println();
			System.out.println("Size: " + size());
		}
	}
	
	public static void main(String[] args) {
		MyQueue queue = new MyQueue(5);

		queue.display();
		queue.enqueue(1);
		queue.display();
		queue.enqueue(2);
		queue.display();
		queue.enqueue(3);
		queue.display();
		queue.enqueue(4);
		queue.display();
		queue.enqueue(5);
		queue.display();
		/*queue.remove();
		queue.display();
		queue.insert(6);
		queue.display();*/
		
		
	}
}
