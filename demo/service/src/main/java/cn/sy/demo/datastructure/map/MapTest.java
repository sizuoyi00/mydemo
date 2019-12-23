package cn.sy.demo.datastructure.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {

        int a = 8;
        int i1 = a >>> 2;

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1","1");
        concurrentHashMap.put("2","1");
        concurrentHashMap.put("3","1");
        concurrentHashMap.put("4","1");
        concurrentHashMap.put("5","1");
        concurrentHashMap.put("6","1");
        concurrentHashMap.put("7","1");
        concurrentHashMap.put("8","1");
        concurrentHashMap.put("9","1");
        concurrentHashMap.put("10","1");
        concurrentHashMap.put("11","1");
        concurrentHashMap.put("12","1");
        concurrentHashMap.put("13","1");
        concurrentHashMap.put("14","1");
        concurrentHashMap.put("15","1");
        concurrentHashMap.put("16","1");
        concurrentHashMap.put("17","1");
        concurrentHashMap.put("18","1");
        concurrentHashMap.put("19","1");
        concurrentHashMap.put("20","1");

        Iterator<Map.Entry<String, String>> iterator2 = concurrentHashMap.entrySet().iterator();
        while (iterator2.hasNext()){
            iterator2.next();
            iterator2.remove();
        }


        int n = 8;
        n |= n >>> 1;
        System.out.println(n);

        System.out.println(16 & 156);
        System.out.println(156 & 16);

        System.out.println("=========");

        Map<String, String> map = new HashMap<>(3);
        Map map1 = new HashMap<>(4);
        Map map2 = new HashMap<>(5);
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        map.put("10", "1");
        map.put("11", "1");
        map.put("12", "1");
        map.put("13", "33");
        map.put("14", "44");


        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            iterator.remove();

        }


        System.out.println("=========");
        for (String s : map.keySet()) {
            System.out.println(s + ": " + map.get(s));
        }

        map.remove("8");
        map.remove("3");
        map.remove("12");

        System.out.println(1 << 30);
        System.out.println(1 << 4);
        System.out.println(8 << 1);

        HashMap hashMap = new HashMap<>(12);
        hashMap.put("1", "1");


        TreeMap<String, String> treeMap = new TreeMap();
        treeMap.put("1", "1");
        treeMap.put("2", "2");
        treeMap.put("3", "3");
        treeMap.put("4", "4");
        treeMap.put("5", "5");
        treeMap.put("6", "6");


        Iterator<Map.Entry<String, String>> iterator1 = treeMap.entrySet().iterator();
        while (iterator1.hasNext()){
            iterator1.next();
            iterator1.remove();
        }

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            treeMap.remove(entry.getKey());
        }

        treeMap.remove("4");


        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (int i = 0; i < list.size(); i++) {
            list.remove(1);
            list.get(1);
            list.get(3);

        }

    }

    /*


    对象计算

  1、内存泄漏：排查代码问题，内存溢出：jdk参数调优  大促场景，单点登录内存溢出

  因为jdk默认这个新生代与老年代的比例是固定的嘛，一般情况我们也不会动这些参数，只有在我们压测出现高并发发现问题之后，我们会考虑动这些参数。
  然后首先第一个思路就是，像电商这种场景多数都是属于朝生夕死的这种对象，不存在像一些老系统那种就是，有很多这种一直存在的这种老对象，或者说特别大的这种对象。
  基于这种前提的话，分析处理的思路，就是考虑将新生代这个空间加大，然后老年代这边可以稍微的缩短一些。

  案例：
  具体场景的话，我这边的实际经验就是一次大促活动，在上线前进行秒杀场景的压测处理的内存泄漏场景。

工具：
  线下压测的话，借助jvisualvm这个工具，因为其可视化十分方便，主要这里的关注点有三个，
  一个是GC界面，对应GC情况，一个就是CPU界面，一个是内存界面，对应的jdk命令就是jmap与jstack命令。
  线上的话，借助GC日志对应GC情况，使用jmap与jstack查看对象内存与线程方法情况。
  ‐XX:+PrintGCDetails ‐XX:+PrintGCTimeStamps ‐XX:+PrintGCDateStamps ‐Xloggc:./gc.log

分析思路：
  当时情况就是在压测秒杀接口的时候，首先从接口的监控就是接口响应越来越慢，然后通过jvisuleVM
  发现minorGC特别频繁，然后老年代内存在持续增加，这种情况说明什么？
  说明存在大量的对象通过minorGC不能回收掉，从而全部跑到老年代了，导致占用了过多内存，导致程序越来越慢。
  在使用jvisualVM

  当时的情况就是在压测秒杀接口的时候，发现minorGC特别频繁，fullGC，像这种情况排查思路一般有两种，
  一种是代码出现问题，可能哪里出现了内存泄漏，另一种情况可能是jdk内存的一些参数可能分配的不合理。
  首先就是排查代码问题，因为状况是minorGC与fullGC，这种情况说明什么？
  说明很少有大对象或者活的比较久的对象产生，但是却产生了非常多的临时对象，将eden占满了，导致了minorGC的频繁发生，从而致使fullGC频繁。
  其次就是分析jvm参数分析，临时对象较多，可能需要调整新生代与老年代的比例等等。
  相反，如果是后管这种，经常有大对象产生的场景，也是类似的分析思路。

实际解决：
  代码定位：上边猜测临时对象过多，所以借助jvisualvm的内存快照，观察对象产生的数量，这个比较依赖对业务了解情况，
  比如我这边遇到的秒杀情况，除了基本数据类型外，会出现大量的业务上的对象，同时创建然后扔到eden中，
  那一般这种业务上的对象的个数理论上与


如果是在线上发现内存溢出，首先临时解决方案肯定就是临时扩容，增加内存。

  fullGC则比较正常，就根据上边的这种思路，很自然的想到要调整年轻代与老年代的比例，
  所以我们的第一处理方案是将年轻代的占比添加，添加后继续压测发现是有缓解的情况，但是还是特别频繁，这次就转变思路了，

  后管平台，查多条数据，导致fullGC频繁，

2、内存泄漏：排查代码问题

处理的另一个实际经验 主要是两个一次是在那个上线前的压测并发压测，然后解决掉的一个场景，另外一个是真正的线上出现的一个就是嗯内存 CPU 的忽高忽下的这这样的一种场景，那第 1 个这种场景就是怎么发现的呢？就是呃我们要做一个大促活动促销活动，然后呃对应的嗯，啊方案就是啊做。

呃，我们这边在使用这个线下压测的时候，噢我这边是会使用那个 jvisualvm ，然后因为毕竟可视化啊，我这边是方便的，能够感知一些事情，然我这边关注点，一是这个它的一个 CPU ， 2 是它的一个内存，然后也就是相当于 j map 和 jstack ，然后最终就是通过这个CPU 查看这个占用占用比较多的这个，然后另外一个就是通过这个内存的大小，然后确认，嗯，占用内存比较多的这么一个对象。

呃，像我上次这种就是压测发现问题的这个具体是什么情况，我简单介绍一下，就是首先就通过这个，呃， cpI ，呃，来确认到我们这个到底具体是哪个县，呃，就是县城的哪个方法？就是耗时比较长，出现的次数比较多，这样的情况，然后哦就具体到了方法嘛，然后我们就看了一下内存，就具体的某个呃对象，然后最终就是得出一个结论，就是在特别高的并发的情况下，有一个对象在疯狂的创建，然后我们就看了一下这个源代码也确实是这样，就是呃代码大概的一个场景可以理解为就是在每次做这个单点登录的时候，因为我们模拟的是登录这个场，呃，压的是登录这个场景了，然后哦就是用户一窝蜂的挤进来，然后每次都在创建这个对象。

嗯，然后用户一窝蜂的进来，我们每一次都呃有一个工具就是校验这个用户，就是做做单点登录用的，具体就是一个工具，然后这个工具类里边有一个呃，就是对象吧，就是实际上是厄只需要创建一次就 OK 的，然后噢这边写的代码是每次都弄一个，然后，嗯就这样就是如果在特别高的地方下，然后这个呃他每次都另有一个，然后他就会嗯，嗯就是数量特别的多嘛，然后哦所以我们平时也看不出来，看不出来这个问题，然后，嗯最终导致的结果就是在，唉，比如说这一套流程走完了，然后我就噢 jc 就是那个命令 jc 一下，然后就导致疯狂的在这个伊甸园在疯狂。

厄，另外一个生产状况是我们这边就是厄压测没有测出来的一个就也没有进行压测，因为不是一个高频场景，这个大概是一个从那个数据库里边去，首先我们定位的方案是一样的，只不过这次因为我们线上是不可以使用这个这个 VRm 的，因为它是 10 分耗费性能的，所以我们就是把这个 VRm 可视化的这个 j stack 查内存，然后这个 map 查现成的这个情况就是只能通过我们命令行来查找，然后嗯，就这样的话也是同样定位到了代码嘛，然后嗯最终是得到了结论，它是一个哦，每次查数据库，就是在 uh.

每次查数据库的时候，由于这个数据库比较大，然后哦就是这一条数据比较大，然后嗯在查的时候又查的数量比较多，然后嗯就是导致了就是嗯发生了，嗯，每次预定员是放不下，直接扔到了老年代，然后做副 gc ，啊，负 bc 的次数比较多的这样一个情况。

然后另外有印象的一个情况就是类似于嗯做有一个 for 循环，然后噢有一个代码写的不规范，就每次都在那个放循环里边，那个对象每次都在放行环里边声明加密对象，然后导致这个内存有一些的紧张，这么一个情况也是就是像这种情况，因为厄实际上它只在 111 这一小块的代码，这一块这一个，呃，站里边嘛，然后实际上他也不会有什么问题，理论上，但是嗯细节决定成败嘛，这种情况在高并发的时候就还是会如果同时，嗯出发的话还是会出现类似的就是嗯，导致内存崩掉，就是导致内存溢出这些情况，反正就是 um.


     */


}
