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
public class LsCommandTestCase extends TestCase {

	@Test
	public void testCreateValidCommand() throws InvalidParametersException {
		LsCommand command = new LsCommand("ls");
		Assert.assertEquals("ls", command.getCommandName());
		Assert.assertEquals(0, command.getParameters().size());
	}

	@Test
	public void testCreateInvalidCommand() {
		try {
			new LsCommand("ls /folder/ something");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testCreateCommandWithWrongName() {
		try {
			new LsCommand("wrong");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testExecuteCommand() throws InvalidParametersException, UnsuccessfulCommandException {
		LsCommand command = new LsCommand("ls");
		Context context = new Context();
		context.setPath(System.getProperty("user.dir"));
		String result = command.execute(context);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testListUnexistentDirectory() throws InvalidParametersException {
		LsCommand command = new LsCommand("ls");
		Context context = new Context();
		context.setPath("/blah/nonexistent");
		try {
			command.execute(context);
			Assert.fail("Expected exception not thrown.");
		} catch (UnsuccessfulCommandException e) {
			Assert.assertTrue("The expected exception was thrown.", e.getMessage().equals("The given directory does not exist."));
		}
	}
}
