package com.algorithms.sorting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class QuickInsertionSort<T extends Comparable<T>> {

	private static final String SPACE = " ";
	T a[];
	boolean useInsertion;
	
	public QuickInsertionSort(T[] a, boolean useInsertion) {
		super();
		this.a = a;
		this.useInsertion = useInsertion;
	}

	public T[] sort() {
		if(a != null) {
			sortRange(0, a.length - 1);
		}
		return a;
	}

	private void sortRange(int start, int end) {
		if(end - start + 1 >= 10 || !useInsertion) {
			quickSort(start, end);
		} else {
			insertionSort(start, end);
		}
	}

	private void quickSort(int start, int end) {
		if(a != null && end>start) {
			chooseAndMovePivot(start, end);

			int i = start + 1;
			int j = end - 1;
			T pivot = a[i];

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
			if(end - start > 2) {
				T temp = a[start + 1];
				a[start + 1] = a[j];
				a[j] = temp;
				sortRange(start, j-1);
				sortRange(j+1, end);
			}


		}
	}

	private void swap(int first, int second) {
		T temp = a[first];
		a[first] = a[second];
		a[second] = temp;
	}

	public int chooseAndMovePivot(int start, int end) {
		int middle = (start + end) / 2;
		T startElement = a[start];
		T middleElement = a[middle];
		T endElement = a[end];

		if(middleElement.compareTo(startElement) < 0 && middleElement.compareTo(endElement) <= 0) {
			a[start] = middleElement;
			if(startElement.compareTo(endElement) < 0) {
				a[middle] = startElement;
			} else {
				a[middle] = endElement;
				a[end] = startElement;
			}
		} else if(endElement.compareTo(startElement) < 0 && endElement.compareTo(middleElement) < 0) {
			a[start] = endElement;
			if(startElement.compareTo(middleElement) < 0) {
				a[middle] = startElement;
				a[end] = middleElement;
			} else {
				a[end] = startElement;
			}
		} else {
			if(endElement.compareTo(middleElement) < 0 && middle != start) {
				a[middle] = endElement;
				a[end] = middleElement;
			}
		}
		
		if(middle != start) {
			swap(middle, start + 1);
		}
		
		return middle;
	}

	private void insertionSort(int start, int end) {
		if(a != null) {
			for(int i=start; i<end; i++) {
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

	public void displayInFile(String filename) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(filename + ".txt", "UTF-8");
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

	public void display() {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i].toString());
			System.out.print(SPACE);
		}
	}

	public static void main(String[] args) {

		int count = 100;
		Integer[] a = new Integer[count];
		Random r = new Random();
		for(int i=0; i<count; i++) {
			a[i] = r.nextInt(count);
		}

		QuickInsertionSort<Integer> quickInsertionSortTrue = new QuickInsertionSort<>(a, true);
		long startTime = System.nanoTime();
		quickInsertionSortTrue.sort();
		long endTime = System.nanoTime();
		System.out.println("QuickSort with median pivot: " + (endTime - startTime));
		quickInsertionSortTrue.displayInFile("quick_median");

		QuickInsertionSort<Integer> quickInsertionSortFalse = new QuickInsertionSort<>(a, true);
		startTime = System.nanoTime();
		quickInsertionSortFalse.sort();
		endTime = System.nanoTime();
		System.out.println("QuickSort with median pivot and Insertion sort: " + (endTime - startTime));
		quickInsertionSortFalse.displayInFile("quick_insertion_median");
	}
}
