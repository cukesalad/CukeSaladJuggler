package org.cukesalad.context;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;

import de.odysseus.el.util.SimpleContext;

public class ContextHookTest {

  @Test
  public void testRefresh() throws ParserConfigurationException {
    SimpleContext elcontext = new SimpleContext();
    elcontext.putContext(String.class, "asdasd");
    CukeSaladContext.setCurrentContext(elcontext);
    assertNotNull(CukeSaladContext.getCurrentContext().getContext(String.class));
    ContextHook.refresh();
    assertNull(CukeSaladContext.getCurrentContext().getContext(String.class));
  }

  @Test
  public void testBeforeHook() throws ParserConfigurationException {
    ContextHook hook = new ContextHook();
    hook.beforeHook();
    assertNotNull(CukeSaladContext.getCurrentContext());
    CukeSaladContext.getCurrentContext().putContext(String.class, "asdasd");
    assertNotNull(CukeSaladContext.getCurrentContext().getContext(String.class));
    hook.beforeHook();
    assertNull(CukeSaladContext.getCurrentContext().getContext(String.class));
  }

  @Test
  public void testAfterHook() throws ParserConfigurationException, IOException {
    ContextHook hook = new ContextHook();
    hook.beforeHook();
    assertNotNull(CukeSaladContext.getCurrentContext());
    CukeSaladContext.getCurrentContext().putContext(String.class, "asdasd");
    assertNotNull(CukeSaladContext.getCurrentContext().getContext(String.class));
    hook.afterHook();
    assertNull(CukeSaladContext.getCurrentContext().getContext(String.class));
    
  }

}
