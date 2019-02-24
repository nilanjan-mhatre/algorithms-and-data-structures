package project;

import project.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Test Class to tune the right hash method,
 * that would give unique hash value for each possible combination
 * of 8-puzzle problem
 *
 */
public class TestHashMethod {

	public static void main(String[] args) {

		Integer[] a1 = {1, 2, 3, 7, 4, 5, 6, 8, 0};
		List<Integer> arr = Arrays.asList(a1);
		
		List<Integer> hashValues = getValuesOfAllCombinations(new ArrayList<>(), arr);
		Set<Integer> uniqueHashValues = new HashSet<>();
		 for (Integer value : hashValues) {
		     if (uniqueHashValues.add(value) == false) {
		        System.out.println(value);
		     }
		}

		System.out.println(hashValues.size() + " " + uniqueHashValues.size());
	}

	private static List<Integer> getValuesOfAllCombinations(List<Integer> b, List<Integer> arr) {
		List<Integer> hashValues = new ArrayList<>();
		if(arr.size() == 0) {
			hashValues.add(generateHashValue(b));
		}
		
		for(Integer a : arr) {
			List<Integer> currentArr = new ArrayList<>(b);
			currentArr.add(a);
			List<Integer> arr1 = arr.stream()
								.filter(ele -> ele.intValue() != a.intValue())
								.collect(Collectors.toList());
			hashValues.addAll(getValuesOfAllCombinations(currentArr, arr1));
		}
		return hashValues;
	}
	
	public static Integer generateHashValue(List<Integer> array) {
		String str ="";
		for(int i=0; i<array.size(); i++) {
			str += array.get(i);
		}
		
		return new Integer(str);
	}

}
