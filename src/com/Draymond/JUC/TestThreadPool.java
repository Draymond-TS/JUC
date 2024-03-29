package com.Draymond.JUC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/* 一、线程池：提供了一个线程队列。队列保存这所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度
 *
 * 二、线程的体系结构
 *    java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 *       |--**ExecutorService 子接口 ： 线程池的主要接口
 *           |--ThreadexecutorServiceExecutor 线程池的实现类
 *           |--ScheduleExecutorService 子接口 ：负责线程的调度
 *              |--ScheduleThreadexecutorServiceExecutor ：继承 ThreadexecutorServiceExcutor ,实现ScheduleExecutorService
 *
 *  三、工具类 ： Executors
 *  ExecutorService newFixedThreadexecutorService() ： 创建固定大小的线程池
 *  ExecutorService newCachedThreadexecutorService() : 缓存线程池，线程池的数量不固定，可以更具需求自动的更改数量
 *  ExecutorService newSingleThreadExecutor() 创建单个线程池，线程池只有一个线程
 *
 *  ScheduleExecutorService newScheduleThreadexecutorService : 创建固定大小的线程，可以延迟或定时的执行任务
 * */
public class TestThreadPool {
    public static void main(String [] agrs) throws  Exception{
        //1.创建线程池
        ExecutorService executorService= Executors.newFixedThreadPool(5);

        List<Future<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = executorService.submit(new Callable<Integer>(){

                @Override
                public Integer call() throws Exception {
                    int sum = 0;

                    for (int i = 0; i <= 100; i++) {
                        sum += i;
                    }

                    return sum;
                }

            });

            list.add(future);
        }

        executorService.shutdown();

        for (Future<Integer> future : list) {
            System.out.println(future.get());
        }





        //ThreadexecutorServiceDemo threadexecutorServiceDemo=new ThreadexecutorServiceDemo();

        //2.为线程池的线程分配任务
        //for(int i=0;i<10;i++){
            //创建相对应的线程数 来执行同一个任务
            //executorService.submit(threadexecutorServiceDemo);
        //}

        //3.关闭线程池
        //executorService.shutdown();;
    }

}


class ThreadexecutorServiceDemo implements Runnable{
    private int i=0;

    @Override
    public void run() {
        while (i<1000 ){
            System.out.println(Thread.currentThread().getName()+" : "+ i++);
        }
    }
}