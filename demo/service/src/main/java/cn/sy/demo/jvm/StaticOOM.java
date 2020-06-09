package cn.sy.demo.jvm;

public class StaticOOM {
    static String[] s0 = new String[1024 * 1024];
    static String[] s1 = new String[1024 * 1024];
    static String[] s2 = new String[1024 * 1024];
    static String[] s3 = new String[1024 * 1024];
    static String[] s4 = new String[1024 * 1024];
    static String[] s5 = new String[1024 * 1024];
    static String[] s6 = new String[1024 * 1024];
    static String[] s7 = new String[1024 * 1024];
    static String[] s8 = new String[1024 * 1024];
    static String[] s9 = new String[1024 * 1024];
    static String[] s10 = new String[1024 * 1024];

    /**
     * -Xms10m -Xmx10m
     * java.lang.OutOfMemoryError: Java
     * heap space
     * <p>
     * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
     * 正常输出
     * <p>
     * 结论:不会导致元空间溢出，即静态变量存储在堆中
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("end");
    }
}