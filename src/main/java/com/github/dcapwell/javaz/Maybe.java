package com.github.dcapwell.javaz;

import com.github.dcapwell.javaz.internal.Preconditions;

public abstract class Maybe<A> implements Fn0<A> {

  public abstract boolean isDefined();

  /**
   * Throws {@link java.lang.IllegalStateException} if {@link #isDefined()} is false.
   */
  @Override
  public abstract A apply() throws IllegalStateException;

  public abstract <B> Maybe<B> map(Fn1<A, B> fn);

  public abstract <B> Maybe<B> flatMap(Fn1<A, Maybe<B>> fn);

  public abstract void foreach(Block<A> fn);

  public static <A> Maybe<A> some(A a) {
    return new Some<>(Preconditions.checkNotNull(a));
  }

  public static <A> Maybe<A> none() {
    return (Maybe<A>) NONE;
  }

  public static <A> Maybe<A> of(A a) {
    if (a == null) {
      return none();
    } else {
      return some(a);
    }
  }

  private static final class None<A> extends Maybe<A> {

    @Override
    public boolean isDefined() {
      return false;
    }

    @Override
    public A apply() throws IllegalStateException {
      throw new IllegalStateException("None");
    }

    @Override
    public <B> Maybe<B> map(Fn1<A, B> fn) {
      return (Maybe<B>) this;
    }

    @Override
    public <B> Maybe<B> flatMap(Fn1<A, Maybe<B>> fn) {
      return (Maybe<B>) this;
    }

    @Override
    public void foreach(Block<A> fn) {
      // no-op
    }
  }

  private static final None<Object> NONE = new None<>();

  private static final class Some<A> extends Maybe<A> {
    private final A value;

    private Some(A value) {
      this.value = value;
    }

    @Override
    public boolean isDefined() {
      return true;
    }

    @Override
    public A apply() throws IllegalStateException {
      return value;
    }

    @Override
    public <B> Maybe<B> map(Fn1<A, B> fn) {
      return of(fn.apply(value));
    }

    @Override
    public <B> Maybe<B> flatMap(Fn1<A, Maybe<B>> fn) {
      return fn.apply(value);
    }

    @Override
    public void foreach(Block<A> fn) {
      fn.apply(value);
    }
  }
}
