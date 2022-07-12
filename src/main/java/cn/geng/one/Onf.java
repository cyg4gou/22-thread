package cn.geng.one;

/**
 * 阐述了 deference between start() and run()
 */
public class Onf {
    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        Thread thread1 = new Thread(new Task());
        thread.start();//Thread-0 is running
        //thread.start();//Exception in thread "main" java.lang.IllegalThreadStateException
        thread.run();//main is running
        thread1.start();//Thread-1 is running
        thread1.run();//main is running
        /*
         * 1.start just can appear once
         * 2.start will create new thread
         * 3.run just execute in current Thread(just Main)
         * 总而言之，两者唯一的区别start会创建一个新线程，而run仅仅就像调用了一个新的方法
         */
    }
}
