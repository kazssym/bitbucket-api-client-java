/*
 * CLI.java - class CLI
 * Copyright (C) 2018 Kaz Nishimura
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package org.vx68k.bitbucket.cli;

import org.vx68k.bitbucket.client.BitbucketClient;

/**
 * Main class of the CLI.
 *
 * @author Kaz Nishimura
 * @since 6.0
 */
public class CLI extends CommandGroup
{
    /**
     * Constructs this object.
     *
     * @param bitbucketClientValue value for the Bitbucket client
     */
    public CLI(final BitbucketClient bitbucketClientValue)
    {
        super(bitbucketClientValue);

        add("user", new UserCommandGroup(getBitbucketClient()));
        add("team", new TeamCommandGroup(getBitbucketClient()));
        add("login", new LoginCommand(getBitbucketClient()));
        add("logout", new LogoutCommand(getBitbucketClient()));
        // @todo Add more commands.
    }

    /**
     * Command entry point.
     *
     * @param args command arguments
     */
    public static void main(final String[] args)
    {
        String commandName = System.getProperty("_0", "CLI");
        int lastSlash = commandName.lastIndexOf("/");
        if (lastSlash >= 0) {
            commandName = commandName.substring(lastSlash + 1);
        }

        CLI cli = new CLI(new BitbucketClient());
        try {
            cli.run(commandName, args);
        }
        catch (final CLIException exception) {
            System.err.format("%s: %s\n", commandName, exception.getMessage());
        }
        catch (final Exception exception) {
            System.err.println(exception);
        }
    }
}
