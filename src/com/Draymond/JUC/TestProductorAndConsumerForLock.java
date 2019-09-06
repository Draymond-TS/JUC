package com.Draymond.JUC;
import java.util.concurrent.locks.Condition;
import	java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Lock;
import	java.util.concurrent.locks.ReentrantLock;

import java.util.function.Consumer;

/* P8
 * 生产者和消费者是案例
 *
 * 解决虚假唤醒的问题
 */
public class TestProductorAndConsumerForLock {

    public static void main(String[] agrs) {
        ClerkCopy clerk = new ClerkCopy();

        ProductorCopy productor = new ProductorCopy(clerk);

        ConsumerCopy conSumer = new ConsumerCopy(clerk);


        new Thread(productor, "生产者A").start();
        new Thread(conSumer, "消费者A").start();
        new Thread(productor, "生产者B").start();
        new Thread(conSumer, "消费者B").start();
    }

}

class ClerkCopy {
    private int product = 0;

    private Lock locker=new ReentrantLock();

    private Condition condition = locker.newCondition();


    //进货
    public /*synchronized */void get() {

        locker.lock();


        try{
            while (product >= 10) {//为了避免虚假唤醒，应该总是在使用循环
                System.out.println("产品已满！！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            condition.signalAll();

        }finally {
            locker.unlock();
        }


    }


    //销售
    public  void sale() {

        locker.lock();


        try{
            while (product <= 0) {//为了避免虚假唤醒，应该总是在使用循环
                System.out.println("缺货！！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            condition.signalAll();

        }finally {
            locker.unlock();
        }



    }
}


class ProductorCopy implements Runnable {

    private ClerkCopy clerk;

    public ProductorCopy(ClerkCopy clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {


        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}


class ConsumerCopy implements Runnable {

    private ClerkCopy clerk;

    public ConsumerCopy(ClerkCopy clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
