package com.github.dcapwell.javaz;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public final class TryTest {
  private static String throwError() {
    throw new RuntimeException("throwError");
  }

  private static String data() {
    return "data";
  }

  public void tryFrom() {
    Try<String> t = Try.from(TryTest::data);
    Assert.assertTrue(t.isDefined());
    Assert.assertEquals(t.asSuccess().get(), "data");
  }

  public void tryFromFailure() {
    Try<String> t = Try.from(TryTest::throwError);
    Assert.assertFalse(t.isDefined());
    Assert.assertEquals(t.asFailure().getThrowable().getMessage(), "throwError");
  }

  public void tryInline() {
    Try<String> t = Try.from(() -> {throw new RuntimeException("inline"); });
    Assert.assertFalse(t.isDefined());
    Assert.assertEquals(t.asFailure().getThrowable().getMessage(), "inline");
  }

  public void map() {
    Try<Integer> t = Try.from(TryTest::data).map(data -> data.length());
    Assert.assertTrue(t.isDefined());
    Assert.assertEquals(t.asSuccess().get(), Integer.valueOf(4));
  }

  public void flatMap() {
    Try<String> t = Try.from(TryTest::data).flatMap(data -> Try.from(TryTest::data));
    Assert.assertTrue(t.isDefined());
    Assert.assertEquals(t.asSuccess().get(), "data");
  }

  public void mapError() {
    Try<Integer> t = Try.from(TryTest::throwError).map(data -> data.length());
    Assert.assertFalse(t.isDefined());
    Assert.assertEquals(t.asFailure().getThrowable().getMessage(), "throwError");
  }

  public void flatMapError() {
    Try<String> t = Try.from(TryTest::throwError).flatMap(data -> Try.from(TryTest::data));
    Assert.assertFalse(t.isDefined());
    Assert.assertEquals(t.asFailure().getThrowable().getMessage(), "throwError");
  }
}
