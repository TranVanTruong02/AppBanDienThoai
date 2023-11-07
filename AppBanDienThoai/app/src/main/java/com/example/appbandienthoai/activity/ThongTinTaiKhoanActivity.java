package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {
    //Biến giao diện
    Toolbar toolbarThongtin;
    TextView tvTennguoidung, tvSodienthoai, tvMatkhau;

    //Hàm chứa thông tin toàn cục Thông Tin Tài Khoản
    public static int id_sua = 0;
    public static String tennguoidung_sua = "", sodienthoai_sua = "", matkhau_sua = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        AnhXa_ThongTinTaiKhoan();
        if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
            ToolBar_ThongTinTaiKhoan();
            LayDuLieu();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = ThongTinTaiKhoanActivity.this;
            startActivity(intent);
        }
    }
    //Gọi ra menu giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_thongtin,menu);
        return true;
    }
    //Bắt sự kiện cho thanh menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_thongtin:
                Intent intentt = new Intent(getApplicationContext(), SuaThongTinTaiKhoanActivity.class);
                startActivity(intentt);
                break;
            case R.id.menu_dangxuat:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AnhXa_ThongTinTaiKhoan(){
        toolbarThongtin = (Toolbar) findViewById(R.id.thongtintaikhoan_toolbar);
        tvTennguoidung = (TextView) findViewById(R.id.thongtintaikhoan_tennguoidung);
        tvSodienthoai = (TextView) findViewById(R.id.thongtintaikhoan_sodienthoai);
        tvMatkhau = (TextView) findViewById(R.id.thongtintaikhoan_matkhau);
    }
    private void ToolBar_ThongTinTaiKhoan(){
        setSupportActionBar(toolbarThongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThongtin.setNavigationIcon(R.drawable.ic_left);
        toolbarThongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void LayDuLieu(){
        String sdt = MainActivity.sodienthoai_login;
        String duongdanThongtintaikhoan = sever_link.duongdanThongtintaikhoan + sdt;
        if (sdt.length() > 0){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(duongdanThongtintaikhoan, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if (response != null){
                        //Khởi tạo giá trị hứng dữ liệu từ json
                        for (int i = 0; i < response.length(); i++){
                            try {
                                //Lấy dữ liệu từ json
                                JSONObject jsonObject = response.getJSONObject(i);
                                id_sua = jsonObject.getInt("id");
                                tennguoidung_sua = jsonObject.getString("tennguoidung");
                                sodienthoai_sua = jsonObject.getString("sodienthoai");
                                matkhau_sua = jsonObject.getString("matkhau");
                                //Đẩy dữ liệu lên textView
                                tvTennguoidung.setText(tennguoidung_sua);
                                tvSodienthoai.setText(sodienthoai_sua);
                                tvMatkhau.setText(matkhau_sua);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonArrayRequest);
        }
    }
}