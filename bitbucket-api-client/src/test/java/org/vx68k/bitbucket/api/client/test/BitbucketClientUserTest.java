/*
 * BitbucketClientUserTest.java - class BitbucketClientUserTest
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

package org.vx68k.bitbucket.api.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import org.junit.Test;
import org.vx68k.bitbucket.api.client.BitbucketClientUser;

/**
 * Unit tests for {@link BitbucketClientUser}.
 *
 * @author Kaz Nishimura
 */
public final class BitbucketClientUserTest
{
    /**
     * Adds a {@code "user"} type to a JSON object builder.
     *
     * @param builder JSON object builder
     * @return JSON object builder which has a {@code "user"} type.
     */
    static JsonObjectBuilder addUserType(final JsonObjectBuilder builder)
    {
        return builder.add("type", "user");
    }

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor()
    {
        try {
            new BitbucketClientUser(null);
            fail();
        }
        catch (IllegalArgumentException exception) {
            System.out.println("Caught " + exception.toString());
        }

        JsonObjectBuilder builder = Json.createObjectBuilder();

        try {
            new BitbucketClientUser(builder.build());
            fail();
        }
        catch (IllegalArgumentException exception) {
            System.out.println("Caught " + exception.toString());
        }

        builder.add("type", "not_user");

        try {
            new BitbucketClientUser(builder.build());
            fail();
        }
        catch (IllegalArgumentException exception) {
            System.out.println("Caught " + exception.toString());
        }
    }

    /**
     * Tests {@link BitbucketClientUser#getName}.
     */
    @Test
    public void testGetName()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getName());

        addUserType(builder).add("username", "mary");

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals("mary", user2.getName());
    }

    /**
     * Tests {@link BitbucketClientUser#getUUID}.
     */
    @Test
    public void testGetUUID()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getUUID());

        UUID uuid = UUID.fromString("01234567-89ab-cdef-0123-456789abcdef");
        addUserType(builder).add("uuid", "{" + uuid.toString() + "}");

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals(uuid, user2.getUUID());
    }

    /**
     * Tests {@link BitbucketClientUser#getDisplayName}.
     */
    @Test
    public void testGetDisplayName()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getDisplayName());

        addUserType(builder).add("display_name", "Mary Somebody");

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals("Mary Somebody", user2.getDisplayName());
    }

    /**
     * Tests {@link BitbucketClientUser#getWebsite}.
     */
    @Test
    public void testGetWebsite()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getWebsite());

        addUserType(builder).add("website", "http://example.com/");

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals("http://example.com/", user2.getWebsite());
    }

    /**
     * Tests {@link BitbucketClientUser#getLocation}.
     */
    @Test
    public void testGetLocation()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getLocation());

        addUserType(builder).add("location", "Behind You");

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals("Behind You", user2.getLocation());
    }

    /**
     * Tests {@link BitbucketClientUser#getCreated}.
     */
    @Test
    public void testGetCreated()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getCreated());

        OffsetDateTime dateTime = OffsetDateTime.parse(
            "2001-01-01T01:23:45.678901+09:00");
        addUserType(builder).add("created_on", dateTime.toString());

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals(dateTime.toInstant(), user2.getCreated());
    }

    /**
     * Tests {@link BitbucketClientUser#isStaff}.
     */
    @Test
    public void testIsStaff()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertEquals(false, user1.isStaff());

        addUserType(builder).add("is_staff", true);

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals(true, user2.isStaff());
    }

    /**
     * Tests {@link BitbucketClientUser#getAccountId}.
     */
    @Test
    public void testGetAccountId()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addUserType(builder);

        BitbucketClientUser user1 = new BitbucketClientUser(builder.build());
        assertNull(user1.getAccountId());

        // We do not care about the official format.
        addUserType(builder).add("account_id", "012345");

        BitbucketClientUser user2 = new BitbucketClientUser(builder.build());
        assertEquals("012345", user2.getAccountId());
    }
}