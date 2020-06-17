package com.nikfce.leetcode.hard.maximumscorewordsformedbyletters;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 位压缩
 * @author shenzhencheng on 2020/6/17
 */
public class Solution2 {

    /**
     * 位标记,words里的每个单词要么就用,要么就不用,因此每个单词的状态用0/1表示,穷举中所有单词的组合,计算每种组合的分数值
     */
    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        int max = 0 ;
        Map<Character, Integer> letterMap = new HashMap<>();
        for (char letter : letters) {
            letterMap.merge(letter, 1, Integer::sum);
        }
        int size = 1 << words.length ;
        for (int i = 1 ; i < size ; i ++) {
            max = Math.max(max, calculate(words, i, letterMap, score));
        }
        return max ;
    }

    /**
     * 计算分数值,先把本次组合的所有单词拿出来,然后根据所需字母的数量判断已有字母数量够不够组成本次组合的所有单词,如果不够则返回0,如果够则返回值
     */
    private static int calculate(String[] words, int mask, Map<Character, Integer> letterMap, int[] score) {
        List<String> wordList = new ArrayList<>() ;
        for (int i = 0 ; i < words.length ; i ++) {
            if (((1 << i) & mask) == 1 << i) {
                wordList.add(words[i]);
            }
        }

        Map<Character, Integer> wordMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0 ; i < word.length() ; i ++) {
                wordMap.merge(word.charAt(i), 1, Integer::sum);
            }
        }

        int r = 0 ;
        for (Character c : wordMap.keySet()) {
            if (!letterMap.containsKey(c) || letterMap.get(c) < wordMap.get(c)) {
                return 0 ;
            }
            r += wordMap.get(c) * score[c - 'a'];
        }
        return r ;
    }


    public static void main(String[] args) {
        String[] words = {"ac", "abd", "db", "ba", "dddd", "bca"};
        char[] letters = {'a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'd', 'd', 'd', 'd'};
        int[] score = {6, 4, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(maxScoreWords(words, letters, score));
    }

}
