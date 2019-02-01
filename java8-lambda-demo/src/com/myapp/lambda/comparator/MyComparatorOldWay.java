package com.myapp.lambda.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyComparatorOldWay {

	public static void main(String[] args) {

		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				return Integer.compare(o1.length(), o2.length());

			}

		};

		List<String> strList = Arrays.asList("lmnop", "abc", "pqrs");

		System.out.println("BEFORE SORTING :" + strList);

		Collections.sort(strList, comparator);

		System.out.println("AFTER SORTING :" + strList);
	}

}
