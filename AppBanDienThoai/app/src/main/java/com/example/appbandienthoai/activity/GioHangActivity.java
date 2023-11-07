package com.example.appbandienthoai.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.adapter.GioHang_Adapter;
import com.example.appbandienthoai.ultil.Chech_internet;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    //Khai báo biến giao diện
    LinearLayout tvThongbaoGiohang;
    static TextView tvTongTienGiohang;
    Button btMuangayGiohang, btThemmoiGiohang;
    ListView listGiohang;
    androidx.appcompat.widget.Toolbar toolbarGiohang;
    GioHang_Adapter adapterGiohang;
    //Ở đây ta đã sử dụng mảng toàn cục được khai báo bên MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa_GioHang();
        if (Chech_internet.haveNetWorkConnection(getApplicationContext())){
            ToolbarGioHang();
            ClickButton();
            //Kiểm tra dữ liệu trong giỏ hàng trống hay k
            CheckGioHang();
            //Bắt sự kiện đổ dữ liệu lên listview
            DataListView();
            ClickItemsLongListView();
            //Sự kiện nút button mua ngay và tiếp tục mua
            ClickButtonn();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            NoInternetActivity.manhinh = GioHangActivity.this;
            startActivity(intent);
        }
    }
    private void AnhXa_GioHang(){
        toolbarGiohang = (Toolbar) findViewById(R.id.toolbar_giohang);
        tvThongbaoGiohang = (LinearLayout) findViewById(R.id.giohang_sanpham_null);
        tvTongTienGiohang = (TextView) findViewById(R.id.tv_tong_gia);
        listGiohang = (ListView) findViewById(R.id.listview_giohang);
        btMuangayGiohang = (Button) findViewById(R.id.bt_muangay_giohang);
        btThemmoiGiohang = (Button) findViewById(R.id.bt_themmoi_giohang);
        // 1 lỗi rất oái ăm. Khi mua sản phẩm xong, ListGioHang sẽ bị xoá trắng
        //Khi này nếu ta không để điều kiện rô đây thì adapterGiohang sẽ tiếp tục chạy với listGioHang trống
        //Điều này sẽ gây ra lỗi arraygiohang.size() rỗng, không thể truy xuất dữ liệu
        //Lúc này ta cần áp 1 điều kiện vào. Chỉ khi giỏ hàng khác null ta mới set adapter
        if(TrangChinhActivity.arrayGioHang == null){}
        else {
            adapterGiohang = new GioHang_Adapter(GioHangActivity.this, TrangChinhActivity.arrayGioHang);
            listGiohang.setAdapter(adapterGiohang);
        }
    }
    private void ClickButton(){
        btMuangayGiohang.setOnTouchListener(new View.OnTouchListener() {
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
        btThemmoiGiohang.setOnTouchListener(new View.OnTouchListener() {
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
    private void ToolbarGioHang(){
        setSupportActionBar(toolbarGiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGiohang.setNavigationIcon(R.drawable.ic_left);
        toolbarGiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //Kiểm tra dữ liệu bên trong giỏ hàng
    private void CheckGioHang(){
        if (TrangChinhActivity.arrayGioHang.size() <= 0){
            tvThongbaoGiohang.setVisibility(View.VISIBLE);//Cho nó hiện ra
            listGiohang.setVisibility(View.INVISIBLE);//Cho nó ẩn đi
        }
        else {
            tvThongbaoGiohang.setVisibility(View.INVISIBLE);//Cho nó hiện ra
            listGiohang.setVisibility(View.VISIBLE);//Cho nó ẩn đi
        }
    }

    //Hiển thị tổng tiền
    public static void DataListView(){
        long tongtien = 0;
        for (int i = 0; i < TrangChinhActivity.arrayGioHang.size(); i++){
            tongtien += TrangChinhActivity.arrayGioHang.get(i).getTongtien();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTienGiohang.setText(decimalFormat.format(tongtien) + " Đ");
    }
    private void ClickItemsLongListView(){
        listGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xoá Sản Phẩm");
                builder.setMessage("Bạn có muốn xoá sản phẩm này không?");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(TrangChinhActivity.arrayGioHang.size() > 0) {
                            //Bắt vị trí theo listview
                            TrangChinhActivity.arrayGioHang.remove(position);

                            //Cập nhật lại tổng tiền
                            DataListView();
                            //Cập nhật lại màn hình
                            adapterGiohang.notifyDataSetChanged();
                            //Sau khi xoá xong nếu dữ liệu trống ta hiện thị giỏ hàng trống
                            if(TrangChinhActivity.arrayGioHang.size() > 0){
                                DataListView();
                                adapterGiohang.notifyDataSetChanged();
                            }
                            else {
                                tvThongbaoGiohang.setVisibility(View.VISIBLE);
                                listGiohang.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }
    private void ClickButtonn(){
        btThemmoiGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrangChinhActivity.class);
                startActivity(intent);
            }
        });
        btMuangayGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TrangChinhActivity.arrayGioHang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), ThongTinMuaHangActivity.class);
                    startActivity(intent);
                }
                else {
                    Chech_internet.ShowToast(getApplicationContext(), "Giỏ hàng của bạn đang trống");
                }
            }
        });
    }


}
