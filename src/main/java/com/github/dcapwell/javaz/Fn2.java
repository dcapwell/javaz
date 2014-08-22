package com.github.dcapwell.javaz;

public interface Fn2<A, B, C> {
  public C apply(A a, B b);

  public default Fn1<A, Fn1<B, C>> curried() {
    return Functions.curried(this);
  }
}
