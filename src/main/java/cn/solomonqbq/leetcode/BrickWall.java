package cn.solomonqbq.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/brick-wall/#/description
 * Created by 宝齐 on 2017/6/16.
 */
public class BrickWall {
    public static void main(String[] args) {
        BrickWall brickWall = new BrickWall();
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 2, 2, 1));
        input.add(Arrays.asList(3, 1, 2));
        input.add(Arrays.asList(1, 3, 2));
        input.add(Arrays.asList(2, 4));
        input.add(Arrays.asList(3, 1, 2));
        input.add(Arrays.asList(1, 3, 1, 1));
        System.out.println(brickWall.leastBricks(input));
    }

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> l : wall) {
            int sum = 0;
            for (int n = 0;n<l.size()-1;n++) {
                sum += l.get(n);
                map.put(sum,map.getOrDefault(sum,0)+1);
                max = Math.max(max,map.get(sum));
            }
        }
        return wall.size()-max;
    }
}
