package com.nikfce.leetcode.hard.nqueensii;

/**
 * 暴力回溯...
 * @author shenzhencheng on 2020/5/22
 */
public class Solution {

    // 结果
    private static int count = 0;

    public static int totalNQueens(int n) {
        if (n == 0 || n == 2 || n == 3) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] a = new int[n][n];
        execute(a, 0);
        return count;
    }

    /**
     * 递归回溯函数,表示本要寻找a数组第i行皇后应该放在哪
     * @param a 数组
     * @param i 当前所在行
     */
    private static void execute(int[][] a, int i) {
        if (i >= a.length) {
            return ;
        }
        for (int l = 0 ; l < a.length ; l ++) {
            if (adjust(a, i, l)) {
                if (i == a.length - 1) {
                    count ++;
                    return ;
                }
                a[i][l] = 1;
                execute(a, i + 1);
                a[i][l] = 0;
            }
        }

    }

    /**
     * 放在(i,j)位置是否合理
     */
    private static boolean adjust(int[][] a, int i, int j) {
        int n = a.length;
        // 检查横竖
        for (int m = 0 ; m < n ; m ++) {
            if (i != m) {
                if (a[m][j] == 1) {
                    return false;
                }
            }
            if (j != m) {
                if (a[i][m] == 1) {
                    return false;
                }
            }
        }
        // 左上
        int k = i - 1, l = j - 1;
        while (k >= 0 && l >= 0) {
            if (a[k--][l--] == 1) {
                return false;
            }
        }
        // 右上
        k = i - 1;
        l = j + 1;
        while (k >= 0 && l < n) {
            if (a[k--][l++] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(5));
    }

}
