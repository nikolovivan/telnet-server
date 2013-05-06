/**
 * 
 */
package com.ivan.nikolov.telnet.server.arguments;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Ivan Nikolov
 * 
 */
public class ArgumentsTestCase extends TestCase {

	@Test
	public void testSupportedArguments() {
		Assert.assertNotNull(Argument.fromAlias("?"));
		Assert.assertEquals(Argument.HELP, Argument.fromAlias("?"));

		Assert.assertNotNull(Argument.fromAlias("-h"));
		Assert.assertEquals(Argument.HELP, Argument.fromAlias("-h"));

		Assert.assertNotNull(Argument.fromAlias("-help"));
		Assert.assertEquals(Argument.HELP, Argument.fromAlias("-help"));

		Assert.assertNotNull(Argument.fromAlias("-port"));
		Assert.assertEquals(Argument.PORT, Argument.fromAlias("-port"));

		Assert.assertNull(Argument.fromAlias("something"));
		Assert.assertNull(Argument.fromAlias("-something"));
	}
}
