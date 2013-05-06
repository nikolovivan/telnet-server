/**
 * 
 */
package com.ivan.nikolov.telnet.server.arguments;

import java.util.HashSet;
import java.util.Set;

/**
 * An enum of the arguments that the server accepts.
 * 
 * @author Ivan Nikolov
 * 
 */
public enum Argument {
	PORT("-port"),
	HELP("?", "-h", "-help");

	private final Set<String> aliases = new HashSet<String>();

	/**
	 * Creates an argument definition from the aliases.
	 */
	private Argument(final String... aliases) {
		if (aliases != null) {
			for (String alias : aliases) {
				this.aliases.add(alias);
			}
		}
	}

	/**
	 * Returns the correct argument from the given alias.
	 * 
	 * @param alias
	 *            The alias.
	 * @return The correct argument or <code>null</code> if nothing found.
	 */
	public static Argument fromAlias(final String alias) {
		for (Argument arg : Argument.values()) {
			if (arg.aliases.contains(alias)) {
				return arg;
			}
		}
		return null;
	}
}