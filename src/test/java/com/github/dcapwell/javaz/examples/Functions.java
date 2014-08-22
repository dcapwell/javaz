package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.Fn0;
import com.github.dcapwell.javaz.Fn1;
import com.github.dcapwell.javaz.Fn2;
import org.testng.annotations.Test;

@Test
public final class Functions {

  public void function0() {
    Fn0<String> fn1 = () -> "Hello World!";
    System.out.println(fn1.apply());
    System.out.println(fn1.apply());
    System.out.println(fn1.apply());
  }

  public void function1() {
    Fn1<String, Integer> fn1 = str -> str.length();
    Fn1<Integer, String> fn2 = i -> Integer.toString(i);

    Fn1<String, String> fn3 = fn1.andThen(fn2);
    Fn1<String, String> fn4 = fn2.compose(fn1);

    System.out.println(fn3.apply("10") + " == " + fn4.apply("10"));
  }

  public void function2() {
    Fn2<String, String, String> concat = (a, b) -> a + b;

    System.out.println(concat.apply("a", "b"));
    Fn1<String, String> fn2 = concat.curried().apply("a"); // partially applied concat with "a"
    System.out.println(fn2.apply("b"));
    System.out.println(fn2.apply("c"));
  }
}
