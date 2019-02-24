package project;

import project.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle8 {
	private List<Integer> a;
	private Integer heuristicValue;
	private Integer depth;
	private Puzzle8 parentPuzzle;
	
	public Puzzle8(List<Integer> a) {
		super();
		this.a = a;
		this.depth = 0;
	}

	public Puzzle8(List<Integer> a, Integer depth) {
		super();
		this.a = a;
		this.depth = depth;
	}

	// Operations up, down, left, right
	/**
     * Move the blank space or 0 up, if possible
     *
     * @return Puzzle8, a new object of Puzzle8, null if operation is not possible
     */
	public Puzzle8 moveUp() {
		Puzzle8 p = null;
		int index = a.indexOf(0);
		if(index > 2) {
			List<Integer> b = new ArrayList<>(a);
			Collections.swap(b, index, index - 3);
			p = new Puzzle8(b);
			p.setParentPuzzle(this);
		}
		return p;
	}

	/**
     * Move the blank space or 0 down, if possible
     *
     * @return Puzzle8, a new object of Puzzle8, null if operation is not possible
     */
	public Puzzle8 moveDown() {
		Puzzle8 p = null;
		int index = a.indexOf(0);
		if(index < 6) {
			List<Integer> b = new ArrayList<>(a);
			Collections.swap(b, index, index + 3);
			p = new Puzzle8(b);
			p.setParentPuzzle(this);
		}
		return p;
	}

	/**
     * Move the blank space or 0 to the right, if possible
     *
     * @return Puzzle8, a new object of Puzzle8, null if operation is not possible
     */
	public Puzzle8 moveRight() {
		Puzzle8 p = null;
		int index = a.indexOf(0);
		if(index % 3 != 2) {
			List<Integer> b = new ArrayList<>(a);
			Collections.swap(b, index, index + 1);
			p = new Puzzle8(b);
			p.setParentPuzzle(this);
		}
		return p;
	}

	/**
     * Move the blank space or 0 to the left, if possible
     *
     * @return Puzzle8, a new object of Puzzle8, null if operation is not possible
     */
	public Puzzle8 moveLeft() {
		Puzzle8 p = null;
		int index = a.indexOf(0);
		if(index % 3 != 0) {
			List<Integer> b = new ArrayList<>(a);
			Collections.swap(b, index, index - 1);
			p = new Puzzle8(b);
			p.setParentPuzzle(this);
		}
		return p;
	}
	
	//.............. display the puzzle .....................//
	public String display() {
		StringBuilder displayString = new StringBuilder(); 
		for(int i=0; i<a.size(); i++) {
			if(i%3 == 0) {
				displayString.append("\n");
			}
			if(a.get(i) != 0) {
				displayString.append(a.get(i));
			} else {
				displayString.append("_");
			}
			displayString.append(" ");
		}
		displayString.append("\n");
//		displayString.append(heuristicValue);

		return displayString.toString();
	}
	//.....................................................//

	// Getters and Setters
	/**
     * The puzzle array stored as a 1D - array
     *
     * @return List<Integer>
     */
	public List<Integer> getA() {
		return a;
	}

	public void setA(List<Integer> a) {
		this.a = a;
	}

	/**
     * The Heuristic value as per the applied heuristic function
     *
     * @return Integer f()
     */
	public Integer getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(Integer heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	
	/**
     * The distance travelled to get to this puzzle/node, g()
     *
     * @return Integer g()
     */
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	/**
     * Parent puzzle will help to determine the path
     *
     * @return Puzzle8 parent
     */
	public Puzzle8 getParentPuzzle() {
		return parentPuzzle;
	}

	public void setParentPuzzle(Puzzle8 parentPuzzle) {
		this.parentPuzzle = parentPuzzle;
	}

	/**
     * The total heuristic value
     *
     * @return Integer g() + f(), total heuristic value
     */
	public Integer getTotalHeuristicValue() {
		return heuristicValue + depth;
	}

	// Equals and hashCode
	@Override
	public int hashCode() {
		String str ="";
		for(int i=0; i<a.size(); i++) {
			str += a.get(i);
		}
		
		return new Integer(str);
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
}
