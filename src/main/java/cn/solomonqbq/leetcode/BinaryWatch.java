package cn.solomonqbq.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二进制手表，递归没裁枝
 * https://leetcode.com/problems/binary-watch/#/description
 * Created by 宝齐 on 2017/6/15.
 */
public class BinaryWatch {
    public static void main(String[] args) {
        BinaryWatch binaryWatch = new BinaryWatch();
        List<String> list  = binaryWatch.readBinaryWatch(1);
        System.out.println(list);
    }

    public List<String> readBinaryWatch(int num) {
        int arrayLength = 10;//0-3是小时，后面是分钟
        List<String> result = new ArrayList<>();
        List<String> l = calc(arrayLength,num);
        for (String str:l) {
            String time = toTime(str);
            if (time!=null){
                result.add(time);
            }
        }
        return result;
    }

    private List<String> calc(int arrayLength,int n){
        List<String> l = new ArrayList<>();
        if (n>arrayLength){
            return Collections.EMPTY_LIST;
        }
        if (n==0){
            if (arrayLength>0){
                StringBuilder sb = new StringBuilder();
                for (int i = 0;i<arrayLength;i++){
                    sb.append("0");
                }
                l.add(sb.toString());
                return l;
            }else {
                return Collections.EMPTY_LIST;
            }
        }else{
            //当期站位1
            List<String> sub = calc(arrayLength-1,n-1);
            l.addAll(appendList(sub,"1"));
            if (arrayLength>=n+1){
                List<String> sub2 = calc(arrayLength-1,n);
                l.addAll(appendList(sub2,"0"));
            }
            return l;
        }
    }

    private List<String> appendList(List<String> l,String num){
        List list = new ArrayList();
        if (!l.isEmpty()){
            for (String str:l){
                list.add(num+str);
            }
        }else {
            list.add(num);
        }
        return list;
    }

    private String toTime(String str){
        String hourBinaryNum = str.substring(0,4);
        String minuteBinaryNum = str.substring(4,10);
        StringBuilder sb = new StringBuilder();
        if (hourBinaryNum.equals("0000")){
            sb.append("0");
        }else{
            int num = Integer.parseInt(hourBinaryNum,2);
            if (num>11){
                return null;
            }
            sb.append(String.format("%d",num));
        }
        sb.append(":");
        if (minuteBinaryNum.equals("000000")){
            sb.append("00");
        }else {
            int num = Integer.parseInt(minuteBinaryNum,2);
            if (num>59){
                return null;
            }
            sb.append(String.format("%02d",num));
        }
        return sb.toString();
    }
}
