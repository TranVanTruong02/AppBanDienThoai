package com.example.appbandienthoai.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class SuaThongTinTaiKhoanActivity extends AppCompatActivity {
    //Biến giao diện
    Toolbar toolbarSuathongtin;
    TextInputEditText inputTennguoidung, inputSdt, inputMatkhau1, inputMatkhau2, inputMatkhau3;
    TextInputLayout textTennguoidung, textSdt, textMatkhau1, textMatkhau2, textMatkhau3;
    AppCompatButton btSuathongtin;

    //Hàm kiểm tra dữ liệu
    Boolean kt_tennguoidung = false;
    Boolean kt_sodienthoai = false;
    Boolean kt_matkhau1 = false;
    Boolean kt_matkhau2 = false;
    Boolean kt_matkhau3 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin_tai_khoan);
        AnhXa_SuaThongTin();
        if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
            ToolBar_SuaThongTin();
            ClickButton();
            KiemTraThaoTacNhap();
            LayDuLieu();
            UpdateDaTa();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = SuaThongTinTaiKhoanActivity.this;
            startActivity(intent);
        }
    }
    private void AnhXa_SuaThongTin(){
        toolbarSuathongtin = (Toolbar) findViewById(R.id.sua_toobar);
        textTennguoidung = (TextInputLayout) findViewById(R.id.sua_text_tennguoidung);
        textSdt = (TextInputLayout) findViewById(R.id.sua_text_sdt);
        textMatkhau1 = (TextInputLayout) findViewById(R.id.sua_text_matkhau1);
        textMatkhau2 = (TextInputLayout) findViewById(R.id.sua_text_matkhau2);
        textMatkhau3 = (TextInputLayout) findViewById(R.id.sua_text_matkhau3);
        inputTennguoidung = (TextInputEditText) findViewById(R.id.sua_input_tennguoidung);
        inputSdt = (TextInputEditText) findViewById(R.id.sua_input_sdt);
        inputMatkhau1 = (TextInputEditText) findViewById(R.id.sua_input_matkhau1);
        inputMatkhau2 = (TextInputEditText) findViewById(R.id.sua_input_matkhau2);
        inputMatkhau3 = (TextInputEditText) findViewById(R.id.sua_input_matkhau3);
        btSuathongtin = (AppCompatButton) findViewById(R.id.sua_button);
    }
    private void ClickButton(){
        btSuathongtin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        view.getBackground().setColorFilter(0x10100100, PorterDuff.Mode.SRC_ATOP);
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
    private void ToolBar_SuaThongTin(){
        setSupportActionBar(toolbarSuathongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSuathongtin.setNavigationIcon(R.drawable.ic_left);
        toolbarSuathongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void KiemTraThaoTacNhap(){
        //Tên người dùng
        textTennguoidung.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textTennguoidung.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 20){
                    textTennguoidung.setError("Bạn chỉ có thể nhập tối đa 20 kí tự");
                }
                else {
                    textTennguoidung.setError("");
                    kt_tennguoidung = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Số điện Thoại
        textSdt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textSdt.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 10){
                    textSdt.setError("Bạn chỉ có thể nhập tối đa 10 kí tự");
                }
                else {
                    textSdt.setError("");
                    kt_sodienthoai = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
            });
            //Mật khẩu cũ
            textMatkhau1.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() < 3){
                        textMatkhau1.setError("Bạn phải nhập ít nhất 3 kí tự");
                    }
                    else if (charSequence.length() > 20){
                        textMatkhau1.setError("Bạn chỉ có thể nhập tối đa 20 kí tự");
                    }
                    else {
                        textMatkhau1.setError("");
                        kt_matkhau1 = true;
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            //Mật khẩu mới
            textMatkhau2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textMatkhau2.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 20){
                    textMatkhau2.setError("Bạn chỉ có thể nhập tối đa 20 kí tự");
                }
                else {
                    textMatkhau2.setError("");
                    kt_matkhau2 = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
            });
        //M#Nhập lại mật khẩu mới
        textMatkhau3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textMatkhau3.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 20){
                    textMatkhau3.setError("Bạn chỉ có thể nhập tối đa 20 kí tự");
                }
                else {
                    textMatkhau3.setError("");
                    kt_matkhau3 = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void LayDuLieu(){
        inputTennguoidung.setText(ThongTinTaiKhoanActivity.tennguoidung_sua);
        inputSdt.setText(ThongTinTaiKhoanActivity.sodienthoai_sua);
    }

    private void UpdateDaTa(){
        btSuathongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
                    //Khởi tạo các giá trị hứng dữ liệu người dung nhập vào
                    final String tennguoidung = inputTennguoidung.getText().toString().trim();
                    final String sodienthoai = inputSdt.getText().toString().trim();
                    final String matkhau1 = inputMatkhau1.getText().toString().trim();
                    final String matkhau2 = inputMatkhau2.getText().toString().trim();
                    final String matkhau3 = inputMatkhau3.getText().toString().trim();

                    //Kiểm tra xem đã có giá trị được nhập vào chưa
                    if (kt_tennguoidung == true && kt_sodienthoai == true && kt_matkhau1 == true && kt_matkhau2 == true && kt_matkhau3 == true) {
                        //Kiểm tra xem số điện thoại đã tồn tại chưa
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, sever_link.duongdanDangnhap, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String response) {
                                if (response.length() > 0) {
                                    textSdt.setError("");
                                    //Kiểm tra mật khẩu
                                    if(matkhau1.equals(response)){ //So sánh chuỗi phân biệt hoa thường
                                        textMatkhau1.setError("");
                                        if (matkhau2.equals(matkhau3)) {
                                            textMatkhau3.setError("");
                                            //Đường dẫn
                                            String duongdansua = sever_link.duongdanSuathongtintaikhoan + String.valueOf(ThongTinTaiKhoanActivity.id_sua);
                                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                            StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdansua, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                @Nullable
                                                @Override
                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                    //Đưa dữ liệu lên bằng HasMap
                                                    HashMap<String, String> hashMap = new HashMap<String, String>();
                                                    hashMap.put("tennguoidung", tennguoidung);
                                                    hashMap.put("sodienthoai", sodienthoai);
                                                    hashMap.put("matkhau", matkhau2);
                                                    return hashMap;
                                                }
                                            };
                                            requestQueue.add(stringRequest);
                                            Intent intent = new Intent(getApplicationContext(), ThongTinTaiKhoanActivity.class);
                                            startActivity(intent);
                                            Chech_internet.ShowToast(getApplicationContext(), "Chúc mừng bạn đã thay đổi thông tin Tài Khoản thành công!");
                                        } else {
                                            textMatkhau3.setError("Mật khẩu không khớp");
                                        }
                                    }
                                    else {
                                        textMatkhau1.setError("Mật khẩu không chính xác");
                                    }
                                }
                                //Số điện thoại không chính xác
                                else {
                                    textSdt.setError("Số điện thoại không tồn tại");
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("sodienthoai", sodienthoai);
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                    else{
                        Chech_internet.ShowToast(getApplicationContext(), "Bạn vui lòng điền thêm thông tin");
                    }
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                    NoInternetActivity.manhinh = SuaThongTinTaiKhoanActivity.this;
                    startActivity(intent);
                }
            }
        });
    }
}