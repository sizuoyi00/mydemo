package cn.sy.demo.datastructure.tree;

import cn.sy.demo.utils.BTreeUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 二叉树：前序中序后续遍历
 * @author sizuoyi
 * @version : demo, v 0.1 2019-09-12 13:19 sizuoyi intxp $
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        String line = "[4,2,7,1,3,5,8,9,null,0,6]";
        TreeNode root = BTreeUtils.stringToBTree(line);
        BTreeUtils.printNode(root);

        System.out.println("\n===============preOrder===============");
        preOrder(root);
        System.out.println();
        preOrderIter(root);
        System.out.println();
        preOrderIter2(root);
        System.out.println("\n===============preOrder===============");

        System.out.println("\n===============inOrder===============");
        inOrder(root);
        System.out.println();
        inOrderIter(root);
        System.out.println("\n===============inOrder===============");

        System.out.println("\n===============postOrder===============");
        postOrder(root);
        System.out.println();
        postOrderIter(root);
        System.out.println("\n===============postOrder===============");

    }

    /**
     * 前序遍历-递归
     */
    public static void preOrder(TreeNode btree) {
        if (btree == null) {
            return;
        }

        //节点值
        System.out.print(btree.val + ",");
        //左子节点
        preOrder(btree.left);
        //右子节点
        preOrder(btree.right);

    }

    /**
     * 前序遍历-非递归
     */
    public static void preOrderIter(TreeNode btree) {

        //访问某结点，并将该结点入栈
        Deque<TreeNode> stack = new ArrayDeque();
        stack.addFirst(btree);

        Deque<Integer> result = new ArrayDeque<>();

        while (!stack.isEmpty()) {
            //前序遍历
            final TreeNode node = stack.pollFirst();
            result.addLast(node.val);

            if (node.right != null) {
                stack.addFirst(node.right);
            }

            if (node.left != null) {
                stack.addFirst(node.left);
            }

        }

        while (!result.isEmpty()) {
            System.out.print(result.pollFirst() + ",");
        }
    }

    /**
     * 前序遍历-非递归2
     */
    public static void preOrderIter2(TreeNode btree) {

        //用来暂存节点的栈
        Deque<TreeNode> stack = new ArrayDeque();
        //返回结果集
        Deque<Integer> result = new ArrayDeque<>();
        TreeNode curr = btree;

        //当遍历到最后一个节点的时候，它的左右子树为空 并且栈也为空
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                //前序顺序根左右，即先将根节点扔到返回结果集中
                result.addLast(curr.val);
                //将新的游标节点暂存，因为后续要访问其右子节点
                stack.addFirst(curr);
                curr = curr.left;
            } else {
                // 一直到左子树为空，则开始考虑右子树
                // 弹出栈顶元素，从栈顶元素开始找其右子树，将游标转移到新右子树
                curr = stack.pollFirst();
                curr = curr.right;
            }
        }

        while (!result.isEmpty()) {
            System.out.print(result.pollFirst() + ",");
        }
    }

    /**
     * 中序遍历-递归
     */
    public static void inOrder(TreeNode btree) {
        if (btree == null) {
            return;
        }
        //左子节点
        inOrder(btree.left);
        //节点值
        System.out.print(btree.val + ",");
        //右子节点
        inOrder(btree.right);
    }

    /**
     * 中序遍历-非递归
     */
    public static void inOrderIter(TreeNode btree) {
        if (btree == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> result = new ArrayDeque<>();
        TreeNode curr = btree;
        while (!stack.isEmpty() || curr != null) {
            //先遍历左子树，直到左子树为空
            if (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            } else {
                //遍历当前右子树
                curr = stack.pollFirst();
                result.addLast(curr.val);
                curr = curr.right;
            }
        }

        while (!result.isEmpty()) {
            System.out.print(result.pollFirst() + ",");
        }

    }

    /**
     * 后序遍历-递归
     */
    public static void postOrder(TreeNode btree) {
        if (btree == null) {
            return;
        }
        //左子节点
        postOrder(btree.left);
        //右子节点
        postOrder(btree.right);
        //节点值
        System.out.print(btree.val + ",");

    }

    /**
     * 后序遍历-非递归
     */
    public static void postOrderIter(TreeNode btree) {
        Deque<TreeNode> stack = new ArrayDeque();
        stack.addFirst(btree);

        Deque<Integer> result = new ArrayDeque<>();

        while (!stack.isEmpty()) {
            //取出栈顶的值
            final TreeNode node = stack.pollFirst();
            result.addFirst(node.val);

            if (node.left != null) {
                stack.addFirst(node.left);
            }

            if (node.right != null) {
                stack.addFirst(node.right);
            }
        }

        while (!result.isEmpty()) {
            System.out.print(result.pollFirst() + ",");
        }
    }

}