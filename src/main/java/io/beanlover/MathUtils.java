package io.beanlover;

public class MathUtils {
	
	public int addition(int a, int b) {
		
		return a+b;
	}
	
	public int subtraction(int a, int b) {
		return Math.abs(a-b);
	}

	public int multiply(int a, int b) {
		
		return a*b;
	}
	
	public double divide(int a, int b) {
//		int x = a/0;
		return a / b;
	}
	
	public double areaOfCircle(int radious) {
		return Math.PI * radious;
	}
	

}
