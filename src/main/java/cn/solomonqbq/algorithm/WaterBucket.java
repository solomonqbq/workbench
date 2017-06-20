package cn.solomonqbq.algorithm;

import java.util.*;
import java.util.function.Consumer;

/**
 * 8、5、3水桶问题，倒出两个4升
 * Created by 宝齐 on 2017/6/20.
 */
public class WaterBucket {
    private final static Integer[] CAPACITY = {8, 5, 3};

    public static void main(String[] args) {
        WaterBucket waterBucket = new WaterBucket();
        List<String> paths = waterBucket.find(new Integer[]{8, 0, 0});
        System.out.println(paths);
    }

    private List<String> find(Integer[] buckets) {
        Node node = new Node(buckets);
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        List<String> result = new ArrayList<>();
        recursiveFind(stack,result);
        return result;
    }

    private void recursiveFind(Stack<Node> stack, List<String> paths) {
        Node node = stack.peek();
        if (node.bucket[0] == 4 && node.bucket[1] == 4) {
            //有解了
            StringJoiner stringJoiner = new StringJoiner("->", "{", "}\n");
            stack.forEach(new Consumer<Node>() {
                @Override
                public void accept(Node node) {
                    StringJoiner sj = new StringJoiner(",", "[", "]");
                    for (int i : node.bucket) {
                        sj.add(String.valueOf(i));
                    }
                    stringJoiner.add(sj.toString());
                }
            });
            paths.add(stringJoiner.toString());
        } else {
            Set<Node> nodes = possibleNodes(node);
            for (Node n:nodes){
                if (!stack.contains(n)){
                    stack.push(n);
                    recursiveFind(stack,paths);
                    stack.pop();
                }
            }
        }
    }

    private Set<Node> possibleNodes(Node node) {
        Set<Node> set = new HashSet<>();
        for (int i =0;i<node.bucket.length;i++){
            for (int j = i+1;j<node.bucket.length;j++){
                if (canPourOut(i,node.bucket[i],j,node.bucket[j])){
                    Integer[] temp= Arrays.copyOf(node.bucket,node.bucket.length);
                    temp[j] = temp[j]+temp[i];
                    temp[i] = 0;
                    set.add(new Node(temp));
                }
                if (canPourOut(j,node.bucket[j],i,node.bucket[i])){
                    Integer[] temp= Arrays.copyOf(node.bucket,node.bucket.length);
                    temp[i] = temp[i]+temp[j];
                    temp[j]=0;
                    set.add(new Node(temp));
                }
                if (canPourToFull(i,node.bucket[i],j,node.bucket[j])){
                    Integer[] temp= Arrays.copyOf(node.bucket,node.bucket.length);
                    temp[i] = temp[i]+temp[j]-CAPACITY[j];
                    temp[j] = CAPACITY[j];
                    set.add(new Node(temp));
                }
                if (canPourToFull(j,node.bucket[j],i,node.bucket[i])){
                    Integer[] temp= Arrays.copyOf(node.bucket,node.bucket.length);
                    temp[j] = temp[j]+temp[i]-CAPACITY[i];
                    temp[i] = CAPACITY[i];
                    set.add(new Node(temp));
                }
            }
        }
        return set;
    }

    private boolean canPourToFull(int fromIndex,int from,int toIndex,int to){
        if (from==0){
            return false;
        }
        if (to==CAPACITY[toIndex]){
            return false;
        }
        if (from+to>=CAPACITY[toIndex]){
            return true;
        }else{
            return false;
        }
    }

    private boolean canPourOut(int fromIndex,int from,int toIndex,int to){
        if (from==0){
            return false;
        }
        if (to==CAPACITY[toIndex]){
            return false;
        }
        if (from+to<=CAPACITY[toIndex]){
            return true;
        }else{
            return false;
        }
    }

    private class Node {
        Integer[] bucket;

        public Node(Integer[] buckets) {
            this.bucket = buckets;
        }

        public int hashCode() {
            return new StringBuilder().append(bucket[0]).append(bucket[1]).append(bucket[2]).toString().hashCode();
        }

        public boolean equals(Object o) {
            Node another = (Node) o;
            for (int i = 0; i < this.bucket.length; i++) {
                if (bucket[i] != another.bucket[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
