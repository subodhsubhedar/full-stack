package com.myapp.lambda.runnable;

public class MyRunnableNewWay {

	public static void main(String[] args) {

		Runnable lambdaRunnable = () -> {
			System.out.println("Current thread name : " + Thread.currentThread().getName());
		};

		Thread myThread = new Thread(lambdaRunnable);

		myThread.start();

	}

}
