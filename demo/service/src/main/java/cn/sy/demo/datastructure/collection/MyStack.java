package cn.sy.demo.datastructure.collection;

public interface MyStack<E> {
    void push(E e);

    E pop();

    E peek();

    boolean empty();

    int size();
}
