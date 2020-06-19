package com.nikfce.leetcode.hard.minimumnumberofflipstoconvertbinarymatrixtozeromatrix;

/**
 * 既然矩阵的每个点只能反转一次,那么对于任意一个点要么翻转,要么不翻转,因此可以用二进制来标记该点是否需要翻转
 * @author shenzhencheng on 2020/6/19
 */
public class Solution2 {

    public static int minFlips(int[][] mat) {
        int m = mat.length ;
        int n = mat[0].length ;
        int count = 1 << (m * n) ;
        int min = Integer.MAX_VALUE ;
        for (int i = 0 ; i < count ; i ++) {
            min = Math.min(min, execute(mat, i)) ;
        }
        return min == Integer.MAX_VALUE ? -1 : min ;
    }

    /**
     * 根据mask的二进制决定翻转的点对mat进行翻转,需要注意的是,每次翻转都会修改mat,需要将它改回来
     */
    private static int execute(int[][] mat, int mask) {
        int count = 0 ;
        for (int i = 0 ; i < mat.length ; i ++) {
            for (int j = 0 ; j < mat[0].length ; j ++) {
                int bit = i * mat[0].length + j ;
                if (((1 << bit) & mask) == 1 << bit) {
                    flip(mat, j, i);
                    count ++ ;
                }
            }
        }

        if (!finish(mat)) {
            count = Integer.MAX_VALUE ;
        }

        // 把mat改回来
        for (int i = 0 ; i < mat.length ; i ++) {
            for (int j = 0 ; j < mat[0].length ; j ++) {
                int bit = i * mat[0].length + j ;
                if (((1 << bit) & mask) == 1 << bit) {
                    flip(mat, j, i);
                }
            }
        }

        return count ;
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

    public static void main(String[] args) {
        int[][] a = {{0}, {1}, {1}};
        System.out.println(minFlips(a));
    }

}
