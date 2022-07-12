package cn.geng.one;

/**
 * two thread use : Thread.sleep() 不同的位置不同的显示效果
 */
public class Oni implements Runnable {

    private static Integer num = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread cyg = new Thread(new Oni(), "cyg");
        cyg.start();
//        Thread.sleep(3 * 1000);
        System.out.println(num);
//        Thread.sleep(3 * 1000);
        num++;
        System.out.println(num);
    }

    @Override
    public void run() {
        num++;
    }
}
