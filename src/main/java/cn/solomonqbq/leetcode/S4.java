package cn.solomonqbq.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 滤掉重复数字
 * Created by 宝齐 on 2017/5/15.
 */
public class S4 {
    public int singleNumber(int[] nums) {
        Set<Integer> s = new HashSet();
        for(int i:nums){
            if (s.contains(i)){
                s.remove(i);
            }else{
                s.add(i);
            }
        }
        return s.iterator().next();
    }

    public static void main(String[] args) {

    }
}
