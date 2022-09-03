package io.beanlover;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("The Math Utils Class")
class MathUtilsTest {

	// As Member variables.
	MathUtils mathUtils;
	
	// TestInfo Interface provides the information about the testing method, class etc.
	TestInfo testInfo;
	
	// TestReporter Interface provides the report of the test info in the console.
	TestReporter testReporter;
	
	
	@BeforeAll
	void beforeAllInit() {
		/*
		 * this method needs to be static so that it can be executed before creating an instance of the test class.
		 * If we use TestInstance(TestInstance.Lifecycle.PER_CLASS) above the test class
		 * then no need to declare this method as static
		 */
		System.out.println("This method is got executed first, before executing the test class.");
//		System.out.println("Running the test class : "+testInfo.getTestClass());
	}
	
	
	@BeforeEach
	void initializeInstances(TestInfo testInfo, TestReporter testReporter) {
		/*
		 * This method got executed just before the execution of each and every test methods.
		 * Usually it is used to initialize the instance variables.  
		 */
//		System.out.println("Initializing the member variables.");
		this.mathUtils = new MathUtils();
		this.testInfo = testInfo;
		this.testReporter = testReporter;
//		System.out.println();
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" With Tags : "+testInfo.getTags());
	}
	
	@AfterEach
	void cleanUp() {
		/*
		 * This method got executed just after the execution of each and every test methods.
		 * Usually it is used to clean up the instance variables.  
		 */
//		System.out.println("Cleaning up the test method.");
	}
	
	@AfterAll
	void afterAllCleanUp() {
		/*
		 * This method got executed after the execution of all test methods.
		 * Usually it is used to clean up the instance of the class. 
		 * 
		 * This method needs to be static so that it can be executed after cleanup the instance of the test class.
		 * If we use TestInstance(TestInstance.Lifecycle.PER_CLASS) above the test class
		 *  then no need to declare this method as static
		 */
		System.out.println("The final clean up is executed here.");
	}
	
	@Test
	@DisplayName("Addition Method")
	@Tag("Math")
	void testAddition() {
		
		int expected, actual;
		expected = 12;
		actual = mathUtils.addition(4, 8);
		assertEquals(expected, actual, "The addition method should add two integers.");

//		actual = mathUtils.addition(4, 6);
//		assertEquals(expected, actual, "The addition method should add two integers.");
//
//		actual = mathUtils.addition(4, 10);
//		assertEquals(expected, actual, "The addition method should add two integers.");
	}

	@Test
	@Tag("Math")
	void testSubtraction() {
		int expected, actual;
		expected = 2;
		
		actual = mathUtils.subtraction(4, 6);
		assertEquals(expected, actual, "The subtraction method should subtract two integers.");

//		actual = mathUtils.subtraction(4, 4);
//		assertEquals(expected, actual, "The subtraction method should subtract two integers.");
//
//
//		actual = mathUtils.subtraction(4, 10);
//		assertEquals(expected, actual, "The subtraction method should subtract two integers.");
	}
	
	@Test
	@DisplayName("Multiplication Method")
	@Tag("Math")
	void multiply() {
		int a = 5;
		int b = 6;
		assertEquals(30, mathUtils.multiply(a, b), "Multiplication should return the product of two numbers");
	}

	@Test
	@DisplayName("Division Method")
	@Tag("Math")
	void divide() {
		int a = 24;
		int b = 2;
		assertEquals(12, mathUtils.divide(a, b), "Divide should return the division of two numbers");
	}

	@Test
	@Disabled
	@DisplayName("Exception Method")
	void exceptionTest() {
		int a = 25;
		int b = 0;
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(a, b), "Can not divide any number by zero");
	}

	@Test
	@DisplayName("Circle Area Method")
	void circleAreaTest() {
		int radious = 10;
		assertEquals(31.41592653589793, mathUtils.areaOfCircle(radious), "The method shopuld return the area of the circle.");
	}

	@Test
	@DisplayName("Assert All Method")
	@Tag("Math")
	void testAssertAll() {
		assertAll(
				()-> assertEquals(30, mathUtils.addition(10, 20), ()-> "The addition method should add two integers."),
				()-> assertEquals(10, mathUtils.subtraction(30, 20), ()-> "The subtraction method should subtract two integers."),
				()-> assertEquals(30, mathUtils.multiply(5, 6), ()-> "Multiplication should return the product of two numbers"),
				()-> assertEquals(12, mathUtils.divide(24, 2), ()-> "Divide should return the division of two numbers")
		);
	}

	@Nested
	@DisplayName("Add Test Class")
	@Tag("Math")
	class AddTest {
		@Test
		@DisplayName("Add With Positive Number")
		void testAdditionForPositive() {
			assertEquals(12, mathUtils.addition(4, 8), () -> "should return the right sum");
			/*
			 * Here we passed the message as a lambda expression
			 * It will create the string only if the assert method fails 
			 * Otherwise it will skip without creating the string.
			 * This optimization saves from creating the message string not matter it is used or not. 
			 */
		}

		@Test
		@DisplayName("Add With Negative Number")
		void testAdditionForNegative() {
			assertEquals(-4, mathUtils.addition(4, -8), () -> "should return the right sum");
		}
	}
	@RepeatedTest(4)
	@DisplayName("Repeted Test")
	void testMultiplication(RepetitionInfo repetitionInfo) {
		int repetitionNumber = repetitionInfo.getCurrentRepetition();
		
		if(Objects.equals(repetitionNumber, 1)) {
			assertEquals(45, mathUtils.multiply(5, 9), () -> "should return the correct multiplication.");
		}
		else if(Objects.equals(repetitionNumber, 2)) {
			assertEquals(40, mathUtils.multiply(5, 9), () -> "should return the correct multiplication.");
		}
		else if(Objects.equals(repetitionNumber, 3)) {
			assertEquals(10, mathUtils.multiply(5, 2), () -> "should return the correct multiplication.");
		}
		else {
			assertEquals(45, mathUtils.multiply(5, 3), () -> "should return the correct multiplication.");
		}
	}
	

}