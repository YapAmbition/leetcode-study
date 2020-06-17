package com.nikfce.leetcode.hard.maximumscorewordsformedbyletters;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 回溯求解
 * @author shenzhencheng on 2020/6/17
 */
public class Solution {

    private static int max = 0;

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : letters) {
            map.merge(c, 1, Integer::sum);
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < words.length; i++) {
            execute(words, i, stack, map, score);
        }
        return max;
    }

    /**
     * 回溯,以canCombine为条件判断剩余的字母集是否能组成当前单词,如果能组成则将该单词入栈,字母集减掉单词的字母进行下一次调用,调用完成之后将该单词出栈之后再进行一次调用
     */
    private static void execute(String[] words, int index, Stack<String> stack, Map<Character, Integer> letterMap, int[] score) {
        if (index >= words.length) {
            print(stack, score);
            max = Math.max(max, calculate(stack, score));
            return;
        }
        if (canCombine(words[index], letterMap)) {
            combine(words[index], stack, letterMap);
            execute(words, index + 1, stack, letterMap, score);
            rollback(stack, letterMap);
        }
        execute(words, index + 1, stack, letterMap, score);
    }

    /**
     * 计算当前栈的分数
     */
    private static int calculate(Stack<String> stack, int[] score) {
        int r = 0;
        for (int i = 0; i < stack.size(); i++) {
            String word = stack.get(i);
            for (int j = 0; j < word.length(); j++) {
                r += score[word.charAt(j) - 'a'];
            }
        }
        return r;
    }

    /**
     * 将当前栈回退,并将回退的单词字母返回到letterMap
     */
    private static void rollback(Stack<String> stack, Map<Character, Integer> letterMap) {
        String word = stack.pop();
        for (int i = 0; i < word.length(); i++) {
            if (letterMap.containsKey(word.charAt(i))) {
                letterMap.put(word.charAt(i), letterMap.get(word.charAt(i)) + 1);
            }
        }
    }

    private static void print(Stack<String> stack, int[] score) {
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i));
            System.out.print(',');
        }
        System.out.print(calculate(stack, score));
        System.out.println();
    }

    /**
     * 将单词入栈,并从letterMap中减掉所用的字母
     */
    private static void combine(String word, Stack<String> stack, Map<Character, Integer> letterMap) {
        stack.push(word);
        Map<Character, Integer> wordMap = string2Map(word);
        for (Character c : wordMap.keySet()) {
            letterMap.put(c, letterMap.get(c) - wordMap.get(c));
        }
    }

    private static Map<Character, Integer> string2Map(String word) {
        Map<Character, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            wordMap.merge(word.charAt(i), 1, Integer::sum);
        }
        return wordMap;
    }

    /**
     * 判断是否当前的字母库能组成当前单词
     */
    private static boolean canCombine(String word, Map<Character, Integer> letterMap) {
        Map<Character, Integer> wordMap = string2Map(word);
        boolean canCombine = true;
        for (Character c : wordMap.keySet()) {
            if (!letterMap.containsKey(c) || letterMap.get(c) < wordMap.get(c)) {
                canCombine = false;
                break;
            }
        }
        return canCombine;
    }

    public static void main(String[] args) {
        String[] words = {"ac", "abd", "db", "ba", "dddd", "bca"};
        char[] letters = {'a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'd', 'd', 'd', 'd'};
        int[] score = {6, 4, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(maxScoreWords(words, letters, score));
    }

}
