/*
 * RepositoryPush.java - class RepositoryPush
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

package org.vx68k.bitbucket.webhook;

import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.vx68k.bitbucket.api.client.Commit;

/**
 * Represents a push to a Bitbucket repository.
 *
 * @author Kaz Nishimura
 * @since 5.0
 */
public class RepositoryPush extends BitbucketActivity
{
    /**
     * Name of the {@code push} object in a JSON object.
     */
    public static final String PUSH = "push";

    /**
     * Name of the {@code changes} array in a {@code} object.
     */
    public static final String CHANGES = "changes";

    /**
     * Parses a JSON array to a list of changes.
     *
     * @param jsonArray JSON array that represents list of changes
     * @return list of changes
     */
    protected static List<Change> parseChanges(final JsonArray jsonArray)
    {
        if (jsonArray == null) {
            return null;
        }

        List<Change> changes = new ArrayList<Change>();
        for (JsonValue value : jsonArray) {
            changes.add(new Change((JsonObject) value));
        }
        return changes;
    }

    /**
     * Constructs this object from a JSON event object.
     *
     * @param eventObject JSON event object
     */
    public RepositoryPush(final JsonObject eventObject)
    {
        super(eventObject);
    }

    /**
     * Returns the list of the changes of this object.
     *
     * @return list of the changes
     */
    public final List<Change> getChanges()
    {
        JsonObject push = getJsonObject().getJsonObject(PUSH);
        return parseChanges(push.getJsonArray(CHANGES));
    }

    /**
     * Change in a Bitbucket repository.
     */
    public static class Change
    {
        /**
         * JSON change object given to the constructor.
         */
        private final JsonObject jsonObject;

        /**
         * Old state.
         */
        private WebhookBranch oldState;

        /**
         * New state.
         */
        private WebhookBranch newState;

        /**
         * Commits in this change.
         */
        private List<Commit> commits;

        /**
         * Constructs this change with a JSON change object.
         *
         * @param changeObject JSON change object
         */
        public Change(final JsonObject changeObject)
        {
            jsonObject = changeObject;
            oldState = new WebhookBranch(changeObject.getJsonObject(
                    WebhookJsonKeys.OLD));
            newState = new WebhookBranch(changeObject.getJsonObject(
                    WebhookJsonKeys.NEW));
            // TODO: Parse commits.
        }

        /**
         * Returns the JSON change object given to the constructor.
         *
         * @return the JSON change object
         */
        public final JsonObject getJsonObject()
        {
            return jsonObject;
        }

        /**
         * Returns {@code true} if this change created a branch.
         *
         * @return {@code true} if created
         */
        public final boolean isCreated()
        {
            JsonObject changeObject = getJsonObject();
            return changeObject.getBoolean(WebhookJsonKeys.CREATED);
        }

        /**
         * Returns {@code true} if this change closed a branch.
         *
         * @return {@code true} if closed
         */
        public final boolean isClosed()
        {
            JsonObject changeObject = getJsonObject();
            return changeObject.getBoolean(WebhookJsonKeys.CLOSED);
        }

        /**
         * Returns {@code true} if this change was forced.
         *
         * @return {@code true} if forced
         */
        public boolean isForced()
        {
            JsonObject changeObject = getJsonObject();
            return changeObject.getBoolean(WebhookJsonKeys.FORCED);
        }

        public final WebhookBranch getOldState() {
            return oldState;
        }

        public final WebhookBranch getNewState() {
            return newState;
        }

        public final List<Commit> getCommits() {
            return commits;
        }
    }
}
