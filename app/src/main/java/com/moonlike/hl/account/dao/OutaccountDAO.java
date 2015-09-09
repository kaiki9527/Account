package com.moonlike.hl.account.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.moonlike.hl.account.domain.Tb_inaccount;
import com.moonlike.hl.account.domain.Tb_outaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hl on 2015-9-8.
 */
public class OutaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public OutaccountDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    /*
    添加支出信息
     */
    public void add(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?," +
                "?,?)", new Object[]{tb_outaccount.get_id(), tb_outaccount.getMoney(),
                tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getAddress(),
                tb_outaccount.getAddress()});
    }

    //更新支出信息
    public void update(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();
        db.execSQL("update tb_outaccount set money = ?,time = ?,type = ?,address = ?,mark = ? " +
                "where _id = ?", new Object[]{tb_outaccount.getMoney(), tb_outaccount.getTime(),
                tb_outaccount.getType(), tb_outaccount.getAddress(), tb_outaccount.getMark(),
                tb_outaccount.get_id()});
    }

    //查找支出信息
    public Tb_outaccount find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount " +
                "where _id = ?", new
                String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor
                    .getDouble(cursor.getColumnIndex("money")), cursor.getString(cursor
                    .getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getColumnName(cursor.getColumnIndex("address")), cursor.getString
                    (cursor.getColumnIndex("mark")));
        }
        return null;

    }

    //删除支出信息
    public void delete(Integer... ids){
        if (ids.length>0){
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<ids.length;i++){
                sb.append('?').append(",");
            }
            sb.deleteCharAt(sb.length()-1);

            db = helper.getWritableDatabase();
            db.execSQL("delete from tb_outaccount where _id in (" + sb + ")",
                    (Object[]) ids);
        }
    }

    //遍历支出信息
    public List<Tb_outaccount> getScrollData(int start, int count) {
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();// 创建集合对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有支出信息
        Cursor cursor = db.rawQuery("select * from tb_outaccount limit ?,?",
                new String[] { String.valueOf(start), String.valueOf(count) });
        while (cursor.moveToNext())// 遍历所有的支出信息
        {
            // 将遍历到的支出信息添加到集合中
            tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor
                    .getColumnIndex("_id")), cursor.getDouble(cursor
                    .getColumnIndex("money")), cursor.getString(cursor
                    .getColumnIndex("time")), cursor.getString(cursor
                    .getColumnIndex("type")), cursor.getString(cursor
                    .getColumnIndex("address")), cursor.getString(cursor
                    .getColumnIndex("mark"))));
        }
        return tb_outaccount;// 返回集合
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount",
                null);// 获取支出信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取支出最大编号
     *
     * @return
     */
    public int getMaxId() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount", null);// 获取支出信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }

}
