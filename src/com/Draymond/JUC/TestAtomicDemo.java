package com.Draymond.JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *一、i++ 的原子问题：i++的操作实际上分为三个步骤“读-改-写”
 *      int i =10;
 *      i=i++;
 *
 *      int temp =i;
 *      i=i+1;
 *      i=temp;
 *
 *二、原子变量：jdk1.5之后，java.util.concurrent.atomic包提供了常用的原子变量
 *           1.volatile 保证了内存可见性
 *           2.CAS（Compare-And-Swap)算法保证数据的原子性
 *             CAS 算法是硬件对于并发操作共享数据的操作
 *             CAS 包含了三个操作数
 *             内存值 V
 *             预估值 A
 *             更新值 B
 *             当且仅当 V == A 的时候 V = B，否则不做任何操作
 *
 */


public class TestAtomicDemo {
    public static void main(String [] agrs){
        AtomicDemo atomicDemo=new AtomicDemo();

        for(int i=0 ;i<10;i++){
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements  Runnable {

   // private  volatile int serialNumber =0;  //volatile也不能解决原子操作的问题
    private AtomicInteger atomicInteger=new AtomicInteger();


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" : "+getSerialNumber());

    }


    public int getSerialNumber() {
        return atomicInteger.getAndIncrement();
    }


}
