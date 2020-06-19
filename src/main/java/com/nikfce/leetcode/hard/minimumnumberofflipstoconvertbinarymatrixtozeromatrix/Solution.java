package com.nikfce.leetcode.hard.minimumnumberofflipstoconvertbinarymatrixtozeromatrix;

/**
 * 本来开始对这道题无从下手的,后来仔细一想,每一次反转就是一次异或,因此同一个点不可能反转两次,于是想到了穷举暴力求解
 * @author shenzhencheng on 2020/6/19
 */
public class Solution {

    private static int min = Integer.MAX_VALUE;

    public static int minFlips(int[][] mat) {
        execute(mat, 0, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 回溯,先不翻转下一个点,递归调用;然后再翻转,继续递归调用.之后再对这个点翻转一次,将矩阵恢复原样
     * @param mat 当前矩阵的状态
     * @param x 下一步要操作的行
     * @param y 下一步要操作的列
     * @param count 当前走了的步数
     */
    private static void execute(int[][] mat, int x, int y, int count) {
        if (finish(mat)) {
            min = Math.min(min, count);
            return;
        }

        if (x >= mat[0].length || y >= mat.length) {
            return;
        }

        int nextX = x + 1 ;
        int nextY = y ;
        if (nextX >= mat[0].length) {
            nextX = 0 ;
            nextY ++ ;
        }
        execute(mat, nextX, nextY, count);

        flip(mat, x, y);
        execute(mat, nextX, nextY, count + 1);
        flip(mat, x, y);
    }

    /**
     * 反转
     */
    private static void flip(int[][] mat, int x, int y) {
        for (int i = 0 ; i < mat.length ; i ++) {
            for (int j = 0 ; j < mat[0].length ; j ++) {
                if ((x == j && y == i) || (x == j + 1 && y == i) || (x == j - 1 && y == i) || (x == j && y == i - 1) || (x == j && y == i + 1)) {
                    mat[i][j] ^= 1 ;
                }
            }
        }
    }

    /**
     * 是否结束,只要还有1在就表示没结束
     */
    private static boolean finish(int[][] a) {
        for (int i = 0 ; i < a.length ; i ++) {
            for (int j = 0 ; j < a[0].length ; j ++) {
                if (a[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /**
         *  3
         *  [1,1]
         *  [1,0]
         *
         *  6
         *  [1,1,1]     [1,0,1]
         *  [1,0,1]     [0,1,0]
         *  [0,0,0]     [0,1,0]
         *
         *  -1
         *  [0,0,1]     [1,0,0]
         *  [0,0,1]     [1,0,0]
         */

        int[][] a = {{1,1,1}, {1,0,1}, {0,0,0}};
        System.out.println(minFlips(a));
    }
}
