package com.nikfce.leetcode.hard.uniquepathsiii;

/**
 * @author shenzhencheng on 2020/5/29
 */
public class Solution {

    private static int step = 0;
    private static int count = 0;

    /**
     * 找到起点和终点,并算清楚总共需要几步
     * 递归回溯,每次递归将当前步置为1,表示墙,然后往上,下,左,右走,如果是墙则直接返回,如果是终点,检查步数是否够了,不够的话也返回,如果是空地则步数-1,并将它置为1
     */
    public static int uniquePathsIII(int[][] grid) {
        int x = 0, y = 0;
        step = 0;
        count = 0;
        for (int i = 0 ; i < grid.length ; i ++) {
            for (int j = 0 ; j < grid[0].length ; j ++) {
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                } else if (grid[i][j] == 0) {
                    step ++ ;
                }
            }
        }
        go(grid, x, y);
        return count;
    }

    private static void go(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return ;
        }
        if (grid[x][y] == -1) {
            return ;
        }
        if (grid[x][y] == 2) {
            if (step == 0) {
                count ++ ;
            }
        } else if (grid[x][y] == 1) {
            grid[x][y] = -1;
            go(grid, x - 1, y);
            go(grid, x + 1, y);
            go(grid, x, y - 1);
            go(grid, x, y + 1);
            grid[x][y] = 1;
        } else if (grid[x][y] == 0) {
            step -- ;
            grid[x][y] = -1;
            go(grid, x - 1, y);
            go(grid, x + 1, y);
            go(grid, x, y - 1);
            go(grid, x, y + 1);
            grid[x][y] = 0;
            step ++;
        }
    }


    public static void main(String[] args) {
        int[][] a = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,2,-1}
        };
        System.out.println(uniquePathsIII(a)); // 2

        int[][] b = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,2}
        };
        System.out.println(uniquePathsIII(b)); // 4

        int[][] c = {
                {1,0},
                {0,0},
                {0,2}
        };
        System.out.println(uniquePathsIII(c)); // 0
    }
}
