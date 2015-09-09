package com.moonlike.hl.account.domain;

/**
 * Created by hl on 2015-9-8.
 */
public class Tb_pwd {
    private String password;//用户密码

    public Tb_pwd() {
    }

    public Tb_pwd(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
