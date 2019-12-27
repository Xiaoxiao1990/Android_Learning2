package com.lxins.asynctaskdemo;

/**
 * Created by pc on 2019/12/27.
 */

public class DelayOperator {
    public void delay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
