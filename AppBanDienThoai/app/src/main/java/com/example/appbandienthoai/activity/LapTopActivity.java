package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.adapter.LapTop_Adapter;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LapTopActivity extends AppCompatActivity {
    //Khai báo biến giao diện
    Toolbar toolbarLapTop;
    ListView listViewLapTop;
    ArrayList<SanPham> arrayLapTop;
    LapTop_Adapter adapter_laptop;

    //Khởi tạo id laptop để nhận id từ main truyền qua
    int id_LapTop = 0;

    //Loat 6 sản phẩm trước
    int page_laptop = 1;
    boolean isLoading = false;
    boolean limitdata = false;
    //Khởi tạo mHander để hàm khác gọi
    mHander mhander;
    //khia báo progressbar cái quay trong khi loat dữ liệu
    View progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);
        AnhXa_LapTop();
        if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
            ToolBar_LapTop();
            //Lấy id được truyền từ main qua
            GetIDLapTop();
            //Lấy dữ liệu truyền rô adapter
            GetData_Laptop(page_laptop);
            //Loat more laptop
            LoatMore_Laptop();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = LapTopActivity.this;
            startActivity(intent);
        }
    }
    //Gọi ra menu Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    //Bắt sự kiện khi click vào menu Giỏ hàng
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_giohang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    //Ánh xạ giá trị cho laptop
    private void AnhXa_LapTop(){
        toolbarLapTop = (Toolbar) findViewById(R.id.toolbar_laptop);
        listViewLapTop = (ListView) findViewById(R.id.listview_laptop);
        //Lấy dữ liệu từ array sản phẩm đổ vào adapter
        arrayLapTop = new ArrayList<SanPham>();
        adapter_laptop = new LapTop_Adapter(getApplicationContext(), arrayLapTop);
        listViewLapTop.setAdapter(adapter_laptop);
        //Khai báo progressbar
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        progressbar = inflater.inflate(R.layout.progressbar,null);
        //Khai báo mHander
        mhander = new mHander();
    }
    private void ToolBar_LapTop(){
        setSupportActionBar(toolbarLapTop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLapTop.setNavigationIcon(R.drawable.ic_left);
        toolbarLapTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //Nhận id được truền từ bên main qua
    private void GetIDLapTop(){
        Intent intent = getIntent();
        id_LapTop = intent.getIntExtra("idSP", -1);
    }
    private void GetData_Laptop(int PAGE){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Lấy đường dẫn
        String duongdanLapTop = sever_link.duongdanSanphamDienThoai + String.valueOf(PAGE);
        //
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdanLapTop, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Khởi tạo các biến hứng dữ liệu
                int id = 0, idLoaisp = 0, giaLaptop = 0;
                String tenLaptop = "", anhLapTop = "", motaLaptop = "";
                if (response != null && response.length() != 2){
                    //khi đã loat cong ta xoá progressbar
                    listViewLapTop.removeFooterView(progressbar);
                    try {
                        //Khi đọc dữ liệu ta sẽ đi từ mảng json vào từng object json
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenLaptop = jsonObject.getString("tenSP");
                            giaLaptop = jsonObject.getInt("giaSP");
                            anhLapTop = jsonObject.getString("anhSP");
                            motaLaptop = jsonObject.getString("moTaSP");
                            idLoaisp = jsonObject.getInt("idSP");
                            arrayLapTop.add(new SanPham(id, tenLaptop, giaLaptop, anhLapTop, motaLaptop, idLoaisp));
                            adapter_laptop.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    limitdata = true;
                    listViewLapTop.removeFooterView(progressbar);
                    Chech_internet.ShowToast(LapTopActivity.this, "Đã hết dữ liệu");
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
                hashMap.put("idSP",String.valueOf(id_LapTop));
                return hashMap;
            }
        };
        //Thực hiện request
        requestQueue.add(stringRequest);
    }
    //Loat more dữ liệu
    private void LoatMore_Laptop(){
        //Sự kiện chuyển màn hình sang chi tiết sản phẩm
        listViewLapTop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham", arrayLapTop.get(i));
                startActivity(intent);
            }
        });
        //Sự kiện kéo của listview
        listViewLapTop.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int dongdau, int itemsthay, int tongitems) {
                if(dongdau + itemsthay == tongitems && tongitems != 0 && isLoading == false && limitdata == false){
                    isLoading = true;
                    ThreadData_Laptop thread = new ThreadData_Laptop();
                    thread.start();
                }
            }
        });
    }
    private class mHander extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listViewLapTop.addFooterView(progressbar);//Hiển thị cái loat
                    break;
                case 1:
                    GetData_Laptop(++page_laptop);
                    isLoading = false;//Khi loat thêm dữ liệu ta cho mở Theard và hiển thị progressbar
                    break;
            }
            super.handleMessage(msg);
        }
    }
    //Lấy what cho mHander chạy
    public class ThreadData_Laptop extends Thread{
        @Override
        public void run() {
            mhander.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mhander.obtainMessage(1);
            mhander.sendMessage(message);
            super.run();
        }
    }

}