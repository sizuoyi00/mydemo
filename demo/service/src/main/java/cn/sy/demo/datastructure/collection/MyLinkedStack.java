package cn.sy.demo.datastructure.collection;

public class MyLinkedStack<E> implements MyStack<E> {

    private int size;

    /**
     * 栈：先进后出，所以记录栈顶元素
     */
    private Node<E> top;

    @Override
    public void push(E e) {
        Node<E> oldTop = this.top;
        this.top = new Node(e, oldTop);
        size++;
    }

    @Override
    public E pop() {
        E item = top.item;
        top = top.next;
        size--;
        return item;
    }

    @Override
    public E peek() {
        return top.item;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyLinkedStack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(i + 1);
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