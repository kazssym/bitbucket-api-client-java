/*
 * BitbucketClientAccount.java - class BitbucketClientAccount
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

package org.vx68k.bitbucket.api.client;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import javax.json.JsonObject;
import org.vx68k.bitbucket.api.BitbucketAccount;
import org.vx68k.bitbucket.api.BitbucketRepository;

/**
 * Account represented by a JSON object.
 *
 * @author Kaz Nishimura
 * @since 5.0
 */
public abstract class BitbucketClientAccount extends BitbucketClientObject
    implements BitbucketAccount
{
    /**
     * Name for the {@code username} value in a JSON account object.
     */
    private static final String USERNAME = "username";

    /**
     * Name for the {@code uuid} value in a JSON account object.
     */
    private static final String UUID = "uuid";

    /**
     * Name for the {@code display_name} value in a JSON account object.
     */
    private static final String DISPLAY_NAME = "display_name";

    /**
     * Name for the {@code website} value in a JSON account object.
     */
    private static final String WEBSITE = "website";

    /**
     * Name for the {@code location} value in a JSON account object.
     */
    private static final String LOCATION = "location";

    /**
     * Name for the {@code created_on} value in a JSON account object.
     */
    private static final String CREATED_ON = "created_on";

    /**
     * Name for the {@code links} object in a JSON account object.
     */
    private static final String LINKS = "links";

    /**
     * Constructs this object with no Bitbucket client.
     *
     * @param object JSON object
     * @exception IllegalArgumentException if {@code object} is {@code null}
     */
    protected BitbucketClientAccount(final JsonObject object)
    {
        this(object, null);
    }

    /**
     * Constructs this object.
     *
     * @param object JSON object
     * @param client Bitbucket client, or {@code null}
     * @exception IllegalArgumentException if {@code object} is {@code null}
     */
    protected BitbucketClientAccount(
        final JsonObject object, final BitbucketClient client)
    {
        super(object, client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getName()
    {
        JsonObject object = getJsonObject();
        return object.getString(USERNAME, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final UUID getUUID()
    {
        JsonObject object = getJsonObject();
        return BitbucketClientUtilities.parseUUID(
                object.getJsonString(UUID));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getDisplayName()
    {
        JsonObject object = getJsonObject();
        return object.getString(DISPLAY_NAME, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getWebsite()
    {
        JsonObject object = getJsonObject();
        return object.getString(WEBSITE, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getLocation()
    {
        JsonObject object = getJsonObject();
        return object.getString(LOCATION, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Instant getCreated()
    {
        JsonObject object = getJsonObject();
        Instant created = null;
        if (object.containsKey(CREATED_ON)) {
            created = OffsetDateTime.parse(object.getString(CREATED_ON))
                .toInstant();
        }
        return created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, String> getLinks()
    {
        JsonObject object = getJsonObject();
        return BitbucketClientUtilities.parseLinks(
            object.getJsonObject(LINKS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BitbucketRepository getRepository(final String name)
    {
        BitbucketClient client = getBitbucketClient();
        return client.getRepository(getName(), name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Collection<BitbucketRepository> repositories()
    {
        // @todo Implement this method.
        return Collections.emptySet();
    }
}