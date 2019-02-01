package com.myapp.lambda.runnable;

public class MyRunnableOldWay {

	public static void main(String[] args) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("Current thread name : " + Thread.currentThread().getName());
			}
		};

		Thread myThread = new Thread(runnable);

		myThread.start();

	}

}
