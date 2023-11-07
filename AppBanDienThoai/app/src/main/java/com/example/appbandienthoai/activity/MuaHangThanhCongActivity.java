package com.example.appbandienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.ultil.Chech_internet;

public class MuaHangThanhCongActivity extends AppCompatActivity {
    Toolbar toolbarThanhcong;
    AppCompatButton btThanhcong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_hang_thanh_cong);
        AnhXa_ThanhCong();
        ToolBar_ThanhCong();
        ClickButton();
        Button_ThanhCong();
    }
    private void AnhXa_ThanhCong(){
        toolbarThanhcong = (Toolbar) findViewById(R.id.thanhcong_toolbar);
        btThanhcong = (AppCompatButton) findViewById(R.id.thanhcong_button);
    }
    private void ToolBar_ThanhCong(){
        setSupportActionBar(toolbarThanhcong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThanhcong.setNavigationIcon(R.drawable.ic_left);
        toolbarThanhcong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void ClickButton(){
        btThanhcong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        int color = Color.parseColor("#dedede");
                        view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });
    }
    private void Button_ThanhCong(){
        btThanhcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
                    Intent intent = new Intent(getApplicationContext(), TrangChinhActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                    NoInternetActivity.manhinh = MuaHangThanhCongActivity.this;
                    startActivity(intent);
                }
            }
        });
    }
}