package cn.solomonqbq.algorithm;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * 非递归计算字符串编辑距离
 * Created by 宝齐 on 2017/6/14.
 */
public class EditDistance2 {
    public static void main(String[] args) {
        EditDistance2 editDistance2 = new EditDistance2();
        Integer distance = editDistance2.distance("sdf","df");
        System.out.println(distance);
    }

    private Integer distance(String src, String dest) {
        int[][] matrix = new int[src.length()+1][dest.length()+1];
        for (int n = 0;n<=dest.length();n++){
            matrix[0][n] = n;
        }
        for (int n = 0;n<=src.length();n++){
            matrix[n][0] = n;
        }
        for (int i = 1;i<=src.length();i++){
            for (int j = 1;j<=dest.length();j++){
                if (src.charAt(i-1)==dest.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1];
                }else{
                    int edIns = matrix[i][j-1]+1;
                    int edDel = matrix[i-1][j]+1;
                    int edRel = matrix[i-1][j-1]+1;
                    matrix[i][j] = Math.min(Math.min(edIns,edDel),edRel);
                }
                printMatrix(matrix);
            }
        }
        return matrix[src.length()][dest.length()];
    }

    private void printMatrix(int[][] matrix){
        System.out.println("=========================================");
        Arrays.stream(matrix).forEach(new Consumer<int[]>() {
            @Override
            public void accept(int[] ints) {
                Arrays.stream(ints).forEach(i-> System.out.printf(i+","));
                System.out.println();
            }
        });
        System.out.println("=========================================");
    }
}
