package cn.sy.demo.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 逃逸分析demo
 */
public class EscapeAnalysisDemo {
    /**
     * 未优化的代码
     */
    public static int test(int x) {
        int xx = x + 2;
        Point p = new Point(xx, 42);
        return p.getX();
    }

    /**
     * 逃逸分析发现test方法Point对象只作用在当前方法内，不会发生逃逸，对其进行标量替换
     * 优化后的代码
     */
    public static int test_per(int x) {
        int xx = x + 2;
        int px = xx;
        int py = 42; //无效代码最终也会消除
        return px;
    }
}

@Data
@AllArgsConstructor
class Point {
    int x;
    int y;
}