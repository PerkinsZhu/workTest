package jdk11;

import org.junit.Test;
import scala.Int;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by PerkinsZhu on 2018/9/29 14:50
 **/
public class TestCase {


    @Test
    public void testVar() {
        var num = 100;
        List.of(1, 2, 3, 4, 5).forEach(i -> {
            System.out.println(i);
            System.out.println(i * num);
        });
        // num += 100;
        System.out.println(num);
        Optional.of("hello").orElseThrow();
        "hello".strip();
        System.out.println(Character.toString(65));
        Stream.of(1, 2, 3, 2, 1).dropWhile(n -> n < 3).collect(Collectors.toList()).forEach(i -> System.out.println(i));
    }

    @Test
    public void testHttpClientGet() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder().uri(URI.create("http://gc.ditu.aliyun.com/geocoding?a=%E8%8B%8F%E5%B7%9E%E5%B8%82")).GET().build();
        var client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        System.out.println("-------测试异步请求---------");
        var requestSync = HttpRequest.newBuilder().uri(URI.create("http://gc.ditu.aliyun.com/geocoding?a=%E8%8B%8F%E5%B7%9E%E5%B8%82")).build();
        var clientSync = HttpClient.newHttpClient();
        clientSync.sendAsync(requestSync, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println);
        Thread.sleep(Int.MaxValue());

    }

    @Test
    public void testHttpClientPost() throws IOException, InterruptedException {

        var request = HttpRequest.newBuilder().uri(URI.create("https://postman-echo.com/post")).header("Content-Type", "text/plain").POST(HttpRequest.BodyPublishers.ofString("Hi there!")).build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        System.out.println("-------测试异步请求---------");
        var requestSync = HttpRequest.newBuilder().uri(URI.create("https://postman-echo.com/basic-auth")).build();
        var clientSync = HttpClient.newBuilder().authenticator(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("postman", "password".toCharArray());
            }
        }).build();
        var responseSync = clientSync.send(requestSync, HttpResponse.BodyHandlers.ofString());
        System.out.println(responseSync.statusCode());      // 200
        System.out.println(responseSync.body());

        Thread.sleep(Int.MaxValue());
    }

    @Test
    public void testImmutableCollection() {
        var list = List.of(1, 2, 3, 4, 5);
        var copyList = List.copyOf(list);
        System.out.println(list == copyList);
        //        list.add(10);     UnsupportedOperationException
        System.out.println(list.size());

        var newList = List.of(1, 2, 3, 4, 5);
        System.out.println(list == newList);


    }
}
