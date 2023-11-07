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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinMuaHangActivity extends AppCompatActivity {
    //Biến gia diện
    Toolbar toolbarMuahang;
    TextInputEditText inputTenkhachhang, inputSdt, inputDiachi, inputThanhtoan;
    TextInputLayout textTenkhachhang, textSdt, textDiachi, textThanhtoan;
    AppCompatButton btXacnhan, btHuy;

    //Hàm kiểm tra giá trị nhập vào
    Boolean kt_tenkhachhang = false;
    Boolean kt_sodienthoai = false;
    Boolean kt_diachi = false;
    Boolean kt_phuongthucthanhtoan = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_mua_hang);
        AnhXa_ChiTietKhacHang();
        if(Chech_internet.haveNetWorkConnection(getApplicationContext())){
            ClickButon();
            KiemTraNhapLieu();
            ToolBarMuaHang();
            ClickButton();
            //Sự kiện của button
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = ThongTinMuaHangActivity.this;
            startActivity(intent);
        }
    }
    private void AnhXa_ChiTietKhacHang(){
        toolbarMuahang = (Toolbar) findViewById(R.id.muahang_toolbar);
        textTenkhachhang = (TextInputLayout) findViewById(R.id.muahang_text_tenkhachhang);
        textSdt = (TextInputLayout) findViewById(R.id.muahang_text_sdt);
        textDiachi = (TextInputLayout) findViewById(R.id.muahang_text_diachi);
        textThanhtoan = (TextInputLayout) findViewById(R.id.muahang_text_thanhtoan);
        inputTenkhachhang = (TextInputEditText) findViewById(R.id.muahang_input_tenkhachhang);
        inputSdt = (TextInputEditText) findViewById(R.id.muahang_input_sdt);
        inputDiachi = (TextInputEditText) findViewById(R.id.muahang_input_diachi);
        inputThanhtoan = (TextInputEditText) findViewById(R.id.muahang_input_thanhtoan);
        btXacnhan = (AppCompatButton) findViewById(R.id.muahang_button_xacnhan);
        btHuy = (AppCompatButton) findViewById(R.id.muahang_button_huy);
    }
    private void ToolBarMuaHang(){
        setSupportActionBar(toolbarMuahang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMuahang.setNavigationIcon(R.drawable.ic_left);
        toolbarMuahang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void ClickButton(){
        btXacnhan.setOnTouchListener(new View.OnTouchListener() {
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
        btHuy.setOnTouchListener(new View.OnTouchListener() {
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
    private void KiemTraNhapLieu(){
        //Tên khách hàng
        textTenkhachhang.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textTenkhachhang.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 20){
                    textTenkhachhang.setError("Bạn chỉ có thể nhập tối đa 20 kí tự");
                }
                else {
                    textTenkhachhang.setError("");
                    kt_tenkhachhang = true;
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
                if (charSequence.length() < 10){
                    textSdt.setError("Bạn phải nhập ít nhất 10 kí tự");
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
        //Địa chỉ
        textDiachi.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textDiachi.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 50){
                    textDiachi.setError("Bạn chỉ có thể nhập tối đa 50 kí tự");
                }
                else {
                    textDiachi.setError("");
                    kt_diachi = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Phương Thức Thanh Toán
        textThanhtoan.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textThanhtoan.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 20){
                    textThanhtoan.setError("Bạn chỉ có thể nhập tối đa 20 kí tự");
                }
                else {
                    textThanhtoan.setError("");
                    kt_phuongthucthanhtoan = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void ClickButon(){
        //Button Huỷ
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy ra giá trị người dùng nhập vào
                final String tenkhachhang = inputTenkhachhang.getText().toString().trim();
                final String sodienthoai = inputSdt.getText().toString().trim();
                final String diachi = inputDiachi.getText().toString().trim();
                final String phuongthucthanhtoan = inputThanhtoan.getText().toString().trim();
                if (tenkhachhang.length() > 0 || sodienthoai.length() > 0 || diachi.length() >0 || phuongthucthanhtoan.length() > 0){
                    inputTenkhachhang.setText("");
                    inputSdt.setText("");
                    inputDiachi.setText("");
                    inputThanhtoan.setText("");
                    Chech_internet.ShowToast(getApplicationContext(), "Bạn đã huỷ nhập thông tin");
                }
                else {
                    Chech_internet.ShowToast(getApplicationContext(), "Bạn chưa nhập thông tin nào?");
                }
            }
        });

        //Button Xác Nhận
        btXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
                    //Lấy ra giá trị người dùng nhập vào
                    final String tenkhachhang = inputTenkhachhang.getText().toString().trim();
                    final String sodienthoai = inputSdt.getText().toString().trim();
                    final String diachi = inputDiachi.getText().toString().trim();
                    final String phuongthucthanhtoan = inputThanhtoan.getText().toString().trim();

                    if (kt_tenkhachhang == true && kt_sodienthoai == true && kt_diachi == true && kt_phuongthucthanhtoan == true){
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        //Truyền dữ liệu vào POST
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, sever_link.duongdanThongTinMuaHang, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String madonhangg) {
                                //Khi có dữ liệu
                                if (madonhangg.length() > 0){
                                    RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, sever_link.duongdanChiTietDonHang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("baoo", response);
                                            //Nếu như có dữ liệu
                                            if ((response.equals("1"))){
                                                TrangChinhActivity.arrayGioHang.clear();
                                                Intent intent = new Intent(getApplicationContext(), MuaHangThanhCongActivity.class);
                                                startActivity(intent);
                                            }
                                            else {
                                                Chech_internet.ShowToast(getApplicationContext(), "Dữ liệu giỏ hàng của bạn đã bị lỗi");
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }){
                                        @Nullable
                                        @Override //Đầu tiên ta chuyển dữ liệu thành 1 chuỗi jsonarray để hasmap đẩy lên server
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
                                            for (int i = 0; i < TrangChinhActivity.arrayGioHang.size(); i++){
                                                JSONObject jsonObject = new JSONObject();
                                                try {
                                                    //Put các dữ liệu vào jsonobject
                                                    jsonObject.put("madonhang", madonhangg);
                                                    jsonObject.put("masanpham", TrangChinhActivity.arrayGioHang.get(i).getIdsp());
                                                    jsonObject.put("anhsanpham", TrangChinhActivity.arrayGioHang.get(i).getAnhsp());
                                                    jsonObject.put("tensanpham", TrangChinhActivity.arrayGioHang.get(i).getTensp());
                                                    jsonObject.put("giasanpham", TrangChinhActivity.arrayGioHang.get(i).getGiasp());
                                                    jsonObject.put("soluongsanpham", TrangChinhActivity.arrayGioHang.get(i).getSoluongsp());
                                                    jsonObject.put("tongtien", TrangChinhActivity.arrayGioHang.get(i).getTongtien());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                //Truyền lại jsonobject lại vào jsonarray tạo thành chuỗi json
                                                jsonArray.put(jsonObject);
                                            }
                                            HashMap<String, String> hashMap = new HashMap<String, String>();
                                            //POST dữ liệu lên server thông qua từ khoá json (từ app-php). và ép kiểu json ở dạng string
                                            hashMap.put("json", jsonArray.toString());
                                            return hashMap;
                                        }
                                    };
                                    requestQueue1.add(stringRequest1);
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
                                //Đẩy dữ liệu lên server kết nối vào POST trong php
                                hashMap.put("tenkhachhang", tenkhachhang);
                                hashMap.put("sodienthoai", sodienthoai);
                                hashMap.put("diachi", diachi);
                                hashMap.put("phuongthucthanhtoan", phuongthucthanhtoan);
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                    else {
                        Chech_internet.ShowToast(getApplicationContext(), "Bạn vui lòng điền đầy đủ thông tin");
                    }
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                    NoInternetActivity.manhinh = ThongTinMuaHangActivity.this;
                    startActivity(intent);
                }
            }
        });
    }
}