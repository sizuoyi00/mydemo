package cn.sy.demo.datastructure.collection;

import java.util.Arrays;

public class MyArrayStack<E> implements MyStack<E> {

    private static final int DEAULT_CAPACITY = 10;

    private E[] data;

    private int size;

    /**
     * 栈：先进后出，所以记录栈顶元素
     */
    private int top;

    public MyArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
        top = -1;
    }

    public MyArrayStack() {
        this(DEAULT_CAPACITY);
    }

    @Override
    public void push(E e) {
        final int minCapacity = size + 1;
        if (minCapacity == data.length) {
            //扩容
            grow(minCapacity);
        }

        data[++top] = e;
        size++;
    }

    private void grow(int minCapacity) {
        //扩容1.5倍
        int newCapacity = data.length + (data.length >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        data = Arrays.copyOf(data, newCapacity);
    }

    @Override
    public E pop() {
        E e = data[top];
        data[top--] = null;
        size--;
        return e;
    }

    @Override
    public E peek() {
        return data[top];
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
        MyStack<Integer> stack = new MyArrayStack();
        for (int i = 0; i < 20; i++) {
            stack.push(i+1);
        }
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            Integer pop = stack.pop();
            System.out.println(pop);
        }
    }
}