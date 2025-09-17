package com.savdev.example.logging.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JclV1_3Lib {

  private static final Log logger = LogFactory.getLog(JclV1_3Lib.class);

  public void performDebugAction() {
    logger.debug("Performing action...");
  }
}
