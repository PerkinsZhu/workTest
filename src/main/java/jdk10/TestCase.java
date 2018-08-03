package jdk10;

import org.junit.Test;

import javax.media.Processor;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by PerkinsZhu on 2018/8/2 19:43
 **/
public class TestCase {

    @Test
    public void test() {
        System.out.printf("----");
        System.out.println(10);
        var a = 10;
        System.out.println(a * 10);

        Arrays.asList(1, 2, 3, 4, 5).forEach((var b) -> System.out.println(b));
    }

    BiFunction<String[], String, String> func1 = (var a, var b) -> b;

    public static void main(String[] args) {
        new TestCase().test();
    }
}
