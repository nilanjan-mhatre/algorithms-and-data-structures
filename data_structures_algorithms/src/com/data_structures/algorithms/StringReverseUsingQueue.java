package com.data_structures.algorithms;

import com.data_structures.queue.MyQueue;
import com.data_structures.queue.MyStack;

public class StringReverseUsingQueue {
	
	public static void main(String[] args) {
		MyQueue queue = new MyQueue(5);
		queue.displayQueue();
		queue.enqueue(1);
		queue.displayQueue();
		queue.enqueue(2);
		queue.displayQueue();
		queue.enqueue(3);
		queue.displayQueue();
		queue.enqueue(4);
		queue.displayQueue();
		queue.enqueue(5);
		queue.displayQueue();
		
		int size = queue.size();
		MyStack stack = new MyStack(size);
		int i = 0;
		while(i++ < size) {
			stack.push(queue.dequeue());
		}
		stack.display();
		queue.display();
		queue.displayQueue();
		i = 0;
		while(i++ < size) {
			queue.enqueue(stack.pop());
		}
		queue.display();
		queue.displayQueue();
	}
}
