package com.lxins.bluetoothdemo;

/**
 * Created by pc on 2019/12/24.
 */

public class BtInfo {
    private String name;
    private String addr;

    public BtInfo(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public String getName() {
        return name;
    }
}
