package cn.geng.one;

import lombok.Data;

/**
 * 之前的印象中的线程的用法，
 * 该案例没有任何可观性
 */
@Data
public class One implements Runnable,Cloneable {
    private Integer number = 0;
    private Dog dog;
    @Override
    public void run() {
//        doSome(number % 2 == 0);
        number++;
        System.out.println(Thread.currentThread().getName() + " is running " + number);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        One clone1 = (One) clone;
        this.dog = new Dog();
        return clone;
    }

    private void doSome(Boolean aBoolean){
        if(aBoolean){
            number++;
            return;
        }
        number--;
    }

    static class InnerOne extends Thread {
        private Integer number;
        private Dog dog;
        InnerOne(One one){
            this.number = one.number;
        }

        @Override
        public void run() {
            number++;
            System.out.println(Thread.currentThread().getName() + " is running (InnerOne...) " + number);
        }
    }
    /**
     * 不知道如何区别两个
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        One one = new One();
        One one1 = new One();
        //one == one1 ，one and one1 都是main线程
        System.out.println("one == one1 : " + one.equals(one1));
        One clone = (One) one.clone();
        //one != clone 并且，one and one1 也 都是main线程
        System.out.println("one == clone : " + one.equals(clone));
        InnerOne innerOne = new One.InnerOne(one);
        innerOne.setName("cyg is a Dog");
        System.out.println("one == innerOne : " + one.equals(innerOne));
        innerOne.start();
        one.run();

    }
}
