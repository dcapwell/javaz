package com.github.dcapwell.javaz;

public interface ShowSyntax<A> {
  public default Show<A> defaultShow() {
    return Shows.showsReflect();
  }

  public default ShowSyntax<A> replaceDefault(final Show<A> show) {
    ShowSyntax<A> self = this;
    return new ShowSyntax<A>() {
      @Override
      public Show<A> defaultShow() {
        return show;
      }

      @Override
      public A self() {
        return self.self();
      }
    };
  }

  public default A self() {
    return (A) this;
  }

  public default String shows() {
    return defaultShow().shows(self());
  }

  public default void print() {
    System.out.print(shows());
  }

  public default void println() {
    System.out.println(shows());
  }

  public default String shows(Show<A> show) {
    return show.shows(self());
  }

  public default void println(Show<A> show) {
    System.out.println(shows(show));
  }

  public default void print(Show<A> show) {
    System.out.print(shows(show));
  }
}
