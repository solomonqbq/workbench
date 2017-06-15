package cn.solomonqbq.algorithm;

import java.util.StringJoiner;

/**
 * Created by 宝齐 on 2017/6/15.
 */
public class Bag2 {
    Integer[] weights;
    Integer[] values;
    Integer[][] matrix;

    public static void main(String[] args) {
        Bag2 bag2 = new Bag2();
        int value = bag2.calc(new Integer[]{35, 30, 60, 50, 40, 10, 25}, new Integer[]{10, 40, 30, 50, 35, 40, 30},
                150);
        System.out.println(value);
    }

    private int calc(Integer[] weight, Integer[] values, int capacity) {
        this.weights = weight;
        this.values = values;
        this.matrix = new Integer[capacity + 1][values.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = -1;
            }
        }
        return getMaxValue(capacity, values.length - 1);
    }

    private int getMaxValue(int capacity, int index) {
        int retGold = 0;
        if (matrix[capacity][index] != -1) {
            retGold = matrix[capacity][index];
        } else {
            if (index == 0) {
                if (capacity >= weights[index]) {
                    retGold = values[index];
                } else {
                    retGold = 0;
                }
            } else {
                if (capacity > weights[index]) {
                    retGold = Math.max(getMaxValue(capacity - weights[index], index - 1) + values[index],
                            getMaxValue(capacity,index-1));
                } else {
                    retGold = getMaxValue(capacity, index - 1);
                }
            }
        }
        matrix[capacity][index] = retGold;
        printMatrix();
        return retGold;
    }

    private void printMatrix() {
        System.out.println("==============================================");
        for (int i = 0; i < matrix.length; i++) {
            StringJoiner stringJoiner = new StringJoiner(",");
            for (int j = 0; j < matrix[i].length; j++) {
                stringJoiner.add(String.valueOf(matrix[i][j]));
            }
            System.out.println(stringJoiner.toString());
        }
    }

}
