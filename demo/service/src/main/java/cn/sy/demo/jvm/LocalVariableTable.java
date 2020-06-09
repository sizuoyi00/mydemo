package cn.sy.demo.jvm;

public class LocalVariableTable {

    public static void main(String[] args) {
        int a = 1;
        long b = 2;

        long sum =test(a, b);

        String c = "2";

        LocalVariableTable localVariableTable = new LocalVariableTable();

        double d = 0D;
    }

    private static long test(int a, long b) {
        long sum = a + b;
        System.out.println(sum);
        return sum;
    }
}
