package cn.sy.demo.datastructure.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试List部分属性
 */
public class TestList {

    public static void main(String[] args) {

        //ArrayList equals
        List<String> list1 = new ArrayList<>();
        list1.add("s");
        list1.add("s123");
        list1.add("fdgw");


        List<String> list2 = new ArrayList<>();
        list2.add("s");
        list2.add("s123");
        list2.add("fdgw");

        List<String> list3 = new ArrayList<>();
        list3.add("s123");
        list3.add("s");
        list3.add("fdgw");

        //只有两个list顺序一致 且 值一致  才为true
        //true
        System.out.println(list1.equals(list2));
        //false
        System.out.println(list1.equals(list3));

    }
}
