package com.github.dcapwell.javaz.collection;

import com.github.dcapwell.javaz.internal.Preconditions;

final class Cons<A> extends List<A> {
  private final A head;
  private final List<A> tail;

  Cons(A value, List<A> tail) {
    this.head = value;
    this.tail = tail;
  }

  @Override
  public A head() throws IllegalStateException {
    return head;
  }

  @Override
  public List<A> tail() {
    return tail;
  }

  @Override
  public List<A> cons(A a) {
    return new Cons<>(Preconditions.checkNotNull(a), this);
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}
