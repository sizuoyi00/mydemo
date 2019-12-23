package cn.sy.demo.datastructure.collection;

public class MyLinkedDeque<E> implements MyDeque<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;


    @Override
    public void push(E e) {

        //前队尾结点
        Node<E> prev = this.last;

        //新的队尾结点
        last = new Node<>(e, null);

        //无数据时更新头结点
        if (size == 0) {
            first = last;
        }else{
            //前队尾结点的下一个元素指向新的队尾结点
            prev.next = last;
        }

        size++;
    }

    /**
     * 先进先出pop first
     *
     * @return
     */
    @Override
    public E pop() {
        //新队头结点
        Node<E> newFirstNode = first.next;
        E item = first.item;

        //更新队头结点
        first = newFirstNode;
        size--;

        //无数据时更新尾结点
        if (size == 0) {
            last = null;
        }

        return item;
    }

    @Override
    public E peek() {
        return first.item;
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
        MyDeque<Integer> deque = new MyLinkedDeque<>();
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
