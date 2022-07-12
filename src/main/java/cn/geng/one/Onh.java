package cn.geng.one;

/**
 * 双线程试用Thread.sleep(),从而保证线程的执行顺序
 */
public class Onh {
    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("the name of current thread : " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread_one = new Thread(task,"thread one");
        Thread thread_two = new Thread(task, "thread two");
        thread_one.start();
        Thread.sleep(3 * 1000);
        thread_two.start();
    }
}
