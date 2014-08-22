package com.github.dcapwell.javaz.collection;

import com.github.dcapwell.javaz.internal.Preconditions;

final class Nil<A> extends List<A> {

  @Override
  public A head() throws IllegalStateException {
    throw new IllegalStateException("Nil.head");
  }

  @Override
  public List<A> tail() {
    return this;
  }

  @Override
  public List<A> cons(A a) {
    return new Cons<>(Preconditions.checkNotNull(a), this);
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
