/**
 * 
 */
package com.ivan.nikolov.telnet.server.start;

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
@SuiteClasses({ StartServerTestCase.class })
public class StartTestSuite {

	@BeforeClass
	public static void doBeforeClass() {
		System.out.println("Starting server initialization tests.");
	}

	@AfterClass
	public static void doAfterClass() {
		System.out.println("Finished server initialization tests.");
	}
}
