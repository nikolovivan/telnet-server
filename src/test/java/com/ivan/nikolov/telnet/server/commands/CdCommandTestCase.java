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
public class CdCommandTestCase extends TestCase {

	@Test
	public void testCreateValidCommand() throws InvalidParametersException {
		CdCommand command = new CdCommand("cd /home/ivan/");
		Assert.assertEquals("cd", command.getCommandName());
		Assert.assertEquals(1, command.getParameters().size());
	}

	@Test
	public void testCreateInvalidCommand() {
		try {
			new CdCommand("cd");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
		try {
			new CdCommand("cd /home/ivan/ param");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testCreateCommandWithWrongName() {
		try {
			new CdCommand("change /home/ivan");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testExecuteCommand() throws InvalidParametersException, UnsuccessfulCommandException {
		// should work fine on all OS.
		CdCommand command = new CdCommand("cd ..");
		Context context = new Context();
		String userDir = System.getProperty("user.dir");
		context.setPath(userDir);
		String result = command.execute(context);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(userDir.startsWith(result));
	}

	@Test
	public void testListUnexistentDirectory() throws InvalidParametersException {
		CdCommand command = new CdCommand("cd /blah/nonexistent");
		Context context = new Context();
		context.setPath("/some/path");
		try {
			command.execute(context);
			Assert.fail("Expected exception not thrown!");
		} catch (UnsuccessfulCommandException e) {
			Assert.assertTrue("The expected exception was thrown!", e.getMessage().equals("The given directory does not exist."));
		}
	}
}
