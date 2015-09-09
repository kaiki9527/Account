package com.moonlike.hl.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.moonlike.hl.account.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private GridView gv;
    private String[] titles = new String[]{"新增支出", "新增收入", "我的支出", "我的收入", "数据管理", "系统设置",
            "收支便签", "退出"};
    private int[] imageIds = new int[]{R.drawable.addoutaccount, R.drawable.addinaccount, R
            .drawable.outaccountinfo, R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable
            .sysset, R.drawable.accountflag, R.drawable.exit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.gv_main);

        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < titles.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("imageId", imageIds[i]);//添加图像资源的ID
            map.put("title", titles[i]);//按序号做ItemText
            lstImageItem.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, lstImageItem, R.layout
                .main_gvitem, new String[]{"imageId", "title"}, new int[]{R.id.iv_main_item, R.id
                .tv_main_item});

        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 1:
                        intent = new Intent(MainActivity.this,IncomeActivity.class);
                        startActivity(intent);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
