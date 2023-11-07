package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.adapter.DienThoai_Adapter;
import com.example.appbandienthoai.adapter.LichSuDonHang_Adapter;
import com.example.appbandienthoai.molder.LichSuDonHang;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
- Một điều cần lưu ý: Ở đây mảng chr có sau khi lấy đc dữ liệu từ json, lên thao tác ẩn hiện thông báo cần thực hiện sau khi mảng lấy đc dữ liệu
- Trong giỏ hàng, mảng là 1 biến toàn cục. tuy nhiên k cần thông qua json, lên ta có thể thao tác bất cứ lúc nào
 */
public class LichSuDonHangActivity extends AppCompatActivity {
    //Khai báo biến giao diện
    Toolbar toolbarLichsu;
    LinearLayout linearLayoutSanphamnull;
    ListView listViewSanpham;
    Button buttonSanpham;

    //Khai báo mảng và adapter
    ArrayList<LichSuDonHang> arrayLicSuDonHang = null;
    LichSuDonHang_Adapter adapterLichSuDonHang;

    //Kiểm tra điều kiện loadmore
    int page = 1;
    Boolean isloat = false;
    Boolean limitdata = false;
    Boolean ktloatmore = false;
    mHander mhander;
    View progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);
        AnhXa_LichSu();
        if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
            Toolbar_LichSu();
            ClickButtonn();
            //Lấy dữ liệu từ xam về
            LoatDuLieu(page);
            LoadMore();
            //Sự kiện ClickLonglistview
            ClickLongListView();
            //Sự kiện nút button
            ClickButton();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = LichSuDonHangActivity.this;
            startActivity(intent);
        }
    }
    private void AnhXa_LichSu(){
        toolbarLichsu = (Toolbar) findViewById(R.id.lichsu_toolbar);
        linearLayoutSanphamnull = (LinearLayout) findViewById(R.id.lichsu_sanpham_null);
        listViewSanpham = (ListView) findViewById(R.id.lichsu_sanpham);
        buttonSanpham = (Button) findViewById(R.id.lichsu_button);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        progressbar = layoutInflater.inflate(R.layout.progressbar, null);
        mhander = new mHander();
        arrayLicSuDonHang = new ArrayList<>();
        adapterLichSuDonHang = new LichSuDonHang_Adapter(getApplicationContext(), arrayLicSuDonHang);
        listViewSanpham.setAdapter(adapterLichSuDonHang);
    }
    private void Toolbar_LichSu(){
        setSupportActionBar(toolbarLichsu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLichsu.setNavigationIcon(R.drawable.ic_left);
        toolbarLichsu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void ClickButtonn(){
        buttonSanpham.setOnTouchListener(new View.OnTouchListener() {
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
    private void LoatDuLieu(int PAGE){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Đường dẫn
        String duongdanlichsudonhang = sever_link.duongdanLichSuDonHang + String.valueOf(PAGE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdanlichsudonhang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Khởi tạo các biến để hứng giá trị
                int id_donhang = 0, id_chitiet = 0, giasanpham = 0, soluongsanpham = 0, tongtien = 0;
                String tensanpham = "", anhsanpham = "", tenkhachhang = "", sodienthoai = "", diachi = "", ptthanhtoan = "";
                //jsson nếu rỗng nó luôn trả ra 1 cặp ngoặc kép lên khi rỗng nó có 2 giá trị lên ta để điều kiện if response.length() != 2
                if (response != null && response.length() != 2){
                    listViewSanpham.removeFooterView(progressbar);//Thanh progressbat sẽ tắt khi có dữ liệu trả về rồi
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id_donhang = jsonObject.getInt("id");
                            id_chitiet = jsonObject.getInt("idd");
                            anhsanpham = jsonObject.getString("anhsanpham");
                            tensanpham = jsonObject.getString("tensanpham");
                            giasanpham = jsonObject.getInt("giasanpham");
                            soluongsanpham = jsonObject.getInt("soluongsanpham");
                            tenkhachhang = jsonObject.getString("tenkhachhang");
                            sodienthoai = jsonObject.getString("sodienthoai");
                            diachi = jsonObject.getString("diachi");
                            ptthanhtoan = jsonObject.getString("phuongthucthanhtoan");
                            tongtien = jsonObject.getInt("tongtien");
                            arrayLicSuDonHang.add(new LichSuDonHang(id_donhang, id_chitiet, anhsanpham, tensanpham, giasanpham, soluongsanpham, tenkhachhang, sodienthoai, diachi, ptthanhtoan, tongtien));
                            if (arrayLicSuDonHang.size() > 0){
                                linearLayoutSanphamnull.setVisibility(View.INVISIBLE);
                                listViewSanpham.setVisibility(View.VISIBLE);
                            }
                            else {
                                linearLayoutSanphamnull.setVisibility(View.INVISIBLE);
                                listViewSanpham.setVisibility(View.VISIBLE);
                            }
                            adapterLichSuDonHang.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    limitdata = true;
                    listViewSanpham.removeFooterView(progressbar);
                    Chech_internet.ShowToast(LichSuDonHangActivity.this, "Đã hết dữ liệu!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //thực thi
        requestQueue.add(stringRequest);
    }
    private void LoadMore(){
        //Sự kiệu kéo của listview
        listViewSanpham.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int sanpham1, int cacsanpham, int tongsanpham) {
                //Bắt sự kiệu khi kéo đến cuối cùng
                if (tongsanpham == 3 && sanpham1 + cacsanpham == tongsanpham && isloat == false && limitdata == false){
                    isloat = true; // Không cho loat tiếp
                    ktloatmore = true; //Kiểm tra lần loat dữ liệu đầu tiên
                    Thread thread = new Thraed(); //Thread gửi mess cho mHander để gọi progressbar và loat dữ liệu
                    thread.start();
                }
            }
        });
    }
    //Khởi tạo class mHander
    public class mHander extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listViewSanpham.addFooterView(progressbar);
                    break;
                case 1:
                    LoatDuLieu(++page);//Sau khi progressbar thì ta loat dữ liệu
                    isloat = false; //Đang loat, ta gọi lại dữ liệu
                    break;

            }
            super.handleMessage(msg);
        }
    }
    public class Thraed extends Thread{
        @Override
        public void run() {
            mhander.sendEmptyMessage(0);
            try {
                Thread.sleep(3000); //Cho nó ngủ 3 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Sau 3s sẽ tiếp tục gọi mhander. Ta sửu dụng liên kết tiếp tục
            Message message = mhander.obtainMessage(1);
            mhander.sendMessage(message);
            super.run();
        }
    }
    private void ClickLongListView(){
        listViewSanpham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int vitri, long l) {
                AlertDialog.Builder aler = new AlertDialog.Builder(LichSuDonHangActivity.this);
                aler.setTitle("Xoá Sản Phẩm");
                aler.setMessage("Bạn có muốn xoá sản phẩm này không?");
                aler.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                            int idchitiet = arrayLicSuDonHang.get(vitri).getIdchitietdonhang();
                            int iddonhang = arrayLicSuDonHang.get(vitri).getIddonhang();
                            Log.d("tim", idchitiet + "");
                            Log.d("tim1", iddonhang + "");
                            //Kiểm tra số lượng madonhang
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, sever_link.duongdanDemMaDonHang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String madonhang) {
                                    //Lúc này ta lấy được mã đơn hàng
                                    if (Integer.parseInt(madonhang) > 1) {
                                        Log.d("tim2", sever_link.duongdanXoaChiTietDonHang);
                                        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, sever_link.duongdanXoaChiTietDonHang, new Response.Listener<String>() {
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
                                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                                Log.d("tim2", String.valueOf(idchitiet));
                                                hashMap.put("chitietdonhang", String.valueOf(idchitiet));
                                                return hashMap;
                                            }
                                        };
                                        requestQueue1.add(stringRequest1);

                                        //Cập nhật lại màn hình
                                        arrayLicSuDonHang.remove(vitri); //xoá dữ liệu có trong mảng
                                        adapterLichSuDonHang.notifyDataSetChanged();
                                        Chech_internet.ShowToast(getApplicationContext(), "Chúc mừng bạn đã xoá Thành Công!");
                                    } else {
                                        //Xoá cơ sở dữ liệu chi tiết đơn hàng
                                        RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
                                        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, sever_link.duongdanXoaChiTietDonHang, new Response.Listener<String>() {
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
                                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                                hashMap.put("chitietdonhang", String.valueOf(idchitiet));
                                                return hashMap;
                                            }
                                        };
                                        Log.d("tim12", sever_link.duongdanXoaDonHang);
                                        requestQueue2.add(stringRequest2);

                                        //Xoá cơ sở dữ liệu đơn hàng
                                        RequestQueue requestQueue3 = Volley.newRequestQueue(getApplicationContext());
                                        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, sever_link.duongdanXoaDonHang, new Response.Listener<String>() {
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
                                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                                hashMap.put("donhang", String.valueOf(iddonhang));
                                                return hashMap;
                                            }
                                        };
                                        requestQueue3.add(stringRequest3);

                                        Log.d("tim5", String.valueOf(arrayLicSuDonHang));
                                        //Cập nhật lại màn hình
                                        arrayLicSuDonHang.remove(vitri);
                                        if (arrayLicSuDonHang.size() > 0) {
                                            linearLayoutSanphamnull.setVisibility(View.INVISIBLE);
                                            listViewSanpham.setVisibility(View.VISIBLE);
                                        } else {
                                            linearLayoutSanphamnull.setVisibility(View.VISIBLE);
                                            listViewSanpham.setVisibility(View.INVISIBLE);
                                        }
                                        adapterLichSuDonHang.notifyDataSetChanged();
                                        Chech_internet.ShowToast(getApplicationContext(), "Chúc mừng bạn đã xoá Thành Công!");
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
                                    hashMap.put("demchitietdonhang", String.valueOf(iddonhang));
                                    return hashMap;
                                }
                            };
                            requestQueue.add(stringRequest);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                            NoInternetActivity.manhinh = LichSuDonHangActivity.this;
                            startActivity(intent);
                        }
                    }
                });
                aler.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                aler.create().show();
                return true;
            }
        });
    }

    private void ClickButton(){
        buttonSanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrangChinhActivity.class);
                startActivity(intent);
            }
        });
    }
}