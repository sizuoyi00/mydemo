package cn.sy.demo.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * jmm -args: -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class TestOOM {

    public byte[] placeHolder = new byte[64 * 1024];

    public static void fillHead(int num) throws InterruptedException {
        List<TestOOM> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //稍作延迟，另监视曲线变化更明显
            Thread.sleep(50);
            System.out.println(1);
            list.add(new TestOOM());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fillHead(1000);
        System.gc();
    }
}
