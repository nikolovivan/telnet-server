/**
 * 
 */
package com.ivan.nikolov.telnet.server.start;

import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.ivan.nikolov.telnet.server.arguments.Argument;
import com.ivan.nikolov.telnet.server.exceptions.InvalidArgumentsException;

/**
 * @author Ivan Nikolov
 * 
 */
public class StartServerTestCase extends TestCase {

	@Test
	public void testCreateValidServer() throws InvalidArgumentsException {
		String[] args = new String[2];
		args[0] = "-port";
		args[1] = "100";

		StartServer s = new StartServer(args);
		Map<Argument, Integer> res = s.getStartArguments();

		Assert.assertNotNull(res);
		Assert.assertEquals(1, res.size());
		Assert.assertTrue(res.containsKey(Argument.PORT));
		Assert.assertEquals(100, s.getPort());
	}

	@Test
	public void testCreateHelpServer() throws InvalidArgumentsException {
		String[] args = new String[6];
		args[0] = "?";
		StartServer s = new StartServer(args);
		Assert.assertTrue(s.getStartArguments().containsKey(Argument.HELP));
	}

	@Test
	public void testCreateWithUnknownArguments() {
		String[] args = new String[4];
		args[0] = "-port";
		args[1] = "100";
		args[2] = "-unknown";
		args[3] = "val";

		try {
			new StartServer(args);
			Assert.fail("Exception was not thrown.");
		} catch (InvalidArgumentsException e) {
			Assert.assertTrue("The expected exception was thrown.", true);
		}
	}
}
