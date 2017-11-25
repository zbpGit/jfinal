package com.zbp.cron;

/**
 * Created by Administrator on 2017/7/1.
 */
public class Task implements Runnable {

    public void run() {
        System.out.println("Task execute...");
    }
}
