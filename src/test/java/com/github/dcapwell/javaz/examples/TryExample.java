package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.Try;
import org.testng.annotations.Test;

@Test
public final class TryExample {

  public void tryPass() {
    Try<String> data = Try.from(() -> "data");
    if (data.isDefined()) System.out.println(data.asSuccess().get());
    else data.asFailure().getThrowable().printStackTrace();

    Try<String> error = Try.from(() -> {throw new RuntimeException("error");});
    if (error.isDefined()) System.out.println(error.asSuccess().get());
    else error.asFailure().getThrowable().printStackTrace();
  }
}
