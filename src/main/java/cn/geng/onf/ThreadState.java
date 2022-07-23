package cn.geng.onf;

/**
 * life cycle of a thread
 * one ~
 *
 * <P>1. new
 * <P>2. active:该状态包含两种状态
 * *          runnable可运行的  running运行中
 * <P>3.Blocked / Waiting
 * <P>blocked
 * *              thread A and B 都想用printer(Resource)打印一些data,
 * *              但是threadB正处于a span of time,正在用printer打印data
 * *              因此,此时的thread A就处于 blocked状态
 * *          waiting:thread.join()/thread.wait()都能进入该状态
 * *              处于blocked状态的在等待reactive,因此blocked也叫做waiting状态</>
 * <P>例一:
 * *              main.join()的时候,main thread就处于blocked/waiting状态
 * *              只有当child thread completed their job,
 * *              main thread 从blocked/waiting转外active状态</>
 * <p>4.Timed waiting
 * * 第三状态waiting的时候,可能会出现一直waiting状态(starvation),谁也不知道什么时候会执行,
 * <p>例二:
 * * thread A is in active,thread B always waiting,
 * 这时候就会导致starvation.
 * * Solution:thread.sleep()ing,thread处于waiting状态,
 * * 一旦thread.sleep()结束,该线程就会开始执行.
 *
 * <p>5.Terminated
 * * Ⅰ:When a thread has finished its job, then it exists or terminates normally.
 * * Ⅱ:Abnormal termination: It occurs when some unusual events such as
 * * an unhandled exception or segmentation fault.
 * Terminate意味着不在System中了,换句话说,该线程已死,
 * * 且没有办法让一个线程从 Terminate  --->    active
 * <p>Ⅱ:
 * * 1.how to get state of thread
 * * you could use method which named threadName.getState()
 *
 * <p>
 *
 * <p>
 *
 * <p>
 */


class CygRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println("The state of thread t1 while it invoked the method join() on thread t2 -" + ThreadState.t1.getState());

        try {
            Thread.sleep(200);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

public class ThreadState implements Runnable {
    public static Thread t1;
    public static ThreadState obj;

    public static void main(String[] args) {
        obj = new ThreadState();
        t1 = new Thread(obj);
        // The thread t1 is currently in the NEW state.
        System.out.println("The state of thread t1 after spawning it - " + t1.getState());

        t1.start();
        // thread t1 is moved to the Runnable state
        System.out.println("The state of thread t1 after invoking the method start() on it - " + t1.getState());
    }

    public void run() {
        CygRunnable myObj = new CygRunnable();
        Thread t2 = new Thread(myObj);

        System.out.println("The state of thread t2 after spawning it - " + t2.getState());
        t2.start();

        // thread t2 is moved to the runnable state
        System.out.println("the state of thread t2 after calling the method start() on it - " + t2.getState());

        try {
        // moving the thread t1 to the state timed waiting
            Thread.sleep(200);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println("The state of thread t2 after invoking the method sleep() on it - " + t2.getState());

        try {
            // waiting for thread t2 to complete its execution
            t2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("The state of thread t2 when it has completed it's execution - " + t2.getState());
    }

}
