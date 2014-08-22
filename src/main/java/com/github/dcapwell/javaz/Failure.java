package com.github.dcapwell.javaz;

public final class Failure<A> extends Try<A> {
  private final Throwable throwable;

  Failure(Throwable throwable) {
    this.throwable = throwable;
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  @Override
  public <B> Try<B> map(Fn1<A, B> fn) {
    return (Try<B>) this;
  }

  @Override
  public <B> Try<B> flatMap(Fn1<A, Try<B>> fn) {
    return (Try<B>) this;
  }

  @Override
  public void foreach(Block<A> fn) {
    // no-op
  }
}
