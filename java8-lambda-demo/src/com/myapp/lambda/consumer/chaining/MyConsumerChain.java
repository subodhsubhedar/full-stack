package com.myapp.lambda.consumer.chaining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 
 * @author Admin
 *
 */
public class MyConsumerChain {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> myList = Arrays.asList("ONE", "TWO", "THREE", "FOUR");

		// Iterate through the list with the help of a default method in iterable
		// interface added for java 8

		List<String> resultList = new ArrayList<String>();

		// Demo of a Consumer functional interface
		Consumer<String> c1 = (s) -> System.out.println(s);

		Consumer<String> c2 = (s) -> {
			if (s.equals("TWO")) {
				resultList.add(s);
			}
		};

		// chanining consumer

		// myList.forEach(c1);

		myList.forEach(c1.andThen(c2));

		System.out.println("Size of the result list is :" + resultList.size());

	}

}
