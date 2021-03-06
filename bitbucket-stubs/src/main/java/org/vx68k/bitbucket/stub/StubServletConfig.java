/*
 * StubServletConfig.java - class StubServletConfig
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

package org.vx68k.bitbucket.stub;

import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * Stub implementation of {@link ServletConfig}.
 *
 * @author Kaz Nishimura
 * @since 6.0
 */
public class StubServletConfig implements ServletConfig
{
    /**
     * {@link ServletContext} object given to the constructor.
     */
    private final ServletContext servletContext;

    /**
     * Constructs this configuration with a {@link ServletContext} object.
     *
     * @param context {@link ServletContext} object
     */
    public StubServletConfig(final ServletContext context)
    {
        servletContext = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServletName()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns the {@link ServletContext} object given
     * to the constructor.</p>
     *
     * @return the {@link ServletContext} object
     */
    @Override
    public final ServletContext getServletContext()
    {
        return servletContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitParameter(final String name)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enumeration<String> getInitParameterNames()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
