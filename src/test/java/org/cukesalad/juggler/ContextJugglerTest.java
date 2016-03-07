package org.cukesalad.juggler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cukesalad.context.ContextHook;
import org.cukesalad.context.CukeSaladContext;
import org.cukesalad.context.TestUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class ContextJugglerTest {

  TestUtil testutil;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    testutil = new TestUtil();
    ContextHook hook = new ContextHook();
    hook.beforeHook();
  }

  @After
  public void tearDown() throws Exception {
    ContextHook hook = new ContextHook();
    hook.afterHook();

  }

  @Test
  public void testJuggle() {
    CukeSaladContext.addToContext("status", String.class, "working");
    //CukeSaladContext.getCurrentContext().setVariable("status", new ExpressionFactoryImpl().createValueExpression("working", String.class));
    testutil.test("aspect is ${status}");
  }
  
  @Test
  public void testJuggleDataTable() {
    //setting context
    List<Map<String, String>> contextObject = new ArrayList<Map<String,String>>();
    Map<String, String> character = new HashMap<String, String>();
    character.put("name", "Ned");
    character.put("house", "Stark");
    character.put("place", "winterfell");
    contextObject.add(character);
    character = new HashMap<String, String>();
    character.put("name", "Tyrion");
    character.put("house", "Lanister");
    character.put("place", "casterly rock");
    contextObject.add(character);
    character = new HashMap<String, String>();
    character.put("name", "Drogo");
    character.put("house", "Dothraki");
    character.put("place", "vas dothrak");
    contextObject.add(character);
    character = new HashMap<String, String>();
    character.put("name", "Robert");
    character.put("house", "Baratheon");
    character.put("place", "storms end");
    contextObject.add(character);
    CukeSaladContext.addToContext("characters", List.class, contextObject);
    
    List<List<String>> raw = new ArrayList<List<String>>();
    raw.add(Arrays.asList("name","house","place"));
    raw.add(Arrays.asList("${characters[0].name}","${characters[0].house}","${characters[0].place}"));
    raw.add(Arrays.asList("${characters[1].name}","${characters[1].house}","${characters[1].place}"));
    raw.add(Arrays.asList("${characters[2].name}","${characters[2].house}","${characters[2].place}"));
    raw.add(Arrays.asList("${characters[3].name}","${characters[3].house}","${characters[3].place}"));
    DataTable actual = DataTable.create(raw );

    testutil.testDataTable(actual);
  }
  
}
