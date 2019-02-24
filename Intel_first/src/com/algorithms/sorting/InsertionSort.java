package com.algorithms.sorting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class InsertionSort<T extends Comparable<T>> {

	private static final String SPACE = " ";
	T a[];
	
	public InsertionSort(T[] a) {
		super();
		this.a = a;
	}

	public T[] sort() {
		if(a != null) {
			insertionSort();
		}
		return a;
	}

	private void insertionSort() {
		if(a != null) {
			for(int i=0; i<a.length-1; i++) {
				int j = i+1;
				T temp = null;
				while(j>0 && a[j].compareTo(a[j-1]) < 0) {
					temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
					j--;
				}
			}
		}
	}

	public void setA(T[] a) {
		this.a = a;
	}

	public void displayInFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("insertion_sort.txt", "UTF-8");
			for(int i=0; i<a.length; i++) {
				output.print(a[i].toString());
				output.print(SPACE);
			}
		} catch (IOException e) {
			e.printStackTrace();	
		} finally {
			if(output != null) {
				output.close();
			}
		}
	}

	public static void main(String[] args) {

		int count = 100;
		Integer[] a = new Integer[count];
		Random r = new Random();
		for(int i=0; i<count; i++) {
			a[i] = r.nextInt(count);
		}

		InsertionSort<Integer> insertionSort = new InsertionSort<>(a);
		insertionSort.displayInFile();
		long startTime = System.nanoTime();
		insertionSort.sort();
		long endTime = System.nanoTime();
		
		System.out.println(endTime - startTime);
		insertionSort.displayInFile();
	}
}
