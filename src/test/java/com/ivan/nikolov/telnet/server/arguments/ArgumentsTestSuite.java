/**
 * 
 */
package com.ivan.nikolov.telnet.server.arguments;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Ivan Nikolov
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ ArgumentsTestCase.class })
public class ArgumentsTestSuite {

	@BeforeClass
	public static void doBeforeClass() {
		System.out.println("Starting arguments tests.");
	}

	@AfterClass
	public static void doAfterClass() {
		System.out.println("Finished arguments tests.");
	}
}
