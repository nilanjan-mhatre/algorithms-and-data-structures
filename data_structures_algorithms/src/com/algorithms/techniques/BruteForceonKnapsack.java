package com.algorithms.techniques;

import java.util.ArrayList;
import java.util.List;

public class BruteForceonKnapsack {

	public static void main(String[] args) {
		BruteForceonKnapsack obj = new BruteForceonKnapsack();
		List<int[]> a = new ArrayList<>();
		int b[][] = {{12, 4}, {10, 6}, {8, 5}, {11, 7}, {14, 3}, {7, 1}, {9, 6}};
		for(int[] t : b) {
			a.add(t);
		}
		List<List<Integer>> allSolutions = obj.findAllSolutions(a, 18);
		List<List<Integer>> maxSolutions = new ArrayList<>();
		for(List<Integer> solution : allSolutions) {
			int benefit = 0;
			for(Integer ben : solution) {
				benefit += ben;
			}
			if(benefit >= 44) {
				maxSolutions.add(solution);
			}
		}
		System.out.println();
	}

	public List<List<Integer>> findAllSolutions(List<int[]> a, int maxWeight) {

		List<List<Integer>> allSolutions = new ArrayList<>();
		for(int i=0; i<a.size(); i++) {
			int[] element = a.get(i);
			if(maxWeight >= element[1]) {
				List<int[]> temp = new ArrayList<>();
				temp.addAll(a);
				temp.remove(i);

				List<List<Integer>> tempSolutions = new ArrayList<>();
				tempSolutions.addAll(findAllSolutions(temp, maxWeight - element[1]));
				for(List<Integer> solution : tempSolutions) {
					solution.add(element[0]);
				}
				if(tempSolutions.isEmpty()) {

					List<Integer> itself = new ArrayList<>();
					itself.add(element[0]);
					tempSolutions.add(itself);
				}
				allSolutions.addAll(tempSolutions);
			}
		}
		return allSolutions;
	}

	public List<Integer> getSolutionForWeight(List<int[]> temp, int weight) {
		
		return null;
	}

}
