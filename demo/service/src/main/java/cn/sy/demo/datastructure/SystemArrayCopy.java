package cn.sy.demo.datastructure;

import java.util.Arrays;

/**
 * @author sizuoyi
 * @version : demo, v 0.1 2019-09-06 14:00 sizuoyi Exp $
 */
public class SystemArrayCopy {

    public static void main(String[] args) {

        String[] list = new String[]{"1", "2", "3", "4"};
        System.out.println(Arrays.toString(list));

        //扩容，否则后边数组后移复制会数组越界
        list = Arrays.copyOf(list, list.length + 1);

        //指定位置的元素
        int index = 1;
        String newIndexVal = "new";

        //src:源数组; srcPos:源数组要复制的起始位置; dest:目的数组; destPos:目的数组放置的起始位置; length:复制的长度.
        //从下标为index的数组开始，整体数组后移一位
        System.arraycopy(list, index, list, index + 1, list.length - (index + 1));

        list[index] = newIndexVal;

        System.out.println(Arrays.toString(list));

        System.arraycopy(list, index + 1, list, index, list.length - (index + 1));
        list[list.length - 1] = null;

        System.out.println(Arrays.toString(list));
        int[] src = new int[]{};
        int[] dest = new int[]{};
        int length = 1;



        /**
         * src:源数组;
         * srcPos:源数组要复制的起始位置;
         * dest:目标数组;
         * destPos:目标数组放置的起始位置;
         * length:复制的长度
         */
        System.arraycopy(src, 0, dest, 0, length);
    }

}