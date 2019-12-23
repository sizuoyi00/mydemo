package cn.sy.demo.datastructure.tree;

import org.apache.poi.ss.formula.functions.T;

/**
 * 二叉搜索树
 */
public class BSTree<E> {

    private BSTree<E> mRoot;

    public BSTree<E> search(E key) {
        return search(mRoot, key);
    }

    public BSTree<E> searchIter(E key) {
        return searchIter(mRoot, key);
    }

    public E maximum() {
        BSTNode<E> p = maximum(mRoot);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    public E minimum() {
        BSTNode<E> p = minimum(mRoot);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    /**
     * 节点的前驱：是该节点的左子树中的最大节点。
     * 节点的后继：是该节点的右子树中的最小节点。
     */

    /**
     * 插入
     */

    /**
     * 删除
     */

    /**
     * 查找最小结点：返回tree为根结点的二叉树的最小结点。
     */
    private BSTNode<E> minimum(BSTree<E> mRoot) {
        return null;
    }

    /**
     * 查找最大结点：返回tree为根结点的二叉树的最大结点。
     */
    private BSTNode<E> maximum(BSTree<E> mRoot) {
        return null;
    }

    /**
     * 非递归-查找"二叉树x"中键值为key的节点
     */
    private BSTree<E> search(BSTree<E> mRoot, E key) {
        return null;
    }

    /**
     * 非递归-查找"二叉树x"中键值为key的节点
     */
    private BSTree<E> searchIter(BSTree<E> mRoot, E key) {
        return null;
    }

    private static class BSTNode<E> {
        E key;
        BSTNode<T> left;
        BSTNode<T> right;
        BSTNode<T> parent;

        public BSTNode(E key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
