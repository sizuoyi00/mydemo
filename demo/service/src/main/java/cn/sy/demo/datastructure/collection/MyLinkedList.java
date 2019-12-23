package cn.sy.demo.datastructure.collection;

public class MyLinkedList<E> implements MyList<E> {

    Node<E> first;

    Node<E> last;

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) == -1;
    }

    /**
     * 创建以最后节点为前驱的 新节点
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        //“原尾节点”
        final Node<E> oldLast = last;

        //创建以尾节点为前驱的 “新尾节点”
        Node<E> newLast = new Node(last, null, e);

        //将“新尾节点”赋值给 “尾节点”
        last = newLast;

        //空链表
        if (oldLast == null) {
            //既是头节点也是尾节点
            first = newLast;
        } else {
            //将“原尾节点”的后置改为 “新尾节点”
            oldLast.next = newLast;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.item == null) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (o.equals(node.item)) {
                    unlink(node);
                    return true;
                }
            }
        }

        return false;
    }

    private void unlink(Node<E> node) {
        //上一节点
        Node<E> prev = node.prev;
        //下一节点
        Node<E> next = node.next;

        //头节点
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            //方便垃圾回收
            node.prev = null;

        }

        //尾节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            //方便垃圾回收
            node.next = null;
        }

        //方便垃圾回收
        node.item = null;
        size--;
    }

    @Override
    public E get(int index) {
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldItem = node.item;
        node.item = element;
        return oldItem;
    }

    @Override
    public void add(int index, E e) {

        //获取节点
        Node<E> oldNode = node(index);
        //尾部插入节点
        if (index == size) {
            add(e);
            return;
        }

        final Node<E> prev = oldNode.prev;
        Node newNode = new Node(prev, oldNode, e);

        //这里不能写成prev = newNode;
        oldNode.prev = newNode;
        //头部插入节点
        if (prev == null) {
            first = newNode;
        } else {
            //中间插入节点
            prev.next = newNode;
        }

        size++;
    }

    private Node<E> node(int index) {
        //LinkedList思想
        //半数以下 从前往后
        if (index <= (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            //半数以上 从前往后
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    @Override
    public E remove(int index) {
        final Node<E> delNode = node(index);

        final E item = delNode.item;
        unlink(delNode);

        return item;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            Node<E> x = first;
            while (x != null) {
                if (o.equals(x.item)) {
                    return index;
                }
                x = x.next;
                index++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        MyList<String> list = new MyLinkedList<>();
        System.out.println(list.isEmpty());
        list.add("0");
        list.add("1");
        System.out.println(list);

        list.add(0, "new0");
        System.out.println(list);

        list.add(list.size(), "4");
        System.out.println(list.size());

        System.out.println(list.get(2));

        System.out.println(list.indexOf("new0"));

        list.set(0, "nn0");

        list.remove(0);
        System.out.println(list);

        list.remove(list.size() - 1);
        System.out.println(list);
    }

    private static class Node<E> {
        //上一节点
        Node<E> prev;
        //下一节点
        Node<E> next;
        //当前节点值
        E item;

        public Node(Node<E> prev, Node<E> next, E item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }
}