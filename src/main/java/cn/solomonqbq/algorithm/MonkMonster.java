package cn.solomonqbq.algorithm;

import java.util.*;

/**
 * Created by 宝齐 on 2017/6/21.
 */
public class MonkMonster {
    public static void main(String[] args) {
        MonkMonster monkMonster = new MonkMonster();
        /**
         * 0是左岸和尚，1是左岸妖怪，2是右岸和尚，3是右岸妖怪
         */
        List<String> result = monkMonster.find(new Integer[]{3, 3, 0, 0});
        System.out.println(result);
    }

    private List<String> find(Integer[] status) {
        Status begin = new Status(status);
        List<String> result = new ArrayList<>();
        Stack<Status> stack = new Stack<>();
        stack.add(begin);
        recursiveFind(stack,result);
        return result;
    }

    private void recursiveFind(Stack<Status> stack,List<String> result){
        if (stack.peek().isEnd()){
            StringJoiner stringJoiner = new StringJoiner("->", "{", "}\n");
            stack.forEach(s->{
                stringJoiner.add(s.toString());
            });
            result.add(stringJoiner.toString());
            return;
        }else {
            for (Status s:stack.peek().possibleStatus()){
                if (s.isValid()&&!stack.contains(s)){
                    stack.push(s);
                    recursiveFind(stack,result);
                    stack.pop();
                }
            }
        }
    }

    class Status {
        Integer[] current = null;
        Side side;

        Status(Integer[] status) {
            this.current = status;
            this.side = Side.LEFT;
        }

        public String toString(){
            StringJoiner sj = new StringJoiner(",", "[", "]");
            Arrays.stream(current).forEach(i->{
                sj.add(String.valueOf(i));
            });
            return sj.toString();
        }

        Status(Integer[] status, Side side) {
            this.current = status;
            this.side = side;
        }

        public int hashCode() {
            return Arrays.deepHashCode(current)+side.hashCode();
        }

        public boolean equals(Object o) {
            Status another = (Status) o;
            return Arrays.deepEquals(current,another.current)&&side==another.side;
        }

        private boolean isEnd() {
            return current[0] == 0 && current[1] == 0 && current[2] == 3 && current[3] == 3;
        }

        private boolean isValid() {
            if (current[0] < current[1]&&current[0]!=0) {
                return false;
            }
            if (current[2] < current[3]&&current[2]!=0) {
                return false;
            }
            return true;
        }

        Status copy(){
            Integer[] array = Arrays.copyOf(current,current.length);
            Status s = new Status(array,side);
            return s;
        }

        Set<Status> move(Integer number) {
            Set<Status> set = new HashSet<>();
            int index = 0;
            if (side == Side.LEFT) {
                index = 0;
            } else {
                index = 2;
            }
            for (int n = 0; n <= number; n++) {
                if (n <= current[index] && (number - n) <= current[index+1]) {
                    Status newStatus = copy();
                    newStatus.changeSide();
                    //源
                    newStatus.current[index] = newStatus.current[index]-n;
                    newStatus.current[index+1] = newStatus.current[index+1]-(number-n);
                    //目标
                    newStatus.current[(index+2)%4] = newStatus.current[(index+2)%4]+n;
                    newStatus.current[(index+2)%4+1] = newStatus.current[(index+2)%4+1]+(number-n);
                    if (newStatus.isValid()){
                        set.add(newStatus);
                    }
                }
            }
            return set;
        }

        void changeSide(){
            if (side==Side.LEFT){
                side = Side.RIGHT;
            }else{
                side = Side.LEFT;
            }
        }

        Set<Status> possibleStatus() {
            Set<Status> statuses = new HashSet<>();
            if (isEnd()) {
                return Collections.EMPTY_SET;
            } else {
                //移动船，装0个人
                statuses.addAll(move(0));
                //移动船，装1个人
                statuses.addAll(move(1));
                //移动船，2个人
                statuses.addAll(move(2));
                return statuses;
            }
        }
    }
}

enum Side {
    LEFT, RIGHT
}