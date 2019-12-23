package cn.sy.demo.datastructure.collection;

/**
 * @author sizuoyi
 * @version : demo, v 0.1 2019-09-11 10:33 sizuoyi Exp $
 */
public class MyArrayDeque<E> implements MyDeque<E> {

    private static final int DEAULT_CAPACITY = 5;

    private E[] data;

    private int size;

    /**
     * 队列：先进先出，记录头尾
     */
    private int first;
    private int last;

    public MyArrayDeque(int capacity) {
        this.first = -1;
        this.last = -1;
        data = (E[]) new Object[capacity];
    }

    public MyArrayDeque() {
        this(DEAULT_CAPACITY);
    }

    @Override
    public void push(E e) {
        final int minCapacity = size + 1;
        if (minCapacity == data.length) {
            grow(minCapacity);
        }

        if (size == 0) {
            first = 0;
        }

        //如果元素弹出队列，由于从头开始弹出元素会导致数组靠前角标的元素弹出后，产生大量占用角标，数据却为空的情况
        //所以采用循环数组，当插入元素时，实际长度没有达到数组长度时，通过对数组长度取余，实现将尾部角标移动到前方的空角标
        //相当于first在前，last在后
        last = (last + 1) % data.length;
        data[last] = e;

        size++;
    }

    private void grow(int minCapacity) {
        int newCapacity = data.length + (data.length >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }

        E[] newData = (E[]) new Object[newCapacity];
        //从原数组的头角标开始，复制整个数组到一个新数组上
        //新数组头角标为0，尾角标为实际长度-1
        System.arraycopy(data, first, newData, 0, size);
        data = newData;
        first = 0;
        last = size - 1;
    }

    @Override
    public E pop() {
        final E e = data[this.first];
        data[this.first] = null;
        size--;
        //弹出元素，头角标+1 因采用循环数组，通过取余数确认头角标
        this.first = (first + 1) % data.length;
        return e;
    }

    @Override
    public E peek() {
        return data[this.first];
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyDeque<Integer> deque = new MyArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            deque.push(i + 1);
        }
        System.out.println(deque.peek());
        System.out.println(deque.pop());
        int size = deque.size();
        for (int i = 0; i < size; i++) {
            Integer pop = deque.pop();
            System.out.println(pop);
        }
    }
}