package com.github.dcapwell.javaz.collection;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public final class ListTest {

  public void nil() {
    List<String> xs = List.nil();
    Assert.assertTrue(xs.isEmpty());
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void nilHead() {
    List.nil().head();
  }

  public void nilCons1() {
    List<String> xs = List.<String>nil().cons("first");
    Assert.assertFalse(xs.isEmpty());

    Assert.assertEquals(xs.tail(), List.nil());
    Assert.assertEquals(xs.head(), "first");
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void nilCons1TailHead() {
    List.nil().cons("first").tail().head();
  }

  public void nilCons2() {
    List<String> xs = List.<String>nil().cons("first").cons("second");
    Assert.assertFalse(xs.isEmpty());

    Assert.assertEquals(xs.tail().getClass(), Cons.class);
    Assert.assertEquals(xs.head(), "second");
    Assert.assertEquals(xs.tail().head(), "first");
  }

  public void nilCons2TailHead() {
    List.nil().cons("first").cons("second").tail().head();
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void nilCons2TailHeadTailTailHead() {
    List.nil().cons("first").cons("second").tail().tail().head();
  }

  public void of() {
    List<Integer> xs = List.of(1, 2, 3, 4, 5);

    Assert.assertFalse(xs.isEmpty());
    Assert.assertEquals(xs.head(), Integer.valueOf(1));
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void ofWithNull() {
    String[] args = null;
    List.of(args);
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void ofWithNullInInput() {
    List.of(1, 2, 3, null);
  }

  public void nilMaybe() {
    Assert.assertFalse(List.nil().headMaybe().isDefined());
  }

  public void consMaybe() {
    Assert.assertTrue(List.nil().cons(1).cons(2).headMaybe().isDefined());
  }
}
