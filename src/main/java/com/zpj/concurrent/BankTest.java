package com.zpj.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/6/15.
 */
public class BankTest {
    public static void main(String[] args) {
        Person per = new Person("Jack", 100.0);
        Executor pool = Executors.newFixedThreadPool(2);
        pool.execute(new Runnable() {
            @Override
            public void run() {

//                Thread.sleep(1000);
                per.setMoney(per.getMoney() - 1);
            }
        });
    }
}

class Person {
    public Person(String name, Double money) {
        this.money = money;
        this.naem = name;
    }

    private String naem;
    private Double money;

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
