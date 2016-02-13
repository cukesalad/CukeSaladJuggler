package org.cukesalad.juggler;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cukesalad.context.CukeSaladContext;

import cucumber.api.DataTable;

@Aspect
public class ContextJuggler {

  @Pointcut("execution(@cucumber.api.java.*.* * *.*(..))")
  public void cukestep() {};

  @Around("cukestep()")
  public Object juggle(ProceedingJoinPoint thisJoinPoint) throws Throwable {
    Object[] args = new Object[thisJoinPoint.getArgs().length];

    for (int i = 0; i < thisJoinPoint.getArgs().length; i++) {
      Object arg = thisJoinPoint.getArgs()[i];
      if (arg instanceof String) {
        args[i] = CukeSaladContext.expandExpressions((String) arg);
      } else if (arg instanceof DataTable) {
        DataTable dataArg = (DataTable) arg;
        List<List<String>> dataTableRaw = dataArg.raw();
        List<List<String>> updatedRawTable = new ArrayList<List<String>>();
        for (List<String> dataRow : dataTableRaw) {
          List<String> updatedDataRow = new ArrayList<String>();
          for (String value : dataRow) {
            updatedDataRow.add(CukeSaladContext.expandExpressions(value));
          }
          updatedRawTable.add(updatedDataRow);
        }
        
        args[i] = DataTable.create(updatedRawTable);
      }
    }
    return thisJoinPoint.proceed(args);
  }

}
