package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.Eqs;
import org.testng.annotations.Test;

@Test
public final class EqExample {

  public void primativeEq() {
    System.out.println(Eqs.natural().eq(1, 2));
    System.out.println(Eqs.natural().eq(1, 1));

    System.out.println(Eqs.syntaxNatural(1).notEq(2));
    System.out.println(Eqs.syntaxNatural(1).eq(1));

//    Eqs.<Integer>natural().eq(1, ";_;"); // won't compile
  }
}
