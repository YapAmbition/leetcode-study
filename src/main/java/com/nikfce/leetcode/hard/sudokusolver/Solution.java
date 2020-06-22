package com.nikfce.leetcode.hard.sudokusolver;

import java.util.*;

/**
 * @author shenzhencheng on 2020/6/20
 */
public class Solution {

    /**
     * 思路: 对于每一个格子,找到能放在这个格子的所有数字,试着把数字放在里面,然后继续下一个格子
     * 注意点: 如果execute执行成功,直接返回,不做之后的操作(避免已经完成的board被修改)
     */
    public static void solveSudoku(char[][] board) {
        execute(board, 0, 0) ;
    }

    private static boolean execute(char[][] board, int x, int y) {
        if (x > 8 || y > 8 ) {
            return true;
        }
        int nx = x == 8 ? 0 : x + 1 ;
        int ny = x == 8 ? y + 1 : y ;
        if (board[y][x] != '.') {
            return execute(board, nx, ny);
        } else {
            Set<Character> available = available(board, x, y) ;
            for (char i : available) {
                board[y][x] = i ;
                if (execute(board, nx, ny)) return true ;
                board[y][x] = '.' ;
            }
        }
        return false ;
    }

    /**
     * 找出这一个格子能放的所有数字
     */
    private static Set<Character> available(char[][] board, int x, int y) {
        if (board[y][x] != '.') {
            return new HashSet<>() ;
        }
        Set<Character> set = new HashSet<Character>() {
            {
                add('1');
                add('2');
                add('3');
                add('4');
                add('5');
                add('6');
                add('7');
                add('8');
                add('9');
            }
        } ;
        // 横排,竖排
        for (int i = 0 ; i < 9 ; i ++) {
            if (board[y][i] != '.') set.remove(board[y][i]) ;
            if (board[i][x] != '.') set.remove(board[i][x]);
        }
        // 同一个正方形
        int h = x / 3 ;
        int v = y / 3 ;
        for (int i = 0 ; i < 3 ; i ++) {
            for (int j = 0 ; j < 3 ; j ++) {
                if (board[v * 3 + i][h * 3 + j] != '.') {
                    set.remove(board[v * 3 + i][h * 3 + j]) ;
                }
            }
        }
        return set ;
    }


    public static void main(String[] args) {
        char[][] a = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(a) ;
    }

}
