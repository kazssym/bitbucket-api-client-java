/*
 * PaginatedListTest.java
 * Copyright (C) 2018-2020 Kaz Nishimura
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

package org.vx68k.bitbucket.client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vx68k.bitbucket.BitbucketIssue;
import org.vx68k.bitbucket.client.internal.ClientIssue;
import org.vx68k.bitbucket.client.util.JsonStructureMessageBodyReader;

/**
 * Unit tests for {@link PaginatedList}.
 *
 * @author Kaz Nishimura
 */
class PaginatedListTest
{
    /**
     * Test resource.
     */
    private static final URI ENDPOINT =
        URI.create("https://api.bitbucket.org/"
            + "2.0/repositories/vx68k/bitbucket-api-client.java/issues");

    private ClientBuilder clientBuilder;

    @BeforeEach
    void setUp()
    {
        clientBuilder = ClientBuilder.newBuilder()
            .register(JsonStructureMessageBodyReader.class);
    }

    @AfterEach
    void tearDown()
    {
        clientBuilder = null;
    }

    /**
     * Tests {@link PaginatedList#get(int)}.
     */
    @Test
    void testGet1()
    {
        List<ClientIssue> issues =
            new PaginatedList<>(clientBuilder, ENDPOINT, ClientIssue.class);

        BitbucketIssue issue = issues.get(0);
        assertTrue(issue instanceof ClientIssue);
    }

    /**
     * Tests {@link PaginatedList#size()}.
     */
    @Test
    void testSize1()
    {
        List<ClientIssue> issues =
            new PaginatedList<>(clientBuilder, ENDPOINT, ClientIssue.class);

        assertTrue(issues.size() > 0);
    }
}
