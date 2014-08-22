# Javaz

I was forced to go back to java and I got bored, so I created this lib.

PLEASE DON'T USE THIS LIB!  I REALLY JUST GOT BORED AND WANTED TO WRITE THIS.

## Functions

Function 0 - n (currently 2) are defined.

Fn0

```java
Fn0<String> fn1 = () -> "Hello World!";
System.out.println(fn1.apply());
// Hello World!
System.out.println(fn1.apply());
// Hello World!
```

Fn1

```java
Fn1<String, Integer> fn1 = str -> str.length();
Fn1<Integer, String> fn2 = i -> Integer.toString(i);

Fn1<String, String> fn3 = fn1.andThen(fn2);
Fn1<String, String> fn4 = fn2.compose(fn1);

System.out.println(fn3.apply("10") + " == " + fn4.apply("10"));
// 2 == 2
```

Fn2 (to n)

```java
Fn2<String, String, String> concat = (a, b) -> a + b;

System.out.println(concat.apply("a", "b"));
// ab
Fn1<String, String> fn2 = concat.curried().apply("a"); // partially applied concat with "a"
System.out.println(fn2.apply("b"));
// ab
System.out.println(fn2.apply("c"));
// ac
```

Convert methods and static functions to function objects

```java
public static Integer length(String s) {
  return s.length();
}
...
Fn1<String, Integer> fn = Functions.of(FunctionsExample::length);
System.out.println(fn.apply("foo"));
// 3
```
