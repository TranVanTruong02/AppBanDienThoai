package com.example.appbandienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.ultil.Chech_internet;

public class NoInternetActivity extends AppCompatActivity {
    AppCompatButton btThulai;
    public static Context manhinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        AnhXa_ThuLai();
        ClickButton();
        KiemTra_ThuLai();
    }
    private void AnhXa_ThuLai(){
        btThulai = (AppCompatButton) findViewById(R.id.bt_thulai);
    }
    private void ClickButton(){
        btThulai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        int color = Color.parseColor("#ededed");
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
    private void KiemTra_ThuLai(){
        btThulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
                    Intent intent = new Intent(getApplicationContext(), manhinh.getClass());
                    startActivity(intent);
                }
                else {
                    Chech_internet.ShowToast(getApplicationContext(), "Bạn vui lòng kiểm tra kết nối internet");
                }
            }
        });
    }
}