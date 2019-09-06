package com.Draymond.JUC;

/* P8
 * 生产者和消费者是案例
 *
 * 解决虚假唤醒的问题
 */
public class TestProductorAndConsumer {

    public static void main(String[] agrs) {
        Clerk clerk = new Clerk();

        Productor productor = new Productor(clerk);

        Consumer conSumer = new Consumer(clerk);


        new Thread(productor, "生产者A").start();
        new Thread(conSumer, "消费者A").start();
        new Thread(productor, "生产者B").start();
        new Thread(conSumer, "消费者B").start();
    }

}

class Clerk {
    private int product = 0;

    //进货
    public synchronized void get() {
        if (product >= 10) {
            System.out.println("产品已满！！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + ++product);
        this.notifyAll();

    }


    //销售
    public synchronized void sale() {
        if (product <= 0) {
            System.out.println("缺货！！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + --product);
        this.notifyAll();


    }
}


class Productor implements Runnable {

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {


        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}


class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
