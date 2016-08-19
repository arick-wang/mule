/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.transformers.xml.xslt;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class XsltTransformerBLEnabledTestCase extends XsltTransformerBLTestCase
{

    @Test
    public void enabled() throws Exception
    {
        String input = makeInput();
        Object payload = input.getBytes();
        String output = (String) runFlow("flow", payload).getMessage().getPayload();
        assertThat(output, containsString("010101010101010101010101010101010101010101010101"));
    }

}
