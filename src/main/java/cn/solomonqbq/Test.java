package cn.solomonqbq;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Description:
 *
 * @author: qinbaoqi
 * Date: 2017/11/2
 * Time: 16:27
 */
public class Test {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(now);
        System.out.println(now + Duration.ofMinutes(10).toMillis());
    }
}
