/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Tests for the various commands.
 * 
 * @author Ivan Nikolov
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ PwdCommandTestCase.class, LsCommandTestCase.class, CdCommandTestCase.class, MkdirCommandTestCase.class, QuitCommandTestCase.class })
public class CommandsTestSuite {

	@BeforeClass
	public static void doBeforeClass() {
		System.out.println("Starting command tests.");
	}

	@AfterClass
	public static void doAfterClass() {
		System.out.println("Command tests finished.");
	}
}
