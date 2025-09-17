package com.savdev.example.logging.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JclLib {

  private static final Log logger = LogFactory.getLog(JclLib.class);

  public void performDebugAction() {
    logger.debug("Performing action...");
  }
}
