package com.github.dcapwell.javaz.internal;

public final class Preconditions {
  private Preconditions() {}

  public static <A> A checkNotNull(A a) {
    if (a == null) {
      throw new NullPointerException();
    }
    return a;
  }
}
