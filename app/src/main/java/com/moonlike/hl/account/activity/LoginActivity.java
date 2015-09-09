package com.moonlike.hl.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moonlike.hl.account.R;
import com.moonlike.hl.account.dao.PwdDAO;

/**
 * Created by hl on 2015-9-8.
 */
public class LoginActivity extends Activity {

    private EditText editText;
    private Button button_ok;
    private Button button_clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editText = (EditText) findViewById(R.id.et_pwd);
        button_ok = (Button) findViewById(R.id.bt_pwd_ok);
        button_clear = (Button) findViewById(R.id.bt_pwd_clear);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PwdDAO dao = new PwdDAO(LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                if (dao.getCount() == 0 || dao.find().getPassword().isEmpty() && editText.getText().toString().trim().isEmpty()){
                    startActivity(intent);
                    finish();
                }else {
                    if(dao.find().getPassword().equals(editText.getText().toString().trim())){
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                editText.setText("");
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                editText.setFocusable(true);
            }
        });
    }
}
