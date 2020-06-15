package com.nikfce.leetcode.hard.longestconsecutivesequence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author shenzhencheng on 2020/6/15
 */
public class Solution {

    /**
     * 用一个map存下当前数字的最长连续序列
     * 数组中当前的数num最长连续序列 = (left = num-1的最长连续序列) + (right = num+1的最长连续序列) + 1
     * 相互地, num-left的最长连续序列=num+right的最长连续序列=num的最长连续序列
     *
     * 例如,[4, 1, 3, 2], 首先,假设数x的最长连续序列为f(x)
     *  所以 f(4)= f(3) + f(5) + 1 = 1, 相互地, f(4-f(3)) = f(4) = 1, f(4+f(5)) = f(4) = 1
     *  继续循环: f(1) = f(0) + f(2) + 1 = 1, 相互地, f(1-f(0)) = f(1) = 1, f(1+f(2)) = f(1) = 1
     *  继续循环: f(3) = f(2) + f(4) + 1 = 2, 相互地, f(3-f(2)) = f(3) = 2, f(3+f(4)) = f(4) = 2
     *  继续循环: f(2) = f(1) + f(3) + 1 = 4, 相互地, f(2-f(1)) = f(1) = 4, f(2+f(3)) = f(4) = 4
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = Optional.ofNullable(map.get(num - 1)).orElse(0);
            int right = Optional.ofNullable(map.get(num + 1)).orElse(0);
            int cur = left + right + 1;
            max = Math.max(max, cur);
            map.put(num, cur);
            map.put(num - left, cur);
            map.put(num + right, cur);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 3, 2};
        System.out.println(longestConsecutive(a));
    }

}
