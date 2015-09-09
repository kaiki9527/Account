package com.moonlike.hl.account.domain;

/**
 * Created by hl on 2015-9-8.
 */
public class Tb_flag {

    private int _id;
    private String flag;

    public Tb_flag() {
    }

    public Tb_flag(int _id, String flag) {
        this._id = _id;
        this.flag = flag;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
