package cn.solomonqbq.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 贪婪背包算法：
 * 物品体积w,价值p,容量C的背包，怎么装最合算？
 * Created by 宝齐 on 2017/5/26.
 */
public class Bag {
    public static void main(String[] args) {
        Bag bag = new Bag();
        bag.calc(new Integer[]{35, 30, 60, 50, 40, 10, 25}, new Integer[]{10, 40, 30, 50, 35, 40, 30}, 150);
    }

    private void calc(Integer[] w, Integer[] p, int c) {
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < w.length; i++) {
            items.add(new Item(w[i], p[i]));
        }
//        Strategy strategy = new MostValueStrategy();
//        Strategy strategy = new LightestStrategy();
        Strategy strategy = new MostUnitValueStrategy();
        int lastCapacity = strategy.pickItem(items, c);
        while (lastCapacity != -1) {
            lastCapacity = strategy.pickItem(items, lastCapacity);
        }
        int sum = 0;
        for (Item item : items) {
            if (item.status == 1) {
                sum += item.value;
                System.out.println(item);
            }
        }
        System.out.println("总价值:"+sum);
    }

    final static class Item {
        int weight;
        int value;
        int status;//0:未装、1:已装、2:超重

        Item(int w, int p) {
            this.weight = w;
            this.value = p;
            this.status = 0;
        }

        public String toString() {
            return String.format("重量:%d,价值:%d", this.weight, this.value);
        }

        float getUnitValue(){
            return (float)value/weight;
        }
    }

    private interface Strategy {
        int pickItem(List<Item> items, int capacity);
    }

    final static class MostValueStrategy implements Strategy {

        public int pickItem(List<Item> items, int capacity) {
            int index = -1;
            int value = 0;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).status == 0) {
                    if (items.get(i).weight > capacity) {
                        items.get(i).status = 2;
                    } else {
                        if (items.get(i).value > value) {
                            value = items.get(i).value;
                            index = i;
                        }
                    }
                }
            }
            if (index != -1) {
                items.get(index).status = 1;
            } else {
                return -1;
            }
            return capacity - items.get(index).weight;
        }
    }

    final static class LightestStrategy implements Strategy {

        public int pickItem(List<Item> items, int capacity) {
            int index = -1;
            int weight = Integer.MAX_VALUE;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).status == 0) {
                    if (items.get(i).weight > capacity) {
                        items.get(i).status = 2;
                    } else {
                        if (items.get(i).weight < weight) {
                            weight = items.get(i).weight;
                            index = i;
                        }
                    }
                }
            }
            if (index != -1) {
                items.get(index).status = 1;
            } else {
                return -1;
            }
            return capacity - items.get(index).weight;
        }
    }

    final static class MostUnitValueStrategy implements Strategy {

        public int pickItem(List<Item> items, int capacity) {
            int index = -1;
            float unitValue = 0;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).status == 0) {
                    if (items.get(i).weight > capacity) {
                        items.get(i).status = 2;
                    } else {
                        if (items.get(i).getUnitValue() > unitValue) {
                            unitValue = items.get(i).getUnitValue();
                            index = i;
                        }
                    }
                }
            }
            if (index != -1) {
                items.get(index).status = 1;
            } else {
                return -1;
            }
            return capacity - items.get(index).weight;
        }
    }
}
