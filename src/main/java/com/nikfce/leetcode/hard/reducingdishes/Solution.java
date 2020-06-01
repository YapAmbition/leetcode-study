package com.nikfce.leetcode.hard.reducingdishes;

import java.util.Arrays;

/**
 * 先正序排序,找到第一个非0的位置作为起点.
 * 从非0的位置开始,起点每向左移动一位,正数和会增加一倍,同时负数和也会增加一倍+新增的负数值.
 * 因此只要保证每次起点向左移动时,正数和增加的量大于负数和+新增负数值的量大于0即可
 * @author shenzhencheng on 2020/5/22
 */
public class Solution {

    public static int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int max = satisfaction[satisfaction.length - 1];
        if (max <= 0) {
            return 0;
        }

        int left = 0; // 起点
        while (satisfaction[left] < 0) left ++;


        int zSum = 0;// 计算正数的和
        for (int i = left ; i < satisfaction.length ; i ++) {
            zSum += satisfaction[i];
        }

        // 比较每次起点向左移动以后,总值是增加还是减少,如果增加则起点左移
        // 每次起点左移,正数会增加一倍,负数会增加(一倍+新的负数值)
        int fSum = 0;
        for (int i = left - 1 ; i >= 0 ; i --) {
            fSum += satisfaction[i];
            if (zSum + fSum > 0) {
                left = i;
            }
        }

        // 计算结果
        int sum = 0;
        for (int i = 1 ; left < satisfaction.length ; left ++, i ++) {
            sum += satisfaction[left] * i;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(maxSatisfaction(new int[]{76,83,55,-36,-8,40,-60,-72,27,-32,37,1,77,24,-46,-26,20,-89,-35,-99,58,-7}));
    }

}
