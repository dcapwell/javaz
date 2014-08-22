package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.Fn1;
import com.github.dcapwell.javaz.Memo;
import org.testng.annotations.Test;

@Test
public final class MemoExample {
  private long slowDouble(long value) {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return value * 2;
  }

  public void memoSlowFunction() {
    Fn1<Long, Long> fn = Memo.<Long, Long>mutableHashMapMemo().apply(this::slowDouble);

    long start, end;
    Long key = Long.valueOf(2);
    for (int i = 0; i < 10; i++) {
      start = System.nanoTime();
      long result = fn.apply(key);
      end = System.nanoTime();
      System.out.println((end - start) + "ns produced " + result);
    }
  }
}
