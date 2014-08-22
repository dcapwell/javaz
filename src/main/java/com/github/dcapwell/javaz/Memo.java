package com.github.dcapwell.javaz;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Memo<K, V> implements Fn1<Fn1<K, V>, Fn1<K, V>> {

  public static <K, V> Memo<K, V> memo(Fn1<Fn1<K, V>, Fn1<K, V>> fn) {
    return new Memo<K, V>() {
      @Override
      public Fn1<K, V> apply(Fn1<K, V> kvFn1) {
        return fn.apply(kvFn1);
      }
    };
  }

  private static <K, V> Memo<K, V> memoMutableMap(final Map<K, V> map) {
    return memo(f -> k -> {
      V value = map.get(k);
      if (value == null) {
        value = f.apply(k);
        map.put(k, value);
      }
      return value;
    });
  }

  public static <K, V> Memo<K, V> mutableHashMapMemo() {
    return memoMutableMap(new HashMap<K, V>());
  }

  public static <K, V> Memo<K, V> weakHashMapMemo() {
    return memoMutableMap(new WeakHashMap());
  }

  public static <K, V> Memo<K, V> concurrentHashMapMemo() {
    return memoMutableMap(new ConcurrentHashMap<>());
  }
}
