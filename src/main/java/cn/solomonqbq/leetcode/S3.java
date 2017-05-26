package cn.solomonqbq.leetcode;

/**
 * 两个矩形是否相交
 * Created by 宝齐 on 2017/5/15.
 */
public class S3 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        Square s1 = new Square(A, B, C, D);
        Square s2 = new Square(E, F, G, H);
        if (s1.contains(s2)) {
            return s1.getArea();
        }
        if (s2.contains(s1)) {
            return s2.getArea();
        }
        return s1.getArea()+s2.getArea()-s1.getDupArea(s2);
    }

    class Square {
        int a, b, c, d = 0;

        Square(int A, int B, int C, int D) {
            this.a = A;
            this.b = B;
            this.c = C;
            this.d = D;
        }

        boolean contains(Square square) {
            if (this.a <= square.a && this.b <= square.b && this.c >= square.c && this.d >= square.d) {
                return true;
            } else {
                return false;
            }
        }

        int getArea() {
            return (this.c - this.a) * (this.d - this.b);
        }

        boolean inSquare(int x, int y) {
            return x >= this.a && x <= this.c && y >= this.b && y <= this.d;
        }

        int[] min(int oldx,int oldy,int  newx,int newy){
            if (oldx<newx){
                return new int[]{oldx,oldy};
            }else if(oldx>newx){
                return new int[]{newx,newy};
            }else {
                if (oldy<newy){
                    return new int[]{oldx,oldy};
                }else {
                    return new int[]{newx,newy};
                }
            }
        }

        int[] max(int oldx,int oldy,int  newx,int newy){
            if (oldx<newx){
                return new int[]{newx,newy};
            }else if(oldx>newx){
                return new int[]{oldx,oldy};
            }else {
                if (oldy<newy){
                    return new int[]{newx,newy};
                }else {
                    return new int[]{oldx,oldy};
                }
            }
        }

        int getDupArea(Square square) {
            int a1 =Integer.MAX_VALUE,b1 = Integer.MAX_VALUE;
            int c1 =Integer.MIN_VALUE,d1 = Integer.MIN_VALUE;
            boolean flag = false;
            for (int i = this.a; i <= this.c; i++) {
                for (int j = this.b; j <= this.d; j++) {
                    if (square.inSquare(i, j)) {
                        flag = true;
                        int[] m = min(a1,b1,i,j);
                        a1=m[0];
                        b1=m[1];
                        m = max(a1,b1,i,j);
                        c1 = m[0];
                        d1 = m[1];
                    }
                }
            }
            if (flag) {
                return new Square(a1, b1, c1, d1).getArea();
            } else {
                return 0;
            }

        }
    }

    public static void main(String[] args) {
        S3 s3 = new S3();
//        System.out.println(s3.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        System.out.println(s3.computeArea(-2,-2, 2, 2, 3, 3, 4, 4));
    }
}
