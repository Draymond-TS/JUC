package com.Draymond.JUC;
/*
 * 一、volatile 关键字：当多个线程进行操作共享数据，可以保证内存中的数据可见。
 *                     相较于synchronized 是一种较为轻量的同步策略
 *
 *
 * 注意：
 * 1.volatile 不具备“互斥性”
 * 2 volatile 不能保证变量的原子性
 */
public class TestVolatile {
    public static void main(String [] agrs){
        ThreadDemo threadDemo = new ThreadDemo();

        new Thread(threadDemo).start();


       while(true){
            if(threadDemo.isFlag()){
                System.out.println("主线程获得flag--->"+threadDemo.isFlag());
            }
        }

    }
}

class ThreadDemo implements  Runnable{

    private volatile boolean flag =false;


    @Override
    public void run() {
        setFlag(true);

        System.out.println("子线程的flag-->"+flag);

    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}