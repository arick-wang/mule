/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.transformer.simple;

import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;
import org.mule.util.AttributeEvaluator;

public class CopyPropertiesTransformer extends AbstractMessageTransformer
{
    private AttributeEvaluator keyEvaluator;

    public CopyPropertiesTransformer()
    {
        registerSourceType(DataTypeFactory.OBJECT);
        setReturnDataType(DataTypeFactory.OBJECT);
    }

    @Override
    public void initialise() throws InitialisationException
    {
        super.initialise();
        this.keyEvaluator.initialize(muleContext.getExpressionManager());
    }

    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException
    {
        if (keyEvaluator.isExpression() || keyEvaluator.isPlainText())
        {
            Object keyValue = keyEvaluator.resolveValue(message);
            if (keyValue != null)
            {
                String propertyName = keyValue.toString();
                Object propertyValue = message.getInboundProperty(propertyName);
                if (propertyValue != null)
                {
                    message.setOutboundProperty(propertyName, propertyValue);
                }
                else
                {
                    logger.info("Property value for is null, no property will be copied");
                }
            }
            else
            {
                logger.info("Key expression return null, no property will be copied");
            }
        }
        else
        {
            for (String inboundPropertyName : message.getInboundPropertyNames())
            {
                if (keyEvaluator.matches(inboundPropertyName))
                {
                    message.setOutboundProperty(inboundPropertyName,message.getInboundProperty(inboundPropertyName));
                }
            }
        }
        return message;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        CopyPropertiesTransformer clone = (CopyPropertiesTransformer) super.clone();
        clone.setKey(this.keyEvaluator.getRawValue());
        return clone;
    }

    public void setKey(String key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException("Null key not supported");
        }
        this.keyEvaluator = new AttributeEvaluator(key).enableRegexSupport();
    }

}
