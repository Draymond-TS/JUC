package com.Draymond.JUC;
import	java.util.concurrent.CopyOnWriteArrayList;
import	java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import	java.util.List;

public class TestCopyOnWriteArrayList {

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet : "写入并复制”
 * 注意：添加操作多时，效率低，因为每次添加进行复制，开销非常的大。
 */



    public static void main(String [] agrs){

        HelloThread helloThread=new HelloThread();

        for (int i = 0; i <10; i++){
            new Thread(helloThread).start();
        }


    }



}

class  HelloThread implements Runnable {

   //private  static List <String> list= Collections.synchronizedList(new ArrayList<String>());

    private  static CopyOnWriteArrayList <String> list =new CopyOnWriteArrayList < String>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }


    @Override
    public void run() {
      Iterator<String> it =list.iterator();
      while (it.hasNext()) {
          System.out.println(it.next());
          list.add("--");
      }
    }
}