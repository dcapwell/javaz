package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.collection.List;
import org.testng.annotations.Test;

@Test
public final class ListExample {

  public void list() {
    List<Integer> list = List.of(1, 2, 3, 4, 5);
    System.out.println(list.head());
    System.out.println(list.tail().head());

    System.out.println("map then foreach");
    list.map(i -> i * 2).foreach(System.out::println);
  }
}
