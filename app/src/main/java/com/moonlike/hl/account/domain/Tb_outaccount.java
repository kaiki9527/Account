package com.moonlike.hl.account.domain;

/**
 * Created by hl on 2015-9-8.
 */
public class Tb_outaccount {
    private int _id;// 存储支出编号
    private double money;// 存储支出金额
    private String time;// 存储支出时间
    private String type;// 存储支出类别
    private String address;// 存储支出地点
    private String mark;// 存储支出备注

    public Tb_outaccount() {
    }

    public Tb_outaccount(int _id, double money, String time, String type, String address, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
