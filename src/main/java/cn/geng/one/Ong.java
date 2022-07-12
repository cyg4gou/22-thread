package cn.geng.one;

/**
 * 1.life cycle fo Thread   // 1.4.Blocked status 阻塞状态 该状态暂时未明如何显示
 * 2.Priority of thread
 */
public class Ong {

    public static void main(String[] args) {
        // 1.1.new Status 被创建状态
        Thread thread = new Thread(new Runnable() {
            // 1.3.Running status 运行时状态：该方法由cpu分配了一个时间片执行的时候
            @Override
            public void run() {
                System.out.println("thread..... "+Thread.currentThread().getName());
            }
        });
        thread.setName("cyg is a dog");
        // 2.1.  1 - 10 优先级从小到大
//        thread.setPriority(10);
//        thread.setPriority(Thread.MAX_PRIORITY);
        // 1.2.Runnable Status 可运行状态：什么时候运行不一定
        thread.start();
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());

        //  1.5.Dead Status 死亡状态:个人感觉，不要用，用了线程还没跑就死亡了。
//        thread.stop();


        Thread cyg = new Thread("cyg");
        // 啥也不干
        cyg.start();
        System.out.println(cyg.getName());
    }
}
