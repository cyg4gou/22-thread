package cn.geng.onf.synchronization;

//example of java synchronized method
class TableTwo {
    private int q = 0;

    synchronized void printTable(int n) {//synchronized method
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -----  " + n * i);
            System.out.println(Thread.currentThread().getName() + " -----  " + q++);
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThreadTwo1 extends Thread {
    TableTwo t;

    MyThreadTwo1(TableTwo t) {
        this.t = t;
    }

    public void run() {
        t.printTable(10);
    }

}

class MyThreadTwo2 extends Thread {
    TableTwo t;

    MyThreadTwo2(TableTwo t) {
        this.t = t;
    }

    public void run() {
        t.printTable(100);
    }
}

public class ExampleTwo {
    public static void main(String[] args) {
        TableTwo obj = new TableTwo();//only one object
        MyThreadTwo1 t1 = new MyThreadTwo1(obj);
        MyThreadTwo2 t2 = new MyThreadTwo2(obj);
        t1.start();
        t2.start();
    }
}
