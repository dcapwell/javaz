package com.github.dcapwell.javaz;

public final class Functions {
  private Functions() { }

  public static <A> Fn0<A> of(Fn0<A> fn) {
    return fn;
  }

  public static <A, B> Fn1<A, B> of(Fn1<A, B> fn) {
    return fn;
  }

  public static <A, B, C> Fn2<A, B, C> of(Fn2<A, B, C> fn) {
    return fn;
  }

  public static <A, B, C> Fn1<A, Fn1<B, C>> curried(final Fn2<A, B, C> fn) {
    return a -> b -> fn.apply(a, b);
  }

  public static <A, B, C> Fn1<A, C> andThen(Fn1<A, B> fn1, Fn1<B, C> fn2) {
    return a -> fn2.apply(fn1.apply(a));
  }

  public static <A, B, C> Fn1<C, B> compose(Fn1<A, B> fn1, Fn1<C, A> fn2) {
    return c -> fn1.apply(fn2.apply(c));
  }
}
