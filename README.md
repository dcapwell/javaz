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

## Maybe

Java 8 added Optional, Scala has Option, but screw that, lets go with Maybe (haskell)!

```java
Maybe<String> value = Maybe.of(null);
Maybe<Integer> sized = value.map(data -> data.length());
Maybe<Character> first = sized.flatMap(i -> {
    if (i > 10) return Maybe.none();
    else return Maybe.some(Integer.toString(i).charAt(0));
  });

System.out.println(first.isDefined());
// false
System.out.println(Maybe.of("value").map(v -> v.length()).apply());
// 5
```

## Try

Try is like maybe, but rather than a none or empty case, there is a `Throwable` case.

```java
Try<String> data = Try.from(() -> "data");
if (data.isDefined()) System.out.println(data.asSuccess().get());
else data.asFailure().getThrowable().printStackTrace();

// data

Try<String> error = Try.from(() -> {throw new RuntimeException("error");});
if (error.isDefined()) System.out.println(error.asSuccess().get());
else error.asFailure().getThrowable().printStackTrace();

// java.lang.RuntimeException: error
```

## List

Java's lists are all mutable.  Guava adds immutable, but they cant be used with recursion cleanly.  So, we provide our own lists!

```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
System.out.println(list.head());
// 1
System.out.println(list.tail().head());
// 2

System.out.println("map then foreach");
list.map(i -> i * 2).foreach(System.out::println);
// 2
// 4
// 6
// 8
// 10
```

## Type Classes

Wait... What?!??!?!  Yes, you can do type classes in java!... its just not as clean...  But same idea!

### Show

```java
Show<String> show = Shows.shows(a -> a);
System.out.println(show.shows("Hello World"));
// Hello World
```

Thats nice, but what about real objects?

```java
public static final class Person {
  private final String name;
  private final int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}


Person person = new Person("bob", 36);
Show<Person> personShow = Shows.showsReflect();
System.out.println(personShow.shows(person));
// Person(name=bob, age=36)
```

"But I want the purdy syntax of scala!"... Fine!  You can have your implicits, just make them explicit!...

```java
Shows.syntaxReflect(person).println();
// Person(name=bob, age=36)
Shows.syntaxA("Hello World!").println();
// Hello World!
```

Java is all about inheritance...  so is scala!

```java
public static final class Person implements ShowSyntax<Person> {
  private final String name;
  private final int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}

Person person = new Person("bob", 36);
person.println();
```

I want to replace the default show, but keep everything else the same!

```java
person.println(Shows.showsA());
// com.github.dcapwell.javaz.examples.ShowsExample$Person@3498ed
person.replaceDefault(Shows.showsA()).println();
// com.github.dcapwell.javaz.examples.ShowsExample$Person@3498ed
```

### Eq

```java
System.out.println(Eqs.natural().eq(1, 2));
// false
System.out.println(Eqs.natural().eq(1, 1));
// true

System.out.println(Eqs.syntaxNatural(1).notEq(2));
// true
System.out.println(Eqs.syntaxNatural(1).eq(1));
// true

Eqs.<Integer>natural().eq(1, ";_;"); // won't compile
```
