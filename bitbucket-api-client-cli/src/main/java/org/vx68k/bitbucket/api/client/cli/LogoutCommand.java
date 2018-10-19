/*
 * LogoutCommand.java
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

package org.vx68k.bitbucket.api.client.cli;

import org.vx68k.bitbucket.api.client.BitbucketClient;

/**
 * {@code logout} command.
 *
 * @author Kaz Nishimura
 * @since 5.0
 */
public final class LogoutCommand extends AbstractCommand
{
    /**
     * Initializes the object.
     *
     * @param bitbucketClient a Bitbucket API client
     */
    public LogoutCommand(final BitbucketClient bitbucketClient)
    {
        super(bitbucketClient);
    }

    @Override
    public void run(String name, String[] args)
    {
        BitbucketClient bitbucketClient = getBitbucketClient();
        bitbucketClient.logout();
        CLIUtilities.saveTokens(bitbucketClient);
    }
}
