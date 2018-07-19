package com.algorithms.graph;

import java.util.*;

class Djkshtr {
	private int g[][];

	public void createGraph() {
		Scanner src = new Scanner(System.in);
		System.out.print("\nEnter number of vertices : ");
		int n = src.nextInt();
		g = new int[n][n];
		for (int i = 0; i < g.length; i++)
			Arrays.fill(g[i], 0);
		System.out.print("\nEnter number of edges : ");
		int e = src.nextInt(), a, b;
		for (int i = 0; i < e; i++) {
			System.out.print("\nEnter start and end vertex : ");
			a = src.nextInt();
			b = src.nextInt();
			System.out.print("\nEnter weight : ");
			g[a - 1][b - 1] = g[b - 1][a - 1] = src.nextInt();
		}
	}

	public void djkshtrAlgo() {
		Scanner src = new Scanner(System.in);
		int d[] = new int[g.length], p[] = new int[g.length], visited[] = new int[g.length], current, dest, source;
		Arrays.fill(d, 32767);
		Arrays.fill(visited, 0);
		for (int i = 0; i < g.length; i++) {
			d[i] = 32767;
			p[i] = -1;
			visited[i] = 0;
		}
		System.out.print("\nEnter source vertex : ");
		current = source = src.nextInt() - 1;
		d[current] = 0;
		visited[current] = 1;
		while (true) {
			int dc = d[current];
			for (int i = 0; i < g.length; i++)
				if (g[current][i] != 0 && visited[i] != 1 && g[current][i] + dc < d[i]) {
					d[i] = g[current][i] + dc;
					p[i] = current;
				}
			int min = 32767;
			for (int i = 0; i < g.length; i++)
				if (visited[i] != 1 && d[i] < min) {
					min = d[i];
					current = i;
				}
			if (min == 32767) {
				break;
			}
			visited[current] = 1;
		}
		for (int i = 0; i < g.length; i++) {
			int temp = i;
			System.out.println("\nTovertex " + i + "\nThe shortest distance = " + d[i]);
			System.out.println("The shortest path is : ");
			while (temp != source) {
				System.out.println((temp + 1) + " from " + (p[temp] + 1));
				temp = p[temp];
			}
			System.out.println("\n");
		}
	}
}

class DjkshtrExp {
	public static void main(String args[]) {
		Djkshtr obj = new Djkshtr();
		obj.createGraph();
		obj.djkshtrAlgo();
	}
}