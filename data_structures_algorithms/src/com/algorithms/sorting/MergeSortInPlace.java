package com.algorithms.sorting;

public class MergeSortInPlace {

	public static void main(String[] args) {

	}
	
	private int[] mergeSort(int a[], int start, int end) {

		if(end - start <= 1) {
			return a;
		}
		int pivot = (start + end)/2;
		mergeSort(a, start, pivot);
		mergeSort(a, pivot + 1, end);
		merge(a, start, pivot, end);
		return a;
	}

	private void merge(int[] a, int start, int pivot, int end) {
		
	}
}
