package com.gate.big.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁模拟缓存
 * Created by zhangchengfu on 2018/3/18.
 */
// 模拟缓存
// 加锁解锁要一致：解没加过的锁会抛出异常；加锁不解会造成死锁
public class CacheSimulation {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String data = (String) getData("key");
                    System.out.println(data);
                }
            }).start();
        }
    }

    private static Map<String, Object> cache = new HashMap<String, Object>(); // 保存缓存
    private static ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static Object getData(String key) {
        rwl.readLock().lock();
        Object value = cache.get(key);
        if (value == null) {
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            if (cache.get(key) == null) { // 防止几个线程都阻塞在writeLock.lock()
                value = "abcde"; // 模拟获得数据
                System.out.println("get");
                cache.put("key",value);
            }
            rwl.writeLock().unlock();
        }
        return value;
    }
}
