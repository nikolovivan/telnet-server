/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;
import com.ivan.nikolov.telnet.server.exceptions.UnsuccessfulCommandException;

/**
 * @author Ivan Nikolov
 * 
 */
public class QuitCommandTestCase extends TestCase {

	@Test
	public void testCreateValidCommand() throws InvalidParametersException {
		QuitCommand command = new QuitCommand("quit");
		Assert.assertEquals("quit", command.getCommandName());
		Assert.assertEquals(0, command.getParameters().size());
	}

	@Test
	public void testCreateInvalidCommand() {
		try {
			new QuitCommand("quit -param");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testCreateCommandWithWrongName() {
		try {
			new QuitCommand("exit");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testExecuteCommand() throws InvalidParametersException, UnsuccessfulCommandException {
		QuitCommand command = new QuitCommand("quit");
		Context context = new Context();
		context.setPath("/some/path");
		Assert.assertEquals("Good bye!", command.execute(context));
	}
}
