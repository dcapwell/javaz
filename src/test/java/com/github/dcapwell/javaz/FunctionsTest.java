package com.github.dcapwell.javaz;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public final class FunctionsTest {

  private String echo(String a, String b) {
    return a + b;
  }

  public void curryEcho() {
    Fn1<String, Fn1<String, String>> fn = Functions.curried(this::echo);
    Assert.assertEquals("foobar", fn.apply("foo").apply("bar"));

    Fn1<String, Fn1<String, String>> fn2 = Functions.of(this::echo).curried();
    Assert.assertEquals("foobar", fn2.apply("foo").apply("bar"));
  }
}
