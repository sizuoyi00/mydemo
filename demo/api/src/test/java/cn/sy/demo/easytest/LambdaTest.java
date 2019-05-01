package cn.sy.demo.easytest;

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

        List<String> list1 = list.stream().map(l -> l.toUpperCase())
                .collect(Collectors.toList());

        List<Integer> list2 = Arrays.asList(2,3,6);
        final List<String> collect2 = list2.stream().map(l -> l + "haha")
                .collect(Collectors.toList());

        //person -> person.getAge();
        // 传入参数是person，返回值是person.getAge()，
        // 那么方法引用Person::getAge就对应着Function<Person,Integer>类型。
        list1.forEach(System.out::println);
        collect2.forEach(System.out::println);

    }
}
