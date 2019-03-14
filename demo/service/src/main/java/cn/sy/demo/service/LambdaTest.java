package cn.sy.demo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","c","b");
        list.forEach(System.out::println);
        Collections.sort(list, String::compareTo);
        list.forEach(System.out::println);

        list = list.stream().map(l -> l.toUpperCase())
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }
}
