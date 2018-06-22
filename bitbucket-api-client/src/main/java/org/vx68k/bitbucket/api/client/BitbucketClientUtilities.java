/*
 * BitbucketClientUtilities.java - class BitbucketClientUtilities
 * Copyright (C) 2015-2018 Kaz Nishimura
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Collection of static utility methods.
 *
 * @author Kaz Nishimura
 * @since 5.0
 */
public class BitbucketClientUtilities
{
    /**
     * JSON key for the <code>href</code> value.
     */
    private static final String HREF = "href";

    private static final String PACKAGE_NAME =
            BitbucketClientUtilities.class.getPackage().getName();

    /**
     * Does nothing but denies direct instantiation.
     */
    protected BitbucketClientUtilities()
    {
    }

    /**
     * Returns a logger.
     * @return logger for this package
     */
    public static Logger getLogger() {
        return Logger.getLogger(
                PACKAGE_NAME, PACKAGE_NAME + ".resources.LogMessages");
    }

    /**
     * Parses a UUID in a JSON string.
     * The string representation of a UUID may be enclosed in braces.
     *
     * @param jsonString JSON string, or {@code null}
     * @return {@link UUID} object, or {@code null}
     * @exception IllegalArgumentException if the JSON string did not represent
     * a UUID
     */
    public static UUID parseUUID(final JsonString jsonString)
        throws IllegalArgumentException
    {
        UUID uuid = null;
        if (jsonString != null) {
            String s = jsonString.getString();
            if (s.startsWith("{") && s.endsWith("}")) {
                s = s.substring(1, s.length() - 1);
            }
            uuid = UUID.fromString(s);
        }
        return uuid;
    }

    /**
     * Parses a JSON object into a map of links.
     * @param jsonObject JSON object
     * @return map of links
     */
    public static Map<String, URL> parseLinks(final JsonObject jsonObject)
    {
        Map<String, URL> links = new HashMap<String, URL>();
        for (Map.Entry<String, JsonValue> entry : jsonObject.entrySet()) {
            try {
                URL link = parseLink((JsonObject) entry.getValue());
                links.put(entry.getKey(), link);
            } catch (MalformedURLException exception) {
                getLogger().log(
                        Level.WARNING, "Could not parse a URL value",
                        exception);
            }
        }
        return links;
    }

    /**
     * Parses a JSON object into a URL.
     * @param jsonObject JSON object
     * @return link
     * @throws MalformedURLException if the <code>href</code> value could not
     * be parsed as a URL.
     */
    protected static URL parseLink(final JsonObject jsonObject)
            throws MalformedURLException
    {
        return parseURL(jsonObject.getString(HREF));
    }

    /**
     * Parses a string into a URL.
     * @param string string that represents a URL
     * @return parsed {@link URL} object, or <code>null</code> if the string
     * is <code>null</code>
     * @throws MalformedURLException if the string could not parsed as a URL.
     */
    public static URL parseURL(final String string)
        throws MalformedURLException
    {
        if (string == null) {
            return null;
        }

        return new URL(string);
    }
}
