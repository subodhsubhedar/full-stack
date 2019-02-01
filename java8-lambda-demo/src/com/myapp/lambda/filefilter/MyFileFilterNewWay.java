package com.myapp.lambda.filefilter;

import java.io.File;
import java.io.FileFilter;

/**
 * Using lambda expression
 * 
 * @author Admin
 *
 */
public class MyFileFilterNewWay {

	public static void main(String[] args) {

		// FileFilter lambdaFilter = (<method parameters>) -> <return stmt without
		// return keyword>

		FileFilter lambdaFilter = (File pathname) -> pathname.getName().endsWith(".java");

		File dir = new File("C:\\Users\\subodh\\Java-8-demo\\FileFilter");

		if (dir.exists()) {

			File[] fileList = dir.listFiles(lambdaFilter);

			for (File file : fileList) {

				System.out.println(file.getName());
			}

		}

	}

}
