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

  public void andThen() {
    Fn1<String, Integer> fn1 = str -> str.length();
    Fn1<Integer, String> fn2 = i -> Integer.toString(i);

    Fn1<String, String> fn3 = fn1.andThen(fn2);
    Assert.assertEquals(fn3.apply("1"), "1");
    Assert.assertEquals(fn3.apply("10"), "2");
  }

  public void compose() {
    Fn1<String, Integer> fn1 = str -> str.length();
    Fn1<Integer, String> fn2 = i -> Integer.toString(i);

    Fn1<String, String> fn3 = fn2.compose(fn1);
    Assert.assertEquals(fn3.apply("1"), "1");
    Assert.assertEquals(fn3.apply("10"), "2");
  }
}
