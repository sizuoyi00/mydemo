package cn.sy.demo.datastructure.collection;

import lombok.ToString;

import java.util.*;

@ToString
public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private E[] data;

    public MyArrayList(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E o) {
        return indexOf(o) == -1;
    }

    @Override
    public boolean add(E o) {
        final int minCapacity = size + 1;
        if (minCapacity == data.length) {
            //扩容
            grow(minCapacity);
        }
        data[size++] = o;
        return true;
    }


    /**
     * 扩容，默认1.5  如果扩容后小于需要的最小容量，则使用传入的容量
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        //扩容1.5倍
        int newCapacity = data.length + data.length >> 1;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        data = Arrays.copyOf(data, newCapacity);
    }

    @Override
    public boolean remove(E o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                E e = data[i];
                if (e == null) {
                    fastRemove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                E e = data[i];
                if (o.equals(e)) {
                    fastRemove(i);
                }
            }
        }
        return false;
    }

    /**
     * 向前移动
     *
     * @param index
     */
    private void fastRemove(int index) {
//        for (int j = 0; j < size - 1; j++) {
//            if (j >= index) {
//                data[j] = data[j + 1];
//            }
//        }
        System.arraycopy(data, index + 1, data, index, size - 1 - index);

        //size要减少一个
        data[--size] = null;
    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        data[index] = element;
        return element;
    }

    /**
     * 向后移动
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        final int minCapacity = size + 1;
        if (minCapacity == data.length) {
            //扩容
            grow(minCapacity);
        }

        //移动
        System.arraycopy(data, index, data, index + 1, 4);

        //赋值
        data[index] = element;
        size++;
    }


    @Override
    public E remove(int index) {
        E e = data[index];
        fastRemove(index);
        return e;
    }

    @Override
    public int indexOf(E o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                E e = data[i];
                if (e == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                E e = data[i];
                if (o.equals(e)) {
                    return i;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        List arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");
        Iterator iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();

        }

        List linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("1");
        linkedList.add("1");
        linkedList.add("1");
        Iterator iterator1 = linkedList.iterator();
        while (iterator1.hasNext()) {
            iterator1.next();
            iterator1.remove();

        }


        MyList<Integer> l = new MyArrayList<>();
        l.isEmpty();
        l.add(1);
        l.add(3);
        l.add(4);
        l.add(7);
        l.add(12);
        System.out.println(l);

        l.add(2, 6);
        System.out.println(l);

        l.remove(3);
        System.out.println(l);

        System.out.println(l.indexOf(1));

        l.set(3, 33);
        System.out.println(l);

        System.out.println(l.get(1));

        l.remove(new Integer(1));
        System.out.println(l);

        System.out.println(l.isEmpty());

        System.out.println(l.size());
    }

}