package com.Draymond.JUC;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* 1.ReadWriteLock 读写锁
 *  写写/读写  需要“互斥”
 *  读读      不需要“互斥”
 */
public class TestReadWriteLock {
    public static void main(String [] agrs){

        ReadWriteLockDemo readWriteLockDemo=new ReadWriteLockDemo();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockDemo.read();
                }
            }, "Read").start();
        }


        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockDemo.write((int) (Math.random() * 100));
                }
            }, "Write").start();
        }

    }

}


class ReadWriteLockDemo{
    private int number =0;
    private ReadWriteLock lock=new ReentrantReadWriteLock();

    //读
    public void read(){
        lock.readLock().lock();

        try{
            System.out.println(Thread.currentThread().getName() + " : " + number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }



    //写
    public void write(int number){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName());
            this.number=number;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

}