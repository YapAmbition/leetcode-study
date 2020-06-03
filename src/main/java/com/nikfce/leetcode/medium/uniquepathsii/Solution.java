package com.nikfce.leetcode.medium.uniquepathsii;

/**
 * @author shenzhencheng on 2020/6/2
 */
public class Solution {

    /**
     * 和unique-paths一样,动态规划,dp[i][j]表示从起点到达(i,j)的路径数量,但是每次计算dp时要判断（i,j）是否是墙,如果是墙则dp[i][j]=0
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0 ; i < m ; i ++) {
            for (int j = 0 ; j < n ; j ++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = obstacleGrid[i][j] ^ 1;
                } else if (i == 0) {
                    dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i][j-1];
                } else if (j == 0) {
                    dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i-1][j];
                } else {
                    dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : (dp[i][j-1] + dp[i-1][j]);
                }
            }
        }
        return dp[m-1][n-1];
    }

}
