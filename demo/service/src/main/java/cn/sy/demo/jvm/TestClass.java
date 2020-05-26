package cn.sy.demo.jvm;

/**
 * <p>
 * 主动引用 OR 被动引用
 * <p>
 *
 * @author sizuoyi
 * @version : TestClass.java, v 0.1 2020年05月26日 10:48 sizuoyi Exp $
 */

public class TestClass {

    public static void main(String[] args) {

        //使用static{}块输出“初始化信息”
//        test1();
//        test2();
//        test3();
        test4();

    }


    /**
     * 主动引用：有且只有5种情况，会立即对类进行“初始化”（加载，验证，准备自然在此之前）
     * 1.遇到new,getstatic,putstatic,invokstatic，如果类没有进行初始化，则先触发其初始化
     * 对于java语句，new对象，读取或设置一个类的静态字段（被final修饰，在编译期把结果放入常量池的静态字段除外），调用一个类的静态方法
     * 2.java.lang.reflect包进行反射调用的时候，如果类没有进行初始化，则先触发其初始化
     * 3.初始化一个类的时候，如果发现其父类没有进行过初始化，先触发其父类的初始化。
     *  子接口初始化时，不要求其父接口全部初始化，只有真正使用到父接口时（如引用接口中定义的函数型常量）才会初始化。
     * 4.当虚拟机启动时，用户需要指定一个要执行的主类，虚拟机会先初始化这个主类
     * 5.JDK1.7后动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_STATIC,REF_PUTSTATIC,REF_INVOKESTATIC方法句柄，且这个方法句柄对应的类没有进行初始化，则需要先触发其初始化
     * <p>
     * 除主动引用外，所有引用类的方式都不会触发初始化，称为被动引用

     */

    /**
     * 问题：接口中定义的都是常量，编译优化也会将该值存储到本身常量池中，那么为什么深入理解jvm说调用接口中常量会触发初始化？
     * 答：不会触发初始化，当接口中常量编译期间可知时，不会触发接口加载/初始化。
     * 当接口中常量为函数型常量，编译不可知，此时调用该常量时，会触发接口的加载/初始化，如SubInstance.CURRENT_TIME_MILLIS
     * <p>
     * 子父接口：仅调用子接口或父接口的常量（编译可知），不会触发子接口及父接口加载/初始化，
     * 当调用子接口或父接口的函数型常量时，会触发各自的初始化
     * 子接口初始化时，不要求其父接口全部初始化，只有真正使用到父接口时（如引用接口中定义的函数型常量）才会初始化。
     * <p>
     * 通过 java -verbose:class com.example.demo.jvm.demo.TestClass 查看类加载情况
     */

    private static void test4() {

        String a = SubInstance.SON;
        System.out.println(a);
        String b = SubInstance.FATHER;
        System.out.println(b);

        //编译优化，上边都不会触发接口的加载和初始化

        //接口函数型变量，会触发接口加载/初始化

        //son father 父接口加载 子接口加载 SubInstance init（子接口初始化）输出日志

        System.out.println(SubInstance.SON_THREAD);
        //son father 父接口加载 父接口加载 SupperInstance init（父接口初始化）输出日志
        System.out.println(SubInstance.FATHER_THREAD);

    }

    /**
     * finalstaticval
     * 被动引用 3.常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
     * 编译阶段通过常量池传播优化，将该值"finalstaticval"存储到了TestClass类的常量池中，
     * 以后TestClass对常量ConstClass.VALUE的引用实际都被转换为TestClass类对自身常量池的引用了，
     * 实际上TestClass的class文件之中并没有ConstClass类的符号引用入口，这两个类再编译成class之后就没有任何联系了
     * <p>
     * 以上结论不包含函数型常量
     */

    private static void test3() {

        //编译后.class文件该语句实际为：String copyValue = "finalstaticval";

        String copyValue = ConstClass.VALUE;

        System.out.println(copyValue);

    }

    /**
     * 无输出
     * 被动引用 2.通过数组定义来引用类，不会导致该类初始化
     * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过子类引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
     * <p>
     * 问题：为什么数组不会导致该类初始化，数组有什么特殊性？
     * 答：非数组通过类加载器创建，而数组是由java虚拟机直接创建的
     */

    private static void test2() {

        SuperClass[] sca = new SuperClass[10];

    }

    /**
     * SuperClass init
     * 123
     * 被动引用 1.通过子类引用父类的静态字段，不会导致子类初始化
     * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过子类引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
     */

    private static void test1() {

        System.out.println(SubClass.value);

    }

}

interface SupperInstance {

    String FATHER = "father";

    Thread FATHER_THREAD = new Thread() {{

        System.out.println("SupperInstance init");

    }};

}

interface SubInstance extends SupperInstance {

    String SON = "son";

    //    long CURRENT_TIME_MILLIS = System.currentTimeMillis();

    Thread SON_THREAD = new Thread() {

        {

            System.out.println("SubInstance init");

        }

    };

}

class ConstClass {

    static {

        System.out.println("ConstClass init");

    }

    public static final String VALUE = "finalstaticval";

}

class SuperClass {

    static {

        System.out.println("SuperClass init");

    }

    public static int value = 123;

}

class SubClass extends SuperClass {

    static {

        System.out.println("SubClass init");

    }

}