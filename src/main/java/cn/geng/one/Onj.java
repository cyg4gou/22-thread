package cn.geng.one;

/**
 * two thread use : 关键method   isActive()
 * the cycle time is not confirm every time
 */
public class Onj extends Thread{

    private static int num;

    public static void main(String[] args) {
        Onj cyg = new Onj();
        cyg.start();
        while(cyg.isAlive()){
//            num++;   不知道为啥不能够这样统计次数
            System.out.println("thread of cyg is activing ... ");
        }
        System.out.println(num);
        num++;
        System.out.println(num);
    }
    @Override
    public void run() {
        num++;
    }
}
