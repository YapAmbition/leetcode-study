package com.nikfce.leetcode.hard.binarytreepostordertraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * @author shenzhencheng on 2020/5/22
 */
public class Solution {

    /**
     *  每次将本节点的值放到结果结尾,然后将左,右节点入栈.然后从栈里循环拿节点.(这思路感觉纯属巧合,等于是逆序地来解决)
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode now = stack.pop();
            list.add(0, now.val);
            Optional.ofNullable(now.left).ifPresent(stack::push);
            Optional.ofNullable(now.right).ifPresent(stack::push);
        }
        return list;
    }

    /**
     * 感觉做出来后序是巧合,就想把中序和前序都实现一下,其实画个图就能清楚地看出来
     * 思路: 首先,之前用的是递归,现在用迭代,区别就在于没有本地变量帮忙记录中间状态了,所以要自己存储中间状态,而类似题目的最好存储办法就是栈了
     * 所以我需要两个栈s1, s2, 然后仔细想之前的递归其实是把根节点的执行时间定格在了执行完自节点之后,所以现在用栈来存(感觉说不清楚,得自己领悟)
     * 拿到root一定是先压入s1, 因为是中序(先输出左节点,再输出自己,再输出右节点),所以如果s1.peek().left != null(栈顶元素有左节点),则将左节点压栈s1.push(s1.peek().left)
     * 如果s1.peek().left == null, s2.push(s1.pop()), s1.push(s2.peek().right), 如果s1的栈顶元素的左节点为null, 则将该元素出栈并压入s2,且将它的右节点压入s1(这里应该可以看出是中序了)
     * 循环以上步骤,s2的值就是结果
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.empty()) {
            TreeNode top = s1.peek();
            // 中序有点麻烦,因为有时候它的自节点要先于它出栈,所以这里要判断s2是否已经有它的左节点了,否则会重复放入节点
            if (top.left != null && !s2.contains(top.left)) {
                s1.push(top.left);
            } else {
                s2.push(s1.pop());
                Optional.ofNullable(top.right).ifPresent(s1::push);
            }
        }
        while (!s2.empty()) {
            result.add(0, s2.pop().val);
        }
        return result;
    }

    /**
     * 同理,前序也很容易了
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.empty()) {
            TreeNode node = s1.pop();
            s2.push(node);
            Optional.ofNullable(node.right).ifPresent(s1::push);
            Optional.ofNullable(node.left).ifPresent(s1::push);
        }
        while (!s2.empty()) {
            result.add(0, s2.pop().val);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        root.left = r2;
        root.right = r3;
        System.out.println(preorderTraversal(root));
    }

}
