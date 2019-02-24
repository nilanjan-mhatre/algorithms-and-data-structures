package com.algorithms.sorting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class MergeSort<T extends Comparable<T>> {

	private static final String SPACE = " ";
	T[] a;

	public MergeSort(T[] a) {
		super();
		this.a = a;
	}

	public T[] sort() {
		if(a != null) {
			mergeSort(a, 0, a.length - 1);
		}
		return a;
	}

	private void mergeSort(T[] a, int start, int end) {

		if(end > start) {
			int pivot = (start + end)/2;
			mergeSort(a, start, pivot);
			mergeSort(a, pivot + 1, end);
			merge(a, start, pivot, end);
		}
	}

	private void merge(T[] a, int start, int pivot, int end) {
		Object[] temp = new Object[end - start + 1];
		int c = 0;

		int i = start;
		int j = pivot + 1;

		while(i<=pivot && j<=end) {
			if(i<=pivot && a[i].compareTo(a[j]) <= 0) {
				temp[c++] = a[i];
				i++;
			} else if(j<=end) {
				temp[c++] = a[j];
				j++;
			}
		}
		while(i<=pivot) {
			temp[c++] = a[i++];
		}
		while(j<=end) {
			temp[c++] = a[j++];
		}
		for(c=0; c<temp.length; c++) {
			a[start + c] = (T) temp[c];
		}
	}
	private void displayInFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("merge_sort.txt", "UTF-8");
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
		MergeSort<Integer> mergeSort = new MergeSort<>(a);
		mergeSort.displayInFile();
		long startTime = System.nanoTime();
		mergeSort.sort();
		long endTime = System.nanoTime();
		
		System.out.println(endTime - startTime);
		mergeSort.displayInFile();
		
	}
}