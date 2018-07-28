package com.gate.big.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//写一个方法，计算斐波那契数列（1,1,2,3,5,8，...）第100项的值，方法参数为第N项，返回值为第N项的值（考虑程序运算效率，异常处理）？
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(getNum(100));
    }

    public static BigDecimal getNum(Integer n) {
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i <= 2) {
                list.add(new BigDecimal(1));
            } else {
                list.add(list.get(i - 3).add(list.get(i-2)));
            }
        }
        return list.get(n -1);
    }
}
