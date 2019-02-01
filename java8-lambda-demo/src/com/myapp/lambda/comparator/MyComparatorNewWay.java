package com.myapp.lambda.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyComparatorNewWay {

	public static void main(String[] args) {

		Comparator<String> lambdaComparator = (String o1, String o2) -> Integer.compare(o1.length(), o2.length());

		List<String> strList = Arrays.asList("lmnop", "abc", "pqrs");

		System.out.println("BEFORE SORTING :" + strList);

		Collections.sort(strList, lambdaComparator);

		System.out.println("AFTER SORTING :" + strList);
	}

}
