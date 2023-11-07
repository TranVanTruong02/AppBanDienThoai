package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.adapter.DienThoai_Adapter;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {
    //Khởi tạo biến giao diện
    androidx.appcompat.widget.Toolbar toolbar_DienThoai;
    ListView listView_DienThoai;
    DienThoai_Adapter dienThoai_adapter;
    ArrayList<SanPham> arrayList_DienThoai;
    //Bến toàn cục nhận id được gửi từ MainActivity
    int id_DienThoai = 0;
    int page = 1;
    //Progressbar
    View progressbar;
    //Sử dụng ifLoading để ng dùng chỉ đc kéo lần tiếp theo khi loaded xong
    boolean isLoading = false;
    boolean limitdata = false;// nếu GetData hết dữ liệu
    //Khai báo class-Ông chủ
    mHander mhander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        AnhXa_DienThoai();
        //Kiểm tra kết nối Internet
        if (Chech_internet.haveNetWorkConnection(DienThoaiActivity.this)){
            //Nhận id được gửi từ Main
            GetIDLoaiSP();
            //Tạo sự kiện hiẻn thị cho toolbar
            ActionTooolbar();
            //Đọc
            GetData(page);
            //Loat more data
            LoatMore_DienThoai();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = DienThoaiActivity.this;
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
    private void AnhXa_DienThoai(){
        toolbar_DienThoai = (Toolbar) findViewById(R.id.toolbar_dienthoai);
        listView_DienThoai = (ListView) findViewById(R.id.lisview_dienthoai);
        arrayList_DienThoai = new ArrayList<>();
        dienThoai_adapter = new DienThoai_Adapter(getApplicationContext(), arrayList_DienThoai);
        listView_DienThoai.setAdapter(dienThoai_adapter);
        //Progressbar-Gọi phương thức gán layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        progressbar = inflater.inflate(R.layout.progressbar,null);
        mhander = new mHander();
    }
    private void GetIDLoaiSP(){
        //Nhận lại id được gửi từ bên MainActivity. Ta sử dụng getIntent
        //Nhận id là idSP, tuy nhiên nếu như không nhận đc id này thì ta sẽ lấy là -1
        Intent intent = getIntent();
        id_DienThoai = intent.getIntExtra("idSP", -1);
        //id_DienThoai = getIntent().getIntExtra("idSP", -1);
        //Log.d("Giá trị loại sản phẩm", id_DienThoai + "");
    }
    private void ActionTooolbar(){
        setSupportActionBar(toolbar_DienThoai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_DienThoai.setNavigationIcon(R.drawable.ic_left);
        toolbar_DienThoai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void GetData(int PASE){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Lấy đường dẫn
        String duongdanDienThoai = sever_link.duongdanSanphamDienThoai + String.valueOf(PASE);
        // Đọc hết dữ liệu
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdanDienThoai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Khởi tạo các biến để hứng giá trị
                int id = 0, idloaisp_DT = 0, giadt = 0;
                String tendt = "", anhdt = "", motadt = "";
                //jsson nếu rỗng nó luôn trả ra 1 cặp ngoặc kép lên khi rỗng nó có 2 giá trị lên ta để điều kiện if response.length() != 2
                if (response != null && response.length() != 2){
                    listView_DienThoai.removeFooterView(progressbar);//Thanh progressbat sẽ tắt khi có dữ liệu trả về rồi
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tendt = jsonObject.getString("tenSP");
                            giadt = jsonObject.getInt("giaSP");
                            anhdt = jsonObject.getString("anhSP");
                            motadt = jsonObject.getString("moTaSP");
                            idloaisp_DT = jsonObject.getInt("idSP");
                            arrayList_DienThoai.add(new SanPham(id, tendt, giadt, anhdt, motadt, idloaisp_DT));
                            dienThoai_adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }//else response.length()<0 hết dữ liệu để loat
                else {
                    limitdata = true;
                    listView_DienThoai.removeFooterView(progressbar);
                    Chech_internet.ShowToast(DienThoaiActivity.this, "Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override //Đầy dữ liệu lên server. đầy key và dữ liệu lên
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> paras = new HashMap<String, String>();
                //Đẩy dử liệu lên. key là idSP nhận từ Main với giá trị là String.valueOf(id_DienThoai)
                paras.put("idSP", String.valueOf(id_DienThoai));
                return paras;
            }
        };
        //thực thi
        requestQueue.add(stringRequest);
    }

    //LoatMore_DienThoai
    private void LoatMore_DienThoai(){
        //Sự kiện click của các items listview
        listView_DienThoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Chuyển màn hình qua ChiTietSanPhamActivity
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham", arrayList_DienThoai.get(i));//truyền dạng abject sang cho màn hình khác ta cần thêm implements Serializable ở SanPham(Chứa các thuộc tính của sản phẩm)
                startActivity(intent);
            }
        });
        //Sự kiện kéo của listview
        listView_DienThoai.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override //Bắt sự viện
            public void onScroll(AbsListView absListView, int dongdau, int cacitemsthay, int tongitem) {
                //Bắt gia trị cuối cùng
                //Thêm sự kiện tongitem != 0 để khi dum lên lần đầu tiện nó k nhảu rô vòng if liền
                //isLoading chỉ cho loat tiếp dữ liệu khi đã loat xong
                //limitdata không cho nó kép tiếp khi đã hết dữ liệu
                if(dongdau + cacitemsthay == tongitem && tongitem != 0 && isLoading == false && limitdata == false){
                    isLoading = true;
                    //Cho nó hoạt động ta gọi đến TheadData
                    TheadData theadData = new TheadData();
                    theadData.start();
                }
            }
        });
    }
    //Tạo ông chủ cho việc quản lý
    public class mHander extends Handler{
        @Override //Quản lí các items đc gửi lên
        public void handleMessage(@NonNull Message msg) {
            //what các giá trị gửi lên
            switch (msg.what){
                //Trường hợp 1: bằng 0 ép thanh prosetbar
                case 0:
                    listView_DienThoai.addFooterView(progressbar);
                    break;
                case 1:
                    //Tăng page
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }

    }
    //Khởi tạo các nhân viên
    public class TheadData extends Thread{
        @Override
        public void run() {
            //Để thực hiện các luồng_ta gửi tin nhắn cho ông chủ
            mhander.sendEmptyMessage(0);// THì ta get vào foterview
            try {
                //Sau 3 giây nó sẽ gọi lại
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Say 3s nó gửi tiếp 1 tin nhắn
            //Phương thức obtainMessage liên kết giữa các Thread và Hander. Khi muốn liên kết tiếp tục ra gọi qua obtainMessage
            Message message = mhander.obtainMessage(1);
            //để thực hiện
            mhander.sendMessage(message);
            super.run();
        }
    }
}