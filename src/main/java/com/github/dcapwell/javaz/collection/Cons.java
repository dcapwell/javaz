package com.github.dcapwell.javaz.collection;

import com.github.dcapwell.javaz.Block;
import com.github.dcapwell.javaz.Fn1;
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

  @Override
  public <B> List<B> map(Fn1<A, B> fn) {
    return tail.map(fn).cons(fn.apply(head));
  }

  @Override
  public <B> List<B> flatMap(Fn1<A, List<B>> fn) {
    return null;
  }

  @Override
  public void foreach(Block<A> fn) {
    fn.apply(head);
    tail.foreach(fn);
  }
}
