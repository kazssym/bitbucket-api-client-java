/*
 * Copyright (C) 2015 Nishimura Software Studio
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.vx68k.bitbucket.api.client;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Collection of unit tests for [@link BitbucketUser}.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public class BitbucketUserTest {

    private Handler loggingHandler;

    @Before
    public void setUp() {
        loggingHandler = new ConsoleHandler();
        ClientUtilities.getLogger().addHandler(loggingHandler);
    }

    @After
    public void tearDown() {
        ClientUtilities.getLogger().removeHandler(loggingHandler);
        loggingHandler = null;
    }

    @Test
    public void testDefaultUser() {
        BitbucketUser user = new BitbucketUser();
        assertNull(user.getName());
        assertEquals("user", user.getType());
        assertNull(user.getUuid());
        assertNull(user.getDisplayName());
        assertNull(user.getWebsite());
    }

    @Ignore
    @Test
    public void testJsonUser() {
    }
}
