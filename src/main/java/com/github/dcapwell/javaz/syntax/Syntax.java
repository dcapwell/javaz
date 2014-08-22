package com.github.dcapwell.javaz.syntax;

public interface Syntax<A> {
  public default A self() {
    return (A) this;
  }
}
