package cn.geng.onf.synchronization;

/**
 * <p>两种Synchronization的方式:1.线程同步2.进程同步
 * <p>1.thread Synchronization
 * <p>两种:A: mutual exclusive(线程互斥)  B: inter-thread communication(线程间通信)
 * <p>A:Mutual Exclusive:
 * 1.Synchronized method.
 * 2.Synchronized block.
 * 3.Static synchronization.
 * <p>B:Cooperation (Inter-thread communication in java)
 */


class Table {
    private int q = 0;
    void printTable(int n) {//method not synchronized
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -----  " + n * i);
            System.out.println(q++);
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}

class MyThread1 extends Thread {
    Table t;

    MyThread1(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(10);
    }

}

class MyThread2 extends Thread {
    Table t;

    MyThread2(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(100);
    }
}

class ExampleOne {
    public static void main(String args[]) {
        Table obj = new Table();//only one object
        MyThread1 t1 = new MyThread1(obj);
        t1.setName("thread_one");
        MyThread2 t2 = new MyThread2(obj);
        t2.setName("thread_two");
        t1.start();
        t2.start();
    }
}
