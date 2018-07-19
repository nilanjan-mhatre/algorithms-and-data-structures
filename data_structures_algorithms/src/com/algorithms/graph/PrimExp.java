package com.algorithms.graph;

import java.util.*;
class Prim
{
	private int g[][];

	public void createGraph()
	{
		Scanner src=new Scanner(System.in);
		System.out.print("\nEnter number of vertices : ");
		int n=src.nextInt();
		g=new int[n][n];
		for(int i=0;i<g.length;i++)
			Arrays.fill(g[i], 0);
		System.out.print("\nEnter number of edges : ");
		int e=src.nextInt(),a,b;
		for(int i=0;i<e;i++)
		{
			System.out.print("\nEnter start and end vertex : ");
			a=src.nextInt();
			b=src.nextInt();
			System.out.print("\nEnter weight : ");
			g[a-1][b-1]=g[b-1][a-1]=src.nextInt();
		}
	}
	public void primAlgo() {
		Scanner src=new Scanner(System.in);
		int d[]=new int[g.length],p[]=new int[g.length],visited[]=new int[g.length],c,current;
		for(int i=0;i<g.length;i++)
		{
			d[i]=32767;
			p[i]=-1;
			visited[i]=0;
		}
		System.out.print("\nEnter starting vertex : ");
		current=src.nextInt()-1;
		d[current]=0;
		visited[current]=1;
		c=0;
		while(c!=g.length-1)
		{
			for(int i=0;i<g.length;i++)
				if(g[current][i]!=0 && visited[i]!=1 && g[current][i]<d[i])
				{
					d[i]=g[current][i];
					p[i]=current;
				}
			int min=32767;
			for(int i=0;i<g.length;i++)
				if(visited[i]!=1 && d[i]<min)
				{
					min=d[i];
					current=i;
				}
			visited[current]=1;
			c++;
		}
		int mincost=0;
		for(int i=0;i<g.length;i++)
			mincost=mincost+d[i];
		System.out.print("\n\nMinimum cost of spanning tree = "+mincost);
		System.out.print("\nThe minimum cost spanning tree is :-\n\n");
		for(int i=0;i<g.length;i++)
			if(p[i]!=-1)
				System.out.println("From "+(i+1)+" to "+(p[i]+1)+" with weight = "+d[i]);
	}
}
class PrimExp
{
	public static void main(String args[])
	{
		Prim obj=new Prim();
		obj.createGraph();
		obj.primAlgo();
	}
}