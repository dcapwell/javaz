package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.Maybe;
import org.testng.annotations.Test;

@Test
public final class MaybeExample {

  public void fromNull() {
    Maybe<String> value = Maybe.of(null);
    Maybe<Integer> sized = value.map(data -> data.length());
    Maybe<Character> first = sized.flatMap(i -> {
      if (i > 10) return Maybe.none();
      else return Maybe.some(Integer.toString(i).charAt(0));
    });

    System.out.println(first.isDefined());

    System.out.println(Maybe.of("value").map(v -> v.length()).apply());
  }
}
