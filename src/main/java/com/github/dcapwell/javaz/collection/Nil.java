package com.github.dcapwell.javaz.collection;

import com.github.dcapwell.javaz.Block;
import com.github.dcapwell.javaz.Fn1;
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
  public List<A> cons(List<A> a) {
    return a;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public <B> List<B> map(Fn1<A, B> fn) {
    return (List<B>) this;
  }

  @Override
  public <B> List<B> flatMap(Fn1<A, List<B>> fn) {
    return (List<B>) this;
  }

  @Override
  public void foreach(Block<A> fn) {
    // no-op
  }

  @Override
  public List<A> reverse() {
    return this;
  }
}
