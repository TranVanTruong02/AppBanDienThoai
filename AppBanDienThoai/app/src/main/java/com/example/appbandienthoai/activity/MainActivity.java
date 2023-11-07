package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.adapter.Fragment_Adapter;
import com.example.appbandienthoai.adapter.LoaiSanPhamAdapter;
import com.example.appbandienthoai.adapter.SanPhamAdapter;
import com.example.appbandienthoai.fragment.TimKiem_Fragment;
import com.example.appbandienthoai.fragment.TrangChinh_Fragment;
import com.example.appbandienthoai.molder.GioHang;
import com.example.appbandienthoai.molder.LoaiSanPham;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextInputEditText inputSdt, inputMatkhau;
    TextInputLayout textSdt, textMatkhau;
    AppCompatButton btLogin;
    TextView tvDangki;

    //Tạo biến toàn cục để truyền dữ liệu
    public static String sodienthoai_login = "";

    //Hàm kiểm tra
    Boolean kt_sodienthoai = false;
    Boolean kt_matkhau = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa_DangNhap();
        KiemTraThaoTacNhap();
        if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
            ClickButton();
            XacThucDangNhap();
            ChuyenManHinhDangKi();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = MainActivity.this;
            startActivity(intent);
        }
    }
    private void AnhXa_DangNhap(){
        textSdt = (TextInputLayout) findViewById(R.id.dangnhap_text_sdt);
        textMatkhau = (TextInputLayout) findViewById(R.id.dangnhap_text_makhau);
        inputSdt = (TextInputEditText) findViewById(R.id.dangnhap_input_sdt);
        inputMatkhau = (TextInputEditText) findViewById(R.id.dangnhap_input_makhau);
        btLogin = (AppCompatButton) findViewById(R.id.dangnhap_button_login);
        tvDangki = (TextView) findViewById(R.id.dangnhap_tv_dangki);
    }

    private void ClickButton(){
       btLogin.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               switch (motionEvent.getAction()) {
                   case MotionEvent.ACTION_DOWN: {
                       int color = Color.parseColor("#fcd0d0");
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

    //Sự Kiện nhập dữ liệu
    private void KiemTraThaoTacNhap(){
        //Kiểm tra tài khoản
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
        //Kiểm tra mật khẩu
        textMatkhau.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 3){
                    textMatkhau.setError("Bạn phải nhập ít nhất 3 kí tự");
                }
                else if (charSequence.length() > 10){
                    textMatkhau.setError("Bạn chỉ có thể nhập tối đa 10 kí tự");
                }
                else {
                    textMatkhau.setError("");
                    kt_matkhau = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    //Sự kiện click login để xác thực đăng nhập
    private void XacThucDangNhap(){
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
                    //Lấy thông tin người dùng nhập trên inputEdit
                    final String sodienthoai = inputSdt.getText().toString().trim();
                    final String matkhau = inputMatkhau.getText().toString().trim();
                    if(kt_sodienthoai == true && kt_matkhau == true){
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, sever_link.duongdanDangnhap, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String response) {
                                if(response.length() > 0) {
                                    textSdt.setError("");
                                    sodienthoai_login = sodienthoai;
                                    if(matkhau.equals(response)){ //So sánh chuỗi phân biệt hoa thường
                                        textMatkhau.setError("");
                                        Intent intent = new Intent(getApplicationContext(), TrangChinhActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        textMatkhau.setError("Mật khẩu không chính xác");
                                    }
                                }
                                //Số điện thoại không chính xác
                                else {
                                    textSdt.setError("Số điện thoại không chính xác");
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
                        Chech_internet.ShowToast(getApplicationContext(), "Bạn vui lòng điền đầy đủ thông tin");
                    }
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                    NoInternetActivity.manhinh = MainActivity.this;
                    startActivity(intent);
                }
            }
        });
    }
    //Sự kiện click vào textview chuyển màn hình sang trang đăng kí
    private void ChuyenManHinhDangKi(){
        tvDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKiActivity.class);
                startActivity(intent);
            }
        });
    }

    //chuoi.substring(0, 1)

}