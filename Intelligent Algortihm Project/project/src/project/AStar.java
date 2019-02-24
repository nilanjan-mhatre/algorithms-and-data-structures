package project;

import project.Puzzle8;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AStar {
	
	List<Integer> initial;
	List<Integer> goal;
	private List<Puzzle8> fringe;
	private Set<Integer> visited;

	public AStar(List<Integer> initial, List<Integer> goal) {
		super();
		this.initial = initial;
		this.goal = goal;
		Puzzle8 puzzle = new Puzzle8(initial);
		this.fringe = new ArrayList<>();
		this.visited = new HashSet<>();
		insertPuzzleNode(puzzle, 0);
	}

	/**
	 * Run the A-star algorithm and display the path
	 *
	 */
	public void runAlgorithm() {
		
		int totalExpandedNodes = 0;
		int totalVisitedNodes = 0;
		Puzzle8 puzzle = null;

		while(true) {

			puzzle = fringe.remove(0);
//			puzzle.display();
			if(puzzle.getHeuristicValue().intValue() == 0) {
				break;
			}
			
			visited.add(puzzle.hashCode());
			totalVisitedNodes++;
			
			Puzzle8 puzzleUp = puzzle.moveUp();
			Puzzle8 puzzleDown = puzzle.moveDown();
			Puzzle8 puzzleRight = puzzle.moveRight();
			Puzzle8 puzzleLeft = puzzle.moveLeft();

			if(puzzleUp != null && !visited.contains(puzzleUp.hashCode())) {
				totalExpandedNodes++;
				insertPuzzleNode(puzzleUp, puzzle.getDepth() + 1);
			}
			if(puzzleDown != null && !visited.contains(puzzleDown.hashCode())) {
				totalExpandedNodes++;
				insertPuzzleNode(puzzleDown, puzzle.getDepth() + 1);
			}
			if(puzzleRight != null && !visited.contains(puzzleRight.hashCode())) {
				totalExpandedNodes++;
				insertPuzzleNode(puzzleRight, puzzle.getDepth() + 1);
			}
			if(puzzleLeft != null && !visited.contains(puzzleLeft.hashCode())) {
				totalExpandedNodes++;
				insertPuzzleNode(puzzleLeft, puzzle.getDepth() + 1);
			}
		}

		System.out.println("Total Expanded nodes: " + totalExpandedNodes);
		System.out.println("Total Visited nodes: " + totalVisitedNodes);

		StringBuilder builder = new StringBuilder();
		while(puzzle != null) {
			builder.insert(0, puzzle.display());
			puzzle = puzzle.getParentPuzzle();
		}
		System.out.println();
		System.out.print("Path: -");
		System.out.println(builder.toString());
	}

	/**
	 * Insert a new puzzle arrangement in the fringe at the right position,
	 * so the fringe stays sorted.
	 * Insertion sort technique is used to insert the new puzzle
	 *
	 */
	private void insertPuzzleNode(Puzzle8 puzzle, int depth) {

		int position = fringe.size();
		Integer heuristicValue = runHeuristic2(puzzle.getA());
		puzzle.setHeuristicValue(heuristicValue);
		puzzle.setDepth(depth);
		
		for(int i=0; i<fringe.size(); i++) {
			if(puzzle.getTotalHeuristicValue() < fringe.get(i).getTotalHeuristicValue()) {
				position = i;
				break;
			}
		}
		fringe.add(position, puzzle);
	}

	/**
	 * Manhattan distance
	 * Heuristic function on the 8-puzzle
	 *
	 */
	public Integer runHeuristic1(List<Integer> a) {
		Integer heuristic = Integer.MAX_VALUE;
		if(a != null) {
			heuristic = 0;
			for(int i=0; i<a.size(); i++) {
				int index = goal.indexOf(a.get(i));
				heuristic += Math.abs(i%3 - index%3) + Math.abs(i/3 - index/3);
			}
		}
		
		return heuristic;
	}

	/**
	 * Missing pieces
	 * Heuristic function on the 8-puzzle
	 *
	 */
	public Integer runHeuristic2(List<Integer> a) {
		Integer heuristic = null;
		if(a != null) {
			heuristic = 0;
			for(int i=0; i<a.size(); i++) {
				if(!a.get(i).equals(goal.get(i))) {
					heuristic++;
				}
			}
		}
		
		return heuristic;
	}
	
	public static void main(String[] args) {

//		Integer[] a1 = {0, 1, 3, 4, 2, 5, 7, 8, 6};Integer[] b1 = {1, 2, 3, 4, 5, 6, 7, 8, 0};
//		Integer[] a1 = {1, 2, 3, 7, 4, 5, 6, 8, 0};Integer[] b1 = {1, 2, 3, 8, 6, 4, 7, 5, 0};
//		Integer[] a1 = {2, 8, 1, 3, 4, 6, 7, 5, 0};Integer[] b1 = {3, 2, 1, 8, 0, 4, 7, 5, 6};
//		List<Integer> a = new ArrayList<>(Arrays.asList(a1));
//		List<Integer> b = new ArrayList<>(Arrays.asList(b1));
//		AStar star = new AStar(a, b);

		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();
		
		Scanner src = new Scanner(System.in);

		System.out.println("Add numbers from 0-9 to INITIAL puzzle state serially, separated by SPACE and/or NEW LINE.");
		while(a.size() < 9) {
			Integer n = src.nextInt();
			if(n >= 0 && n < 9 && !a.contains(n)) {
				a.add(n);
			} else {
				System.out.println("Invalid. Add numbers from 0-9.");
			}
		}

		System.out.println("Add numbers from 0-9 to GOAL puzzle state serially.");
		while(b.size() < 9) {
			Integer n = src.nextInt();
			if(n >= 0 && n < 9 && !b.contains(n)) {
				b.add(n);
			} else {
				System.out.println("Invalid. Add numbers from 0-9.");
			}
		}
		
		AStar star = new AStar(a, b);
		
		star.runAlgorithm();
		src.close();
	}
}
