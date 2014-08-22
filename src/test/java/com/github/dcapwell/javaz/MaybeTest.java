package com.github.dcapwell.javaz;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public final class MaybeTest {

  public void maybeOfNonNeg() {
    Maybe<String> content = Maybe.of("content");
    Assert.assertTrue(content.isDefined());
    Assert.assertEquals(content.apply(), "content");
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void maybeOf() {
    Maybe<String> content = Maybe.of(null);
    Assert.assertFalse(content.isDefined());
    content.apply();
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void maybeOfNone() {
    Maybe<String> content = Maybe.none();
    Assert.assertFalse(content.isDefined());
    content.apply();
  }

  public void maybeOfNonNegSome() {
    Maybe<String> content = Maybe.some("content");
    Assert.assertTrue(content.isDefined());
    Assert.assertEquals(content.apply(), "content");
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void maybeOfSomeNull() {
    Maybe.some(null);
  }

  public void mapIntToString() {
    Maybe<String> maybe = Maybe.some(1).map(n -> Integer.toString(n));
    Assert.assertEquals(maybe.apply(), "1");
  }

  public void flatMapToString() {
    Fn1<String, Maybe<Integer>> fn = data -> {
      try {
        return Maybe.some(Integer.valueOf(data));
      } catch (NumberFormatException e){
        return Maybe.none();
      }
    };

    Assert.assertFalse(Maybe.some("one").flatMap(fn).isDefined());
    Assert.assertTrue(Maybe.some("1").flatMap(fn).isDefined());
  }
}
