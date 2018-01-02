package cn.solomonqbq.leetcode;

/**
 * \* Description:
 * \* User: qinbaoqi
 * \* Date: 2017/7/28
 * \* Time: 16:05
 * \*
 * \
 */
public class TrimSpecialChar {
    public static void main(String[] args) {
        E e= null;
        switch (e){
            case A:
                System.out.println("A");
                break;
            case B:
                System.out.println("B");
                break;
            default:
                System.out.println("null");

        }
        TrimSpecialChar trimSpecialChar = new TrimSpecialChar();
        trimSpecialChar.trim("aab\b\bghi");
    }

    private void trim(String str) {
        for (int i =str.length();i>0;i--){
            System.out.println(str.substring(i-1,i));
        }
    }
}
enum E{
    A,B,C
}