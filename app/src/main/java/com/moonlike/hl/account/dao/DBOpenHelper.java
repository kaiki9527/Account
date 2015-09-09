package com.moonlike.hl.account.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hl on 2015-9-8.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "account.db";

    public DBOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //支出表
        db.execSQL("create table tb_outaccount (_id integer primary key,money decimal,time varchar(10),type varchar(10),address varchar(100),mark varchar(200))");
        //收入表
        db.execSQL("create table tb_inaccount (_id integer primary key,money decimal,time varchar(10),type varchar(10),handler varchar(100),mark varchar(200))");
        //保存密码表
        db.execSQL("create table tb_pwd(password varchar(20))");
        //便签信息
        db.execSQL("create table tb_flag(_id integer primary key,flag varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
