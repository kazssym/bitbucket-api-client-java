/*
 * Commit
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

import java.util.logging.Level;
import javax.json.JsonObject;
import org.vx68k.bitbucket.api.BitbucketCommit;

/**
 * Commit in a Bitbucket repository.
 * @author Kaz Nishimura
 * @since 3.0
 */
public class Commit extends BitbucketClientObject implements BitbucketCommit
{
    /**
     * Type value for commits.
     */
    private static final String COMMIT_TYPE = "commit";

    /**
     * Constructs this object from a JSON object.
     * @param jsonObject JSON object that represents a commit.
     */
    public Commit(final JsonObject jsonObject) {
        super(jsonObject);
        if (!getType().equals(COMMIT_TYPE)) {
            throw new IllegalArgumentException("Not user");
        }
        BitbucketClientUtilities.getLogger().log(
                Level.INFO,
                "Parsing JSON object (\"" + COMMIT_TYPE + "\"): {0}",
                jsonObject);
        // TODO: Add fields.
    }
}
