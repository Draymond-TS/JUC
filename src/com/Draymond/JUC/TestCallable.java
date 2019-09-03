package com.Draymond.JUC;
import	java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
   public static void main(String [] agrs){
        CallableDemo callableDemo=new CallableDemo();


        //1.执行Callable方法，需要FutureTask实现类的支持，用于接收运算结果
       FutureTask<Integer> task = new FutureTask<Integer> (callableDemo);


       new Thread(task).start();


        //2.接受线程运算后的结果
       try {
           Integer result = task.get();
           System.out.println(result);
           System.out.println("-----------------");
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       }

   }

}

class CallableDemo implements  Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for(int i = 0; i <= 100; i++){
            sum += i;
        }
        return sum;
    }
}
