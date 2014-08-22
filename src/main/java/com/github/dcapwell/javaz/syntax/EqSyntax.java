package com.github.dcapwell.javaz.syntax;

import com.github.dcapwell.javaz.Eq;
import com.github.dcapwell.javaz.Eqs;

public interface EqSyntax<A> extends Syntax<A> {

  public default Eq<A> defaultEq() {
    return Eqs.natural();
  }

  public default EqSyntax<A> replaceDefault(final Eq<A> eq) {
    EqSyntax<A> self = this;
    return new EqSyntax<A>() {
      @Override
      public Eq<A> defaultEq() {
        return eq;
      }

      @Override
      public A self() {
        return self.self();
      }
    };
  }

  public default boolean eq(A a) {
    return defaultEq().eq(self(), a);
  }

  public default boolean eq(Eq<A> eq, A a) {
    return eq.eq(self(), a);
  }

  public default boolean notEq(A a) {
    return ! eq(a);
  }

  public default boolean notEq(Eq<A> eq, A a) {
    return ! eq(eq, a);
  }
}
