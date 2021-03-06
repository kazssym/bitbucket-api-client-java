/*
 * ClientUserAccount.java
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

package org.vx68k.bitbucket.client.internal;

import javax.json.bind.annotation.JsonbProperty;
import org.vx68k.bitbucket.BitbucketUserAccount;

/**
 * Client implementation class of {@link BitbucketUserAccount} for the
 * {@code "user"} objects.
 *
 * @author Kaz Nishimura
 * @since 6.0
 */
public class ClientUserAccount extends ClientAccount
    implements BitbucketUserAccount
{
    public static final String USER = "user";

    private String type;

    private boolean staff = false;

    private String accountId;

    /**
     * Constructs a user account.
     */
    public ClientUserAccount()
    {
        // Nothing to do.
    }

    /**
     * Constructs a user account copying another.
     *
     * @param other another user account
     */
    public ClientUserAccount(final ClientUserAccount other)
    {
        super(other);
        this.type = other.type;
        this.staff = other.staff;
        this.accountId = other.accountId;
    }

    public ClientUserAccount copy()
    {
        return new ClientUserAccount(this);
    }

    public final String getType()
    {
        return type;
    }

    public final void setType(final String type)
    {
        if (type != null && !(type.equals(USER))) {
            throw new IllegalArgumentException("Type is not of user account objects");
        }
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @JsonbProperty("is_staff")
    @Override
    public final boolean isStaff()
    {
        return staff;
    }

    /**
     * Set the staff flag of the user account.
     *
     * @param staff a Boolean value for the staff flag
     */
    @JsonbProperty("is_staff")
    public final void setStaff(final boolean staff)
    {
        this.staff = staff;
    }

    /**
     * {@inheritDoc}
     */
    @JsonbProperty("account_id")
    @Override
    public final String getAccountId()
    {
        return accountId;
    }

    @JsonbProperty("account_id")
    public final void setAccountId(final String accountId)
    {
        this.accountId = accountId;
    }
}
