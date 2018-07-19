package com.algorithms.sorting;

import java.util.Scanner;

public class HeapSortInPlace {

	private int a[];

	public HeapSortInPlace(int a[]) {
		this.a = a;
	}

	public void adjust(int i, int n) {
		int x = a[i], j = i * 2 + 1;
		while (j <= n) {
			if (j < n && a[j] < a[j + 1]) {
				j++;
			}
			if (x > a[j]) {
				break;
			}
			a[(j - 1) / 2] = a[j];
			j = j * 2 + 1;
		}
		a[(j - 1) / 2] = x;
	}

	public void sort() {
		int n = a.length - 1;
		while (n != 0) {
			for (int i = (a.length - n) / 2; i >= 0; i--) {
				adjust(i, n);
			}
			System.out.println(toString());
			int t = a[0];
			a[0] = a[n];
			a[n] = t;
			n--;

			System.out.println(toString());
		}
	}

	public String toString() {
		String str = "";
		System.out.println("Array" + a.length);
		for (int i = 0; i <= a.length - 1; i++) {
			str += a[i] + " ";
		}
		return str;
	}

	public static void main(String args[]) {
		Scanner src = new Scanner(System.in);
		System.out.print("\nEnter number of elements : ");
		int a[] = new int[src.nextInt()];
		System.out.print("\nEnter elements : ");
		for (int i = 0; i <= a.length - 1; i++) {
			a[i] = src.nextInt();
		}
		src.close();
		HeapSortInPlace obj = new HeapSortInPlace(a);
		System.out.print("Array is : " + obj);
		obj.sort();
		System.out.print("\nSorted array is : " + obj);
	}
}
