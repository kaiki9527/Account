package com.moonlike.hl.account.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moonlike.hl.account.R;
import com.moonlike.hl.account.dao.InaccountDAO;
import com.moonlike.hl.account.domain.Tb_inaccount;

import java.util.Calendar;

public class IncomeActivity extends Activity {
    protected static final int DATE_DIALOG_ID = 0;
    private EditText et_money, et_handler, et_mark;
    private TextView tv_time;
    private Spinner spinner;
    private Button btn_ok, btn_cancel;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_account);
        initView();

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_money.setText("");
                et_handler.setText("");
                et_mark.setText("");
                spinner.setSelection(0);
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = et_money.getText().toString().trim();
                if (money.equals(""))
                    return ;
                if (!money.equals("")) {
                    InaccountDAO dao = new InaccountDAO(IncomeActivity.this);
                    Tb_inaccount tb_inaccount = new Tb_inaccount(dao.getMaxId() + 1, Double
                            .parseDouble(money), tv_time.getText().toString().trim(), spinner
                            .getSelectedItem().toString(), et_handler.getText().toString().trim()
                            , et_mark.getText().toString().trim());
                    dao.add(tb_inaccount);

                    Toast.makeText(IncomeActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    et_money.setText("");
                }else
                    Toast.makeText(IncomeActivity.this,"保存失败",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateDisplay() {
        tv_time.setText(new StringBuilder().append(mYear).append("-").append(mMonth).append("-")
                .append(mDay));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog
            .OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void initView() {
        et_money = (EditText) findViewById(R.id.et_income_money);
        et_handler = (EditText) findViewById(R.id.et_income_handler);
        et_mark = (EditText) findViewById(R.id.et_income_mark);
        tv_time = (TextView) findViewById(R.id.tv_income_time);
        spinner = (Spinner) findViewById(R.id.spinner_income);
        btn_ok = (Button) findViewById(R.id.bt_income_ok);
        btn_cancel = (Button) findViewById(R.id.bt_income_cancel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_income, menu);
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
