package cn.solomonqbq.algorithm;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * 递归计算字符串编辑距离
 * Created by 宝齐 on 2017/6/13.
 */
public class EditDistance {
    Memo[][] memos;

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        int distance = editDistance.distance("sodjfoleoidfl1py", "sodjfolioeoidflnpy", 1, 1);
        System.out.println(distance);
    }

    private int distance(String src, String dest, int i, int j) {
        if (memos == null) {
            memos = new Memo[src.length()+2][dest.length()+2];
            Arrays.stream(memos).forEach(new Consumer<Memo[]>() {
                @Override
                public void accept(Memo[] memos) {
                    for (int i = 0; i < memos.length; i++) {
                        memos[i] = new Memo();
                    }
                }
            });
        }
        if (memos[i][j].refCount != 0) {
            memos[i][j].refCount++;
            System.out.println("hit");
            return memos[i][j].distance;
        }
        int distance = 0;
        if (i == src.length()) {
            distance = dest.length() - j;
        } else if (j == dest.length()) {
            distance = src.length()  - i;
        } else {
            if (src.charAt(i-1) == dest.charAt(j-1)) {
                distance = distance(src, dest, i + 1, j + 1);
            } else {
                int edIns = distance(src, dest, i, j + 1) + 1;
                int edDel = distance(src, dest, i + 1, j) + 1;
                int edRep = distance(src, dest, i + 1, j + 1) + 1;
                distance = Math.min(edIns, Math.min(edDel, edRep));
            }
        }
        memos[i][j].distance = distance;
        memos[i][j].refCount = 1;
        return distance;
    }

    class Memo {
        int distance = 0;
        int refCount = 0;
    }
}
