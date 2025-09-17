package com.savdev.example.logging.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JulLib {

  private static final Logger logger = Logger.getLogger(JulLib.class.getName());

  public void performDebugAction() {
    //there is no debug level
    //Level.FINEST - has the lowest logging weight
    logger.log(Level.FINER, "Performing action...");
  }
}
