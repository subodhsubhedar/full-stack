package com.myapp.lambda.filefilter;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilterOldWay {

	public static void main(String[] args) {
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".java");
			}
		};

		File dir = new File("C:\\Users\\subodh\\Java-8-demo\\FileFilter");

		if (dir.exists()) {

			File[] fileList = dir.listFiles(fileFilter);

			for (File file : fileList) {

				System.out.println(file.getName());
			}

		}

	}

}
