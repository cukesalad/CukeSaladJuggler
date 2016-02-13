package org.cukesalad.context;

import java.util.HashMap;
import java.util.Map;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

/**
 * This is the class that help in storing the test context for all the steps. It can be easily used to store and access
 * objects and values between steps which is a limitation in cucumber. You can also use this class to evaluate any
 * expression with the context.
 * 
 * @author a542099
 *
 */
public final class CukeSaladContext {

  // Setting the UserContext in Thread Local
  private static SimpleContext         CURRENTCONTEXT          = new SimpleContext();
  private static ExpressionFactoryImpl EXPRESSION_FACTORY_IMPL = new ExpressionFactoryImpl();
  private static Map<String, Object>   CONTEXTMAP              = new HashMap<String, Object>();

  public static SimpleContext getCurrentContext() {
    return CURRENTCONTEXT;
  }

  public static void remove() {
    CURRENTCONTEXT = new SimpleContext();
    CONTEXTMAP = new HashMap<String, Object>();
  }

  public static void setCurrentContext(final SimpleContext context) {
    CURRENTCONTEXT = context;
  }

  public static Object getFromContext(String name) {
    return CONTEXTMAP.get(name);
  }

  public static void addToContext(String name, Class<?> type, Object value) {
    CukeSaladContext.getCurrentContext().setVariable(name, EXPRESSION_FACTORY_IMPL.createValueExpression(value, type));
    CONTEXTMAP.put(name, value);
  }

  public static String expandExpressions(String nativeString) {
    return (String) EXPRESSION_FACTORY_IMPL
        .createValueExpression(CukeSaladContext.getCurrentContext(), nativeString, nativeString.getClass())
        .getValue(CukeSaladContext.getCurrentContext());
  }

}
