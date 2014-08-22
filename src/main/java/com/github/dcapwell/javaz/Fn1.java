package com.github.dcapwell.javaz;

public interface Fn1<A, B> {
  public B apply(A a);

  public default <C> Fn1<A, C> andThen(Fn1<B, C> fn) {
    return Functions.andThen(this, fn);
  }

  public default <C> Fn1<C, B> compose(Fn1<C, A> fn) {
    return Functions.compose(this, fn);
  }
}
