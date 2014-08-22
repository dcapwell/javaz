package com.github.dcapwell.javaz;

import com.github.dcapwell.javaz.syntax.ShowSyntax;

import java.lang.reflect.Field;

public final class Shows {
  private Shows() {}

  public static <A> Show<A> shows(final Fn1<A, String> fn) {
    return a -> fn.apply(a);
  }

  public static <A> Show<A> showsA() {
    return (A a)  -> a.toString();
  }

  public static <A> Show<A> showsReflect() {
    return a -> {
      Field[] fields = a.getClass().getDeclaredFields();
      StringBuilder sb = new StringBuilder();
      sb.append(a.getClass().getSimpleName()).append("(");
      for (Field f : fields) { // map ... mkString
        sb.append(f.getName()).append("=").append(getValue(f, a)).append(", ");
      }
      sb.setLength(sb.length() - 2);
      sb.append(")");
      return sb.toString();
    };
  }

  private static <A> Object getValue(Field field, A a) {
    try {
      field.setAccessible(true);
      return field.get(a);
    } catch (IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  public static <A> ShowSyntax<A> syntax(Show<A> show, A a) {
    return new ShowSyntax<A>() {
      @Override
      public A self() {
        return a;
      }

      @Override
      public Show<A> defaultShow() {
        return show;
      }
    };
  }

  public static <A> ShowSyntax<A> syntaxA(A a) {
    return syntax(Shows.showsA(), a);
  }

  public static <A> ShowSyntax<A> syntaxReflect(A a) {
    return syntax(Shows.showsReflect(), a);
  }
}
