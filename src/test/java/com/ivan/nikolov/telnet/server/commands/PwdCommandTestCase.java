package com.ivan.nikolov.telnet.server.commands;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;

/**
 * @author Ivan Nikolov
 * 
 */
public class PwdCommandTestCase extends TestCase {

	@Test
	public void testCreateValidCommand() throws InvalidParametersException {
		PwdCommand command = new PwdCommand("pwd");
		Assert.assertEquals("pwd", command.getCommandName());
		Assert.assertEquals(0, command.getParameters().size());
	}

	@Test
	public void testCreateInvalidCommand() {
		try {
			new PwdCommand("pwd -param");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testCreateCommandWithWrongName() {
		try {
			new PwdCommand("wrong");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testExecuteCommand() throws InvalidParametersException {
		PwdCommand command = new PwdCommand("pwd");
		Context context = new Context();
		context.setPath("/some/path");
		Assert.assertEquals("/some/path", command.execute(context));
	}
}
