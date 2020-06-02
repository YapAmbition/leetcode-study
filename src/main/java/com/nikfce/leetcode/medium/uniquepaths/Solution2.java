package com.nikfce.leetcode.medium.uniquepaths;

/**
 * @author shenzhencheng on 2020/6/2
 */
public class Solution2 {

    /**
     * 排列组合,从起点走到终点一共要走m+n-2步,这些步数中有m-1步是'向右',有n-1步是'向下',
     * 因此不同的路径数量为C(m+n-2, m-1)C(n-1,n-1)或者C(m+n-2,n-1)C(m-1,m-1), 即C(m+n-2,m-1)或者C(m+n-2,n-1)
     */
    public static int uniquePaths(int m, int n) {
        return comb(m + n - 2, n - 1);
    }

    /**
     * 组合数,底小于10直接算,大于10的话对半算(- - leetcode上居然还通过了)
     */
    private static int comb(int m, int n) {
        if (n == 0 || m == n) {
            return 1;
        }
        if (n == 1) {
            return m;
        }
        if (n > m / 2) {
            return comb(m, m - n);
        }
        if (m <= 10) {
            int a = 1;
            for (int i = 0 ; i < n ; i ++) {
                a *= (m - i);
            }
            int b = 1;
            for (int i = n ; i > 1 ; i --) {
                b *= i;
            }
            return a / b;
        } else {
            int i = m / 2;
            int sum = 0;
            for (int j = 0 ; j <= n ; j ++) {
                sum += comb(i, j) * comb(m - i, n - j);
            }
            return sum;
        }

    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(10, 10));
    }

}
