/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.transport.smtp;

import org.mule.providers.soap.axis.transport.VoidURLConnection;

import java.net.URL;
import java.net.URLConnection;

/**
 * A Dummy Url handler for handling smtp. This is needed becuase Axis uses
 * urlStreamHandlers to parse non-http urls.
 */
public class Handler extends java.net.URLStreamHandler
{
    protected URLConnection openConnection(URL url)
    {
        return new VoidURLConnection(url);
    }
}
