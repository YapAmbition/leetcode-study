package com.nikfce.leetcode.hard.nqueens;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenzhencheng on 2020/6/4
 */
public class Solution {

    /**
     * 回溯法
     * 起初在第一行的每列放置棋子
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] checkerBoard = new char[n][n];
        for (int i = 0 ; i < n ; i ++) {
            for (int j = 0 ; j < n ; j ++) {
                checkerBoard[i][j] = '.';
            }
        }
        for (int i = 0 ; i < n ; i ++) {
            execute(checkerBoard, 0, i, n, result);
        }
        return result;
    }

    /**
     * 将棋子放在(row, col)上
     *
     * 先检查如果棋子放在(row, col)上会不会发生冲突,如果不会,将(row,col)置为Q表示放置棋子
     * 然后在下一行的每一列都放置棋子试试,直到放置到最后一行,如果还不发生冲突,就将棋盘记录下来
     */
    private static void execute(char[][] checkerBoard, int row, int col, int n, List<List<String>> result) {
        if (row >= n || col >= n) {
            return ;
        }
        // 检查(row, col)是否符合条件正确
        if (!check(checkerBoard, row, col)) {
            return ;
        }
        checkerBoard[row][col] = 'Q';
        if (row == n - 1) {
            result.add(toList(checkerBoard));
        } else {
            for (int i = 0 ; i < n ; i ++) {
                execute(checkerBoard, row + 1, i, n, result);
            }
        }
        checkerBoard[row][col] = '.';
    }

    private static List<String> toList(char[][] checkerBoard) {
        List<String> r = new ArrayList<>();
        for (char[] row : checkerBoard) {
            StringBuilder sb = new StringBuilder();
            for (char c : row) {
                sb.append(c);
            }
            r.add(sb.toString());
        }
        return r;
    }

    private static boolean check(char[][] checkerBoard, int row, int col) {
        // 垂直检查
        for (int i = 0 ; i < row ; i ++) {
            if (checkerBoard[i][col] == 'Q') {
                return false;
            }
        }
        // 水平检查
        for (int i = 0 ; i < checkerBoard.length ; i ++) {
            if (i != col && checkerBoard[row][i] == 'Q') {
                return false;
            }
        }
        // 左上角
        for (int i = 1 ; row - i >= 0 && col - i >= 0 ; i ++) {
            if (checkerBoard[row - i][col - i] == 'Q') {
                return false;
            }
        }
        // 右上角
        for (int i = 1 ; row - i >= 0 && col + i < checkerBoard.length ; i ++) {
            if (checkerBoard[row - i][col + i] == 'Q') {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        /**
         * 输入4
         * 输出:
         * [
         *  [".Q..",
         *   "...Q",
         *   "Q...",
         *   "..Q."],
         *
         *  ["..Q.",
         *   "Q...",
         *   "...Q",
         *   ".Q.."]
         * ]
         */
        System.out.println(JSON.toJSONString(solveNQueens(4)));
    }
}
