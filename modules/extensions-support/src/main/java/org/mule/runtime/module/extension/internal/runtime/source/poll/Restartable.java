/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.extension.internal.runtime.source.poll;

import java.util.Map;

/**
 * A component that can be restarted.
 *
 * When restarted {@link #beginRestart()} needs to be called and will return a {@link Map}. After the restart is
 * performed, the {@link #finishRestart(RestartContext)} will be called with the value that the {@link #beginRestart()} method
 * have generated.
 *
 * @since 4.2.3 4.3.1 4.4.0
 */
public interface Restartable {

  /**
   * Method that needs to be called when a restart is performed.
   *
   * @return a context needed after a restart is performed.
   */
  RestartContext beginRestart();

  /**
   * Method that needs to be called to finish the process of restarting.
   *
   * @param restartContext the context generated by {@link #beginRestart()}
   */
  void finishRestart(RestartContext restartContext);
}
