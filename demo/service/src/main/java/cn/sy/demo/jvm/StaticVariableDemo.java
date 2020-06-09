package cn.sy.demo.jvm;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

/**
 * 静态变量存放位置demo
 */
public class StaticVariableDemo {

    public static void main(String[] args) {
        //对象的大小不计算静态变量
        long objectSize = ObjectSizeCalculator.getObjectSize(new A());
        long objectSize2 = ObjectSizeCalculator.getObjectSize(new B());
        System.out.println(objectSize);//40
        System.out.println(objectSize2);//40
    }
}

class A {
    int m0;
    long m1;
    double m2;
    String m3;
}

class B {
    int m0;
    long m1;
    double m2;
    String m3;
    static int n0;
    static long n1;
    static double n2 = 1;
    static String n3 = "1";
}
