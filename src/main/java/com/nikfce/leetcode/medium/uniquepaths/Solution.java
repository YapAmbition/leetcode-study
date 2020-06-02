package com.nikfce.leetcode.medium.uniquepaths;

/**
 * @author shenzhencheng on 2020/6/1
 */
public class Solution {

    /**
     * 动态规划,位置从(0,0)到(i,j)的路有(i-1,j)+(i,j-1)条
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0 ; i < m ; i ++) {
            dp[0][i] = 1;
        }
        for (int i = 0 ; i < n ; i ++) {
            dp[i][0] = 1;
        }
        for (int i = 1 ; i < n ; i ++) {
            for (int j = 1 ; j < m ; j ++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }



    public static void main(String[] args) {
        System.out.println(uniquePaths(10,10));
    }
}
