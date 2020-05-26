package cn.sy.demo.jvm;

public class TestClassInit {

    /**
     * 主动引用 main方法，触发初始化该类，调用<clinit>()方法
     * <p>
     * 1.<clinit>()方法是由编译期自动收集类的所有“静态变量的赋值动作”和“静态代码块中的语句”合并产生的，收集顺序按语句顺序决定
     * 静态代码块只能访问静态代码块之前的变量，定义在它之后的变量可以赋值（赋值不会生效），不能访问
     * <p>
     * 2.子类<clinit>()方法调用之前，会调用父类的<clinit>()方法
     * <p>
     * 3.父类<clinit>()优先执行，即父类的静态代码块要优先与子类的变量赋值操作
     * <p>
     * 4.<clinit>()非必需，无静态代码块，且无静态变量赋值操作，即不生成<clinit>()方法
     * <p>
     * 5.接口没有静态代码块，接口不需要先执行父接口的<clinit>()方法。
     * <p>
     * 6.<clinit>()方法会被加锁同步初始化，每次只会有一个线程执行这个类的<clinit>()方法，其他线程阻塞等待
     */

    public static void main(String[] args) {
        //0
        System.out.println(i);
        test1();
//        test2();
        //test3();

    }

    /**
     * <clinit>()方法收集顺序
     * 1.static String quality = "150g";
     * 2.static TestClassInit testClassInit = new TestClassInit();
     * * * * 此时触发对象实例化<init>()方法，调用BookTest构造方法，但是不会进入，先收集BookTest成员信息，按顺序收集
     * * * * 2.1.String warehouse = "上海仓";
     * * * * 2.2.System.out.println("书的普通代码块");
     * * * * 2.3.int price = 110;
     * * * * 2.4.BookTest构造方法，此时warehouse,price在前三步已经赋值，saleAmount在编译时已经赋值
     * * * * price=110,amount=0,saleAmount=119,count=0,quality=150g,warehouse=上海仓
     * * * * 成员信息收集完毕，继续进行静态信息收集
     * 3.static int count = 15;
     * 4.static int amount = 112;
     */

    static String quality = "150g";
    String warehouse = "上海仓";
    static TestClassInit testClassInit = new TestClassInit();
    static int count = 15;

    static {
        System.out.println("书的静态代码块");
    }

    TestClassInit() {
        System.out.println("书的构造方法");

        System.out.println("price=" + price + ",amount=" + amount + ",saleAmount=" +

                saleAmount + ",count=" + count + ",quality=" + quality + ",warehouse=" + warehouse);
    }

    {
        System.out.println("书的普通代码块");

    }

    int price = 110;
    static int amount = 112;
    //final普通常量，编译期-准备期间已经赋值
    static final int saleAmount = 119;


    //==============================================//


    //静态代码块只能访问静态代码块之前的变量，定义在它之后的变量可以赋值（赋值不会生效），不能访问
    static {
        //赋值编译没有问题，但是没有效果
        i = 1;
        //读取会编译报错 Error:(10, 28) java: 非法前向引用
//        System.out.println(i);
    }

    static int i = 0;

    private static void test3() {
        //Grandpa class init -> Father class init -> Son class init -> Grandpa init -> Father init -> Son init
        new Son();

    }

    /**
     * 不会触发Son的初始化
     */

    private static void test2() {
        //Grandpa class init -> Father class init ->2
        System.out.println(Father.A);

    }

    /**
     * 2.子类<clinit>()方法调用之前，会调用父类的<clinit>()方法
     * <p>
     * 3.父类<clinit>()优先执行，即父类的静态代码块要优先与子类的变量赋值操作
     */

    private static void test1() {

        //Grandpa class init -> Father class init -> Son class init ->2
        System.out.println(Son.B);

    }

}


class Grandpa {

    static {
        System.out.println("Grandpa class init");
    }

    public Grandpa() {
        System.out.println("Grandpa init");
    }

}

class Father extends Grandpa {

    public static int A = 1;

    static {
        A = 2;
        System.out.println("Father class init");

    }

    public Father() {
        System.out.println("Father init");

    }

}

class Son extends Father {

    public static int B = A;

    static {
        System.out.println("Son class init");
    }

    public Son() {
        System.out.println("Son init");

    }

}