package com.github.dcapwell.javaz;

public abstract class Try<A> {
  public abstract boolean isDefined();

  public Success<A> asSuccess() {
    return (Success<A>) this;
  }

  public Failure<A> asFailure() {
    return (Failure<A>) this;
  }

  public abstract <B> Try<B> map(Fn1<A, B> fn);

  public abstract <B> Try<B> flatMap(Fn1<A, Try<B>> fn);

  public abstract void foreach(Block<A> fn);

  public static <A> Try<A> from(Fn0<A> fn) {
    try {
      return new Success(fn.apply());
    } catch (Throwable e) {
      return new Failure(e);
    }
  }
}
