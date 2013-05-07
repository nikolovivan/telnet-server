/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.io.File;

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
public class MkdirCommandTestCase extends TestCase {

	@Test
	public void testCreateValidCommand() throws InvalidParametersException {
		MkdirCommand command = new MkdirCommand("mkdir /home/ivan/blah");
		Assert.assertEquals("mkdir", command.getCommandName());
		Assert.assertEquals(1, command.getParameters().size());
	}

	@Test
	public void testCreateInvalidCommand() {
		try {
			new MkdirCommand("mkdir");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
		try {
			new MkdirCommand("mkdir /home/ivan/ param");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testCreateCommandWithWrongName() {
		try {
			new CdCommand("makedir /home/ivan");
			Assert.fail("Did not throw exception!");
		} catch (InvalidParametersException e) {
			Assert.assertTrue("Expected exception was thrown.", true);
		}
	}

	@Test
	public void testExecuteCommand() throws InvalidParametersException, UnsuccessfulCommandException {
		// should work fine on all OS.
		MkdirCommand command = new MkdirCommand("mkdir src/test/resources/test-make-dir");
		Context context = new Context();
		String userDir = System.getProperty("user.dir");
		context.setPath(userDir);
		String result = command.execute(context);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals("Created!", result);

		// cleanup
		File dir = new File("src/test/resources/test-make-dir");
		dir.delete();
	}
}
