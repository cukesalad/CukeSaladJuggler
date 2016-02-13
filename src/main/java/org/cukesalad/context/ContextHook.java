package org.cukesalad.context;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import de.odysseus.el.util.SimpleContext;

public class ContextHook {

  static final Logger LOG = LoggerFactory.getLogger(ContextHook.class);
  
  public static final int ORDER = 0;

  public static void refresh() throws ParserConfigurationException {
    CukeSaladContext.remove();
  }

  @Before(order = ORDER)
  public void beforeHook() throws ParserConfigurationException {
    CukeSaladContext.remove();
    SimpleContext context = new SimpleContext();
    CukeSaladContext.setCurrentContext(context );
  }

  @After
  public void afterHook() throws IOException, ParserConfigurationException {
    refresh();
  }


}
