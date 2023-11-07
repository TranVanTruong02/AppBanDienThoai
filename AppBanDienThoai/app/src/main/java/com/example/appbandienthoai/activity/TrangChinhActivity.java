package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrangChinhActivity extends AppCompatActivity {
    //Biến giao diện
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar_main;
    DrawerLayout drawerLayout_main;
    NavigationView navigationView_main;
    ListView listView_main;
    ArrayList<LoaiSanPham> arrayLoaisp = null;
    LoaiSanPhamAdapter adapterLoaisp;

    // Khai báo các thuộc tính của loại sản phẩm
    int id = 0;
    String tenLoaiSP = "";
    String anhSP = "";

    //-----------Khởi tạo 1 mảng giỏ hàng toàn cục để giữ liệu không bị mất đi khi ta chuyển màn hình
    public static ArrayList<GioHang> arrayGioHang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh_new);

        Anhxa_Main();
        if(Chech_internet.haveNetWorkConnection(TrangChinhActivity.this)){ //getApplicationContext gọi đến màn hình này
            KhoiTaoViewPager();
            Actionbar();
            GetLoaiSP();
            //Chuyển màn hình cho thanh Menu
            ManHinh_ItemsMenu();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = TrangChinhActivity.this;
            startActivity(intent);
        }
    }

    private void Anhxa_Main(){
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        drawerLayout_main = (DrawerLayout) findViewById(R.id.drawerlayout_main);
        navigationView_main = (NavigationView) findViewById(R.id.nagrationview_main);
        listView_main = (ListView)findViewById(R.id.listview_main);
        //Ánh xạ cho toolbar
        arrayLoaisp = new ArrayList<>();
        arrayLoaisp.add(new LoaiSanPham(0, "Trang Chính", "https://cdn-icons-png.flaticon.com/512/3259/3259023.png"));
        adapterLoaisp = new LoaiSanPhamAdapter(arrayLoaisp, getApplicationContext());
        //Đổ dữ liệu vào listview
        listView_main.setAdapter(adapterLoaisp);
    }

    private void KhoiTaoViewPager(){
        //Đưa fragment vào viewpager, và tạo title
        Fragment_Adapter fragment_adapter = new Fragment_Adapter(getSupportFragmentManager());
        //ép fragment
        fragment_adapter.addFragment(new TrangChinh_Fragment(), "Trang Chính");
        fragment_adapter.addFragment(new TimKiem_Fragment(), "Tìm Kiếm");
        //đổ lên viewpager
        viewPager.setAdapter(fragment_adapter);
        //thêm icon
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_main);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_main);
    }

    //Gọi ra menu Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
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

    private void  Actionbar(){
        //Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Tạo icon home
        toolbar_main.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        //Mở ra menu
        toolbar_main.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout_main.openDrawer(GravityCompat.START); //Nhảy menu ra giữa
            }
        });
    }

    //Đọc dữ liệu cho loại sản phẩm
    private void GetLoaiSP(){
        //Đọc đường lick, đọc đường link json
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Vì đường link được bọc json lên ta sử dụng JsonArrayRequest để đọc
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sever_link.duongdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Nếu có dữ liệu, thì mới đọc dữ liệu
                if(response != null){
                    //Vì phẩn tử mảng có 2 json con, nên ta sử dụng vòng for duyệt từng phần tử
                    for (int i = 0; i < response.length(); i++){
                        try {
                            //Ta đi từ jsonarray vào jsonobject theo thứ tự i
                            JSONObject jsonObject = response.getJSONObject(i);
                            //Lấy dữ liệu ra
                            id = jsonObject.getInt("id");
                            tenLoaiSP = jsonObject.getString("tenLoaiSP");
                            anhSP = jsonObject.getString("anhSP");
                            //Truyền vào theo cấu trúc vào mảng
                            arrayLoaisp.add(new LoaiSanPham(id,tenLoaiSP,anhSP));
                            //Cập nhật lại màn hình
                            adapterLoaisp.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    arrayLoaisp.add(new LoaiSanPham(3, "Lịch Sử Đơn Hàng", "https://cdn-icons-png.flaticon.com/512/5643/5643764.png"));
                    arrayLoaisp.add(new LoaiSanPham(4, "Thông Tin", "https://cdn-icons-png.flaticon.com/512/3598/3598391.png"));
                }
            }
        }, new Response.ErrorListener() { //Nếu lỗi trả về trong đây
            @Override
            public void onErrorResponse(VolleyError error) {
                //Thông báo error
                Chech_internet.ShowToast(getApplicationContext(), error.toString());
            }
        });
        //Muốn cho việc đọc dữ liệu thực hiện
        requestQueue.add(jsonArrayRequest);
    }

    //Sự kiện chuyển màn hình cho menu
    private void ManHinh_ItemsMenu(){
        //Sử dụng sự kiện setOnItemClickListener để chọn sự kiện cho từng Items
        listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Sử dụng switch để chọn sự kiện
                switch (i){
                    case 0:
                        //Kiểm tra việc kết nối mạng
                        if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                            //Khởi tạo các màn hình cần chuyển
                            Intent intent = new Intent(getApplicationContext(), TrangChinhActivity.class);//Màn hình đang đứng, màn hình sẽ chuyển
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                            NoInternetActivity.manhinh = TrangChinhActivity.this;
                            startActivity(intent);
                        }
                        //Khi ta click lại vào menu thì nó sẽ đóng
                        drawerLayout_main.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), DienThoaiActivity.class);
                            //Truyền id loại sản phầm qua màn hình DienThoaiActivity
                            intent.putExtra("idSP", arrayLoaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                            NoInternetActivity.manhinh = TrangChinhActivity.this;
                            startActivity(intent);
                        }
                        drawerLayout_main.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), LapTopActivity.class);
                            //Truyền id loại sản phầm qua màn hình LaptopActivity
                            intent.putExtra("idSP", arrayLoaisp.get(i).getId()); //lưu ý ở đây ta lấy get(i) là vì i trùng với id bên phpadmin
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                            NoInternetActivity.manhinh = TrangChinhActivity.this;
                            startActivity(intent);
                        }
                        drawerLayout_main.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), LichSuDonHangActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                            NoInternetActivity.manhinh = TrangChinhActivity.this;
                            startActivity(intent);
                        }
                        drawerLayout_main.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), ThongTinTaiKhoanActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                            NoInternetActivity.manhinh = TrangChinhActivity.this;
                            startActivity(intent);
                        }
                        drawerLayout_main.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }
}