package com.Draymond.JUC;

import java.util.concurrent.locks.Lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 一、用于解决多线程安全问题
 *
 * synchronized：隐式锁
 * 1、同步代码块
 *
 * 2、同步方法
 *
 * jdk1.5后
 * 3、同步锁Lock
 * 注意：是一个显示锁，需要通过lock方法上锁，必须通过unlock()方法进行释放锁
 *
 * */
public class TestLock {
    public static void main(String [] agrs){
        Ticket ticket = new Ticket();
        new Thread(ticket,"綫程A").start();
        new Thread(ticket,"綫程B").start();;
        new Thread(ticket,"綫程c").start();;
    }
}

class Ticket implements Runnable {

    private int tickNumber = 100;

    Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {
            lock.lock();

            try {
                if (tickNumber > 0) {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为：" + --tickNumber);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


    }
}