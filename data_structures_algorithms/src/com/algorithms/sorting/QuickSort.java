package com.algorithms.sorting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class QuickSort<T extends Comparable<T>> {

	private static final String SPACE = " ";
	T a[];
	
	public QuickSort(T[] a) {
		super();
		this.a = a;
	}

	public T[] sort() {
		if(a != null) {
			quickSort(0, a.length - 1);
		}
		return a;
	}

	private void quickSort(int start, int end) {
		if(a != null && end>start) {
			T pivot = a[start];
			int i = start;
			int j = end;

			while(i<j) {
				while(i<end && a[i].compareTo(pivot) <= 0) {
					i++;
				}
				while(j>start && a[j].compareTo(pivot) > 0) {
					j--;
				}
				
				if(i<j) {
					T temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
			T temp = a[start];
			a[start] = a[j];
			a[j] = temp;

			quickSort(start, j-1);
			quickSort(j+1, end);
		}
	}

	public void displayInFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("src/com/algorithms/sorting/files/quick_sort.txt", "UTF-8");
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
		/*Integer[] a = new Integer[count];
		Random r = new Random();
		for(int i=0; i<count; i++) {
			a[i] = r.nextInt(count);
		}*/
		Integer[] a = new Integer[count];
		for(int i=0; i<count; i++) {
			a[i] = i;
		}
		QuickSort<Integer> quickSort = new QuickSort<>(a);
		quickSort.displayInFile();
		long startTime = System.nanoTime();
		quickSort.sort();
		long endTime = System.nanoTime();
		
		System.out.println(endTime - startTime);
		quickSort.displayInFile();
	}
}
