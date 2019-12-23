package cn.sy.demo.datastructure.collection;

public interface MyList<E> {

    int size();

    boolean isEmpty();

    boolean contains(E o);

    boolean add(E o);

    boolean remove(E o);

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E o);
}
