package com.nikfce.leetcode.hard.volumeofhistogramlcci;

/**
 * @author shenzhencheng on 2020/6/7
 */
public class Solution {

    /**
     * 将每个数字当作木板的长度,记录这些木板能够装下多少水
     * 思路:
     *  1. 从左边往右扫,如果当前木板是迄今为止最长(>=)的木板,则计算左边的储水量(此时无论右边的木板长度如何都无法改变左边的储水量),之后记录下最长木板的长度和位置
     *  2. 第一步执行完之后,如果最右边的木板不是最长木板,则可能会在最长木板右边还有储水量没有计算到.此时从右边开始到最长木板为止从右往左扫一次,计算出最长木板右边的储水量
     */
    public static int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int max = height[0] ;
        int l = 0 ;
        int sum = 0 ;
        // 从左往右扫
        for (int i = 1 ; i < height.length ; i ++) {
            if (height[i] >= max) {
                int tmpMax = Math.min(max, height[i]);
                for (int j = l + 1 ; j < i ; j ++) {
                    sum += tmpMax - height[j];
                }
                max = height[i];
                l = i ;
            }
        }
        // 从右往左扫
        sum += reverse(height, l);
        return sum;
    }

    private static int reverse(int[] a, int e) {
        if (e >= a.length - 2) {
            return 0;
        }
        int max = a[a.length - 1];
        int r = a.length - 1;
        int sum = 0;
        for (int i = a.length - 2 ; i >= e ; i --) {
            if (a[i] >= max) {
                int tmpMax = Math.min(max, a[i]);
                for (int j = r - 1 ; j > i ; j --) {
                    sum += tmpMax - a[j];
                }
                max = a[i];
                r = i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(a)); // 6
    }
}
