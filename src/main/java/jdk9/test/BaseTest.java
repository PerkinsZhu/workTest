package jdk9.test;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by PerkinsZhu on 2017/12/6 11:36.
 */
public class BaseTest {

    /*@Test
    public void testBase(){
        Set<Integer> ints = Set.of(1, 2, 3);
        List<String> strings = List.of("first", "second");
        ints.forEach((Integer i ) -> System.out.printf("i"));
        IntStream.iterate(1, i -> i < 100, i -> i + 1).forEach(System.out::println);
        Stream<Integer> s = Optional.of(1).stream();
        List<String> tempList = new ArrayList<>();
        Properties properties = System.getProperties();
        properties.forEach((Object key,Object value) -> System.out.println(key+"--->"+value));
        String [] array = new String[]{"a"};
        List<String> names =  Arrays.asList(array);
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        System.out.printf(names.get(0));
    }*/
 /*   @Test
    public void testHttp2(){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest req =
                HttpRequest.newBuilder(URI.create("http://www.google.com"))
                        .header("User-Agent","Java")
                        .GET()
                        .build();
        try {
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandler.asString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void test8() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);

        Converter<String, Integer> converter2 = Integer::valueOf;//通过::关键字获取方法或者构造函数的的引用，这里的方法可以是对象调用实例方法obj::showInfo 也可以使通过类名调用静态方法
        Integer converted2 = converter.convert("123");
        System.out.println(converted);   // 123

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.printf(person.toString());

    }
}
interface Student{
    default void showInfo(){
        System.out.println(getInfo());
    }
    default String getInfo(){
        return "hekki";
    }
}

class Person {
    String firstName;
    String lastName;
    Person() {}
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
//@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
    boolean equals(Object obj);
}
