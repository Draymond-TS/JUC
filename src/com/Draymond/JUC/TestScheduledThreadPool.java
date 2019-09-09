package com.Draymond.JUC;

import java.util.Random;
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
public class TestScheduledThreadPool {
    public static void main(String [] agrs) throws  Exception{
        ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(5);

        for(int i =0;i<5;i++){
            ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);//生成随机数
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);

            System.out.println(schedule.get());
        }


        scheduledExecutorService.shutdown();

    }

}
