package com.github.dcapwell.javaz;

import com.github.dcapwell.javaz.internal.Preconditions;

public final class Success<A> extends Try<A> {
  private final A value;

  Success(A value) {
    this.value = Preconditions.checkNotNull(value);
  }

  @Override
  public boolean isDefined() {
    return true;
  }

  public A get() {
    return value;
  }

  @Override
  public <B> Try<B> map(Fn1<A, B> fn) {
    try {
      return new Success(fn.apply(value));
    } catch (Throwable e) {
      return new Failure(e);
    }
  }

  @Override
  public <B> Try<B> flatMap(Fn1<A, Try<B>> fn) {
    try {
      return Preconditions.checkNotNull(fn.apply(value));
    } catch (Throwable e) {
      return new Failure(e);
    }
  }
}
