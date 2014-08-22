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
  public List<A> cons(List<A> as) {
    if (as.isEmpty()) return this;
    else {
      // reverse the as, then iterate over each, cons with the new list
      List<A> rev = as.reverse();
      List<A> newList = this;
      while (! rev.isEmpty()) {
        newList = newList.cons(rev.head());
        rev = rev.tail();
      }
      return newList;
    }
  }

  @Override
  public List<A> reverse() {
    List<A> as = this;
    List<A> result = List.nil();
    while (! as.isEmpty()) {
      result = result.cons(as.head());
      as = as.tail();
    }
    return result;
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
    return tail.flatMap(fn).cons(fn.apply(head));
  }

  @Override
  public void foreach(Block<A> fn) {
    fn.apply(head);
    tail.foreach(fn);
  }
}
