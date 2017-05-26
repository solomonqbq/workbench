package cn.solomonqbq.leetcode;

import java.util.Arrays;

/**
 * 查找数组中主元素（唯一出现过两次的）
 * Created by 宝齐 on 2017/5/15.
 */
public class S2 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2] ==nums[Math.max(0,nums.length/2-1)]?nums[nums.length/2-1]:nums[Math.max(nums
                .length-1,nums
                .length+1)];
    }
}
