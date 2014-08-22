package com.github.dcapwell.javaz.examples;

import com.github.dcapwell.javaz.Show;
import com.github.dcapwell.javaz.syntax.ShowSyntax;
import com.github.dcapwell.javaz.Shows;
import org.testng.annotations.Test;

@Test
public final class ShowsExample {

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

  public void showString() {
    Show<String> show = Shows.shows(a -> a);
    System.out.println(show.shows("Hello World"));

    Person person = new Person("bob", 36);
    Show<Person> personShow = Shows.showsReflect();
    System.out.println(personShow.shows(person));

    // java doesn't have implicits or type class support, so... hack implicits with explicits!
    person.println(); // use inheritance to get these methods
    Shows.syntaxReflect(person).println(); // create a new syntaxReflect object based off person
    Shows.syntaxA("Hello World!").println();

    // what if you want to replace the default, but keep the syntax?

    person.replaceDefault(Shows.showsA()).println();
    person.println(Shows.showsA());
  }
}
