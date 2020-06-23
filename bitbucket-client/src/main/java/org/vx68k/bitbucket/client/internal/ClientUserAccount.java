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
    public ClientUserAccount(final BitbucketUserAccount other)
    {
        super(other);

        this.staff = other.isStaff();
        this.accountId = other.getAccountId();
    }

    public ClientUserAccount copy()
    {
        return new ClientUserAccount(this);
    }

    @Override
    public final AccountType getType()
    {
        return AccountType.USER;
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
