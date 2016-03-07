package org.cukesalad.context;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class TestUtil {

  @Given("asdasd")
  public void test(String something) {
    
    assertEquals("aspect is working", something);
  }
  
  @Given("asdasd")
  public void testDataTable(DataTable actual) {
    
    List<List<String>> raw = new ArrayList<List<String>>();
    raw.add(Arrays.asList("name","house","place"));
    raw.add(Arrays.asList("Ned","Stark","winterfell"));
    raw.add(Arrays.asList("Tyrion","Lanister","casterly rock"));
    raw.add(Arrays.asList("Drogo","Dothraki", "vas dothrak"));
    raw.add(Arrays.asList("Robert","Baratheon", "storms end"));
    DataTable expected = DataTable.create(raw );
    assertEquals(expected, actual);
  }
}
