package com.gate.big.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 取set集合的第一个元素
public class Set1 {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        System.out.println(set);
        //第一种方法
        if (!set.isEmpty()) {
            System.out.println(set.iterator().next());
        }
        // 第二种方法:将set集合转换成list集合,取第一个
        List list = new ArrayList(set);
        System.out.println(list.get(0));
    }
}
