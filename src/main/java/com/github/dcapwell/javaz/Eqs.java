package com.github.dcapwell.javaz;

import com.github.dcapwell.javaz.syntax.EqSyntax;

public final class Eqs {
  private Eqs() {}

  public static <A> Eq<A> natural() {
    return new Eq<A>() {
      @Override
      public boolean eq(A a1, A a2) {
        return a1.equals(a2);
      }
    };
  }

  public static <A> EqSyntax<A> syntax(Eq<A> eq, A a) {
    return new EqSyntax<A>() {
      @Override
      public Eq<A> defaultEq() {
        return eq;
      }

      @Override
      public A self() {
        return a;
      }
    };
  }

  public static <A> EqSyntax<A> syntaxNatural(A a) {
    return syntax(natural(), a);
  }
}
