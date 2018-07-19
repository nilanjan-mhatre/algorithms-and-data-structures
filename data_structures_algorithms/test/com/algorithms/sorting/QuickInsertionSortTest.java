package com.algorithms.sorting;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class QuickInsertionSortTest {

	Integer a[];

	public QuickInsertionSortTest(Integer[] a) {
		super();
		this.a = a;
	}

	@Test
	public void test() {
		QuickInsertionSort<Integer> quickInsertionSort = new QuickInsertionSort<>(a, true);
		quickInsertionSort.chooseAndMovePivot(0, a.length - 1);
		assertEquals(a[0] <= a[1], true);
		assertEquals(a[1] <= a[2], true);
	}

	@Parameters
	public static Collection input() {
		int[][] integers = new int[][]{{1, 2, 3}, {1, 3, 2}, {2, 3, 1}, {2, 1, 3}, {3, 2, 1}, {3, 1, 2}};
		Integer[][] newArray = new Integer[integers.length][];
		int i = 0;
		for (int[] value : integers) {
			int j=0;
			newArray[i] = new Integer[value.length];
			for(int v : value) {
				newArray[i][j++] = Integer.valueOf(v);
			}
			i++;
		}
		return Arrays.asList(new Integer[][][] {
            {{1, 2, 3}},
            {{1, 3, 2}},
            {{2, 3, 1}},
            {{2, 1, 3}},
            {{3, 2, 1}},
            {{3, 1, 2}}
        });
	}
}
