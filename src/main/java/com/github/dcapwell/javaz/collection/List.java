package com.github.dcapwell.javaz.collection;

import com.github.dcapwell.javaz.Block;
import com.github.dcapwell.javaz.Fn1;
import com.github.dcapwell.javaz.Maybe;
import com.github.dcapwell.javaz.internal.Preconditions;

public abstract class List<A> {

  public abstract A head() throws IllegalStateException;

  public Maybe<A> headMaybe() {
    if (isEmpty()) return Maybe.none();
    else return Maybe.some(head());
  }

  public abstract List<A> tail();

  public abstract List<A> cons(A a);

  public abstract boolean isEmpty();

  public abstract <B> List<B> map(Fn1<A, B> fn);

  public abstract <B> List<B> flatMap(Fn1<A, List<B>> fn);

  public abstract void foreach(Block<A> fn);

  public static <T> List<T> nil() {
    return (List<T>) NIL;
  }

  private static final Nil<?> NIL = new Nil<>();

  public static <T> List<T> of(T... ts) {
    Preconditions.checkNotNull(ts);

    List<T> value = nil();
    for (int i = ts.length - 1; i >= 0; i--) {
      value = value.cons(ts[i]);
    }
    return value;
  }
}
