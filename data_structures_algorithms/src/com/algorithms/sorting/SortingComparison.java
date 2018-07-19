package com.algorithms.sorting;

import java.text.DecimalFormat;
import java.util.Random;

public class SortingComparison {

	public static void main(String[] args) {
//		int[] countArray = {500, 1000, 2000, 4000, 5000, 10000, 20000, 30000, 40000, 50000};
		int[] countArray = {50, 100, 500, 1000, 2000, 4000, 5000, 10000, 15000};
		int numberOfSamples = 1;
		long[][] finalTime = new long[5][countArray.length];
		
		for(int k=0; k<countArray.length; k++) {
			int count = countArray[k];
			int n = 0;
			long[][] time = new long[5][numberOfSamples];
			while(n < numberOfSamples) {
				long[] timeCalculated = callSortAlgorithmsOnReverselySortedArray(count);
				for(int i=0; i<timeCalculated.length; i++) {
					time[i][n] = timeCalculated[i];
				}
				n++;
			}
			for(int p=0; p<time.length; p++) {
				long sum = 0;
				for(int q=0; q<time[p].length; q++) {
					sum += time[p][q];
				}
				finalTime[p][k] = sum / numberOfSamples;
			}
		}
		DecimalFormat df=new DecimalFormat("0.00");
		for(int i=0; i<finalTime.length; i++) {
			for(int j=0;j<finalTime[i].length; j++) {
				try {
					String format = df.format(finalTime[i][j]/1000000.0); 
					double finalValue = (Double)df.parse(format);
					System.out.print(finalValue + " ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		for(int i=0; i<finalTime[0].length; i++) {
			System.out.print(countArray[i] + ";");
			for(int j=0;j<finalTime.length; j++) {
				try {
					String format = df.format(finalTime[j][i]/1000000.0); 
					double finalValue = (Double)df.parse(format);
					System.out.print(finalValue + ";");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
	}

	public static long[] callSortAlgorithms(int count) {
		Integer[] a = new Integer[count];
		Random r = new Random();
		for(int i=0; i<count; i++) {
			a[i] = r.nextInt(count);
		}
		long[] time = new long[5];

		Integer b[] = a.clone();
		Integer c[] = a.clone();
		Integer d[] = a.clone();
		Integer e[] = a.clone();

		time[2] = callQuickSort(a);
		time[0] = callInsertionSort(b);
		time[1] = callMergeSort(c);
		time[3] = callQuickWithMedianSort(d);
		time[4] = callQuickInsertionWithMedianSort(e);
		
		return time;
	}

	public static long[] callSortAlgorithmsOnAlreadySorted(int count) {
		Integer[] a = new Integer[count];
		for(int i=0; i<count; i++) {
			a[i] = i;
		}
		long[] time = new long[5];

		Integer b[] = a.clone();
		Integer c[] = a.clone();
		Integer d[] = a.clone();
		Integer e[] = a.clone();

		time[2] = callQuickSort(a);
		time[0] = callInsertionSort(b);
		time[1] = callMergeSort(c);
		time[3] = callQuickWithMedianSort(d);
		time[4] = callQuickInsertionWithMedianSort(e);
		
		return time;
	}

	public static long[] callSortAlgorithmsOnReverselySortedArray(int count) {
		Integer[] a = new Integer[count];
		for(int i=count-1; i>=0; i--) {
			a[i] = i;
		}
		long[] time = new long[5];

		Integer b[] = a.clone();
		Integer c[] = a.clone();
		Integer d[] = a.clone();
		Integer e[] = a.clone();

		time[2] = callQuickSort(a);
		time[0] = callInsertionSort(b);
		time[1] = callMergeSort(c);
		time[3] = callQuickWithMedianSort(d);
		time[4] = callQuickInsertionWithMedianSort(e);
		
		return time;
	}

	private static long callQuickInsertionWithMedianSort(Integer[] a) {
		QuickInsertionSort<Integer> quickInsertionSortMedian = new QuickInsertionSort<>(a, true);
		long startTime = System.nanoTime();
		quickInsertionSortMedian.sort();
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}

	private static long callQuickWithMedianSort(Integer[] a) {
		QuickInsertionSort<Integer> quickSortMedian = new QuickInsertionSort<>(a, false);
		long startTime = System.nanoTime();
		quickSortMedian.sort();
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}

	private static long callQuickSort(Integer[] a) {
		QuickSort<Integer> quickSort = new QuickSort<>(a);
		long startTime = System.nanoTime();
		quickSort.sort();
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}

	private static long callMergeSort(Integer[] a) {
		MergeSort<Integer> mergeSort = new MergeSort<>(a);
		long startTime = System.nanoTime();
		mergeSort.sort();
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}

	private static long callInsertionSort(Integer[] a) {
		InsertionSort<Integer> insertionSort = new InsertionSort<>(a);
		long startTime = System.nanoTime();
		insertionSort.sort();
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}

}
