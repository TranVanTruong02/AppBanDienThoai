package com.example.appbandienthoai.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.molder.GioHang;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    //Biến giao diện
    Toolbar toolbarChitietsanpham;
    ImageView imageChitetsanpham;
    TextView tvTenChitietsanpham, tvGiaChitietsanpham, tvMotaChitietsanpham;
    Spinner spinnerChitietsanpham;
    Button btChitietsanpham;
    //Khai báo toàn cục các giá trị hứng dữ liệu
    int id = 0, gia_chitietsanpham = 0, idloaisp_chitietsanpham = 0;
    String ten_chitietsanpham = "",anh_chitietsanpham = "", mota_chitietsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa_Chitietsanpham();
       if( Chech_internet.haveNetWorkConnection(getApplicationContext())){
            Toolbar_ChiTietSanPham();
            ClickButton();
            GetInformation();
            CatchEvenSpinner();
            EvenButton();
        }
       else {
           Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
           NoInternetActivity.manhinh = ChiTietSanPhamActivity.this;
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
    private void AnhXa_Chitietsanpham(){
        toolbarChitietsanpham = (Toolbar) findViewById(R.id.tobar_chitietsanpham);
        imageChitetsanpham = (ImageView) findViewById(R.id.image_chitietsanpham);
        tvTenChitietsanpham = (TextView) findViewById(R.id.tv_ten_chitietsanpham);
        tvGiaChitietsanpham = (TextView) findViewById(R.id.tv_gia_chitietsanpham);
        tvMotaChitietsanpham = (TextView) findViewById(R.id.tv_mota_chitietsanpham);
        spinnerChitietsanpham = (Spinner) findViewById(R.id.spinner_chitietsanpham);
        btChitietsanpham = (Button) findViewById(R.id.bt_mua_chitietsanpham);
    }
    private void Toolbar_ChiTietSanPham(){
        setSupportActionBar(toolbarChitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitietsanpham.setNavigationIcon(R.drawable.ic_left);
        toolbarChitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void ClickButton(){
        btChitietsanpham.setOnTouchListener(new View.OnTouchListener() {
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
    //Lấy dữ liệu đc truyền qua và gán lên layout
    private void GetInformation(){
        //Gọi lại object SanPham và lấy dữ liệu đc chuyền qua theo dạng Serializable
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        //Gán dữ liệu cho các biến
        id = sanPham.getId();
        ten_chitietsanpham = sanPham.getTenSP();
        gia_chitietsanpham = sanPham.getGiaSP();
        anh_chitietsanpham = sanPham.getAnhSP();
        mota_chitietsanpham = sanPham.getMoTaSP();
        idloaisp_chitietsanpham = sanPham.getIdloaiSP();
        //Gán dữ liệu lên cho layout
        tvTenChitietsanpham.setText(ten_chitietsanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaChitietsanpham.setText("Giá: " + decimalFormat.format(gia_chitietsanpham) + " Đ");
        tvMotaChitietsanpham.setText(mota_chitietsanpham);
        Picasso.get().load(anh_chitietsanpham)
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(imageChitetsanpham);
    }
    //Giới hạn số lượn chọn cho spinner
    private void CatchEvenSpinner(){
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7,8, 9, 10};
        //Layout đặc trưng của Spinner simple_spinner_dropdown_item
        ArrayAdapter<Integer> arrayAdapteSpinner = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinnerChitietsanpham.setAdapter(arrayAdapteSpinner);
    }
    //Bắt sự kiện cho nút button
    private void EvenButton() {
        btChitietsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Chech_internet.haveNetWorkConnection(getApplicationContext())) {
                    //Kiểm tra dữ liệu mảng
                    int soluong = Integer.parseInt(spinnerChitietsanpham.getSelectedItem().toString());
                    //Hàm exits kiểm tra nếu như không tìm đươc
                    boolean exits = false;
                    if (TrangChinhActivity.arrayGioHang.size() > 0) {//Nếu có dữ liệu rồi, thì coi có cái nào cần update dữ liệu không
                        //Khởi tạo vòng for để kiểm tra
                        for (int i = 0; i < TrangChinhActivity.arrayGioHang.size(); i++) {
                            if (TrangChinhActivity.arrayGioHang.get(i).getIdsp() == id) {//nếu id của mảng trùng id ban đầu
                                //Thì update lại số lượng sản phẩm
                                TrangChinhActivity.arrayGioHang.get(i).setSoluongsp(TrangChinhActivity.arrayGioHang.get(i).getSoluongsp() + soluong);
                                //Nếu như số lượng bằng hoặc vượt quá 10
                                if (TrangChinhActivity.arrayGioHang.get(i).getSoluongsp() >= 10) {
                                    //Thì ta vẫn set lại số lượng chỉ tối đa có 10 sản phẩm
                                    TrangChinhActivity.arrayGioHang.get(i).setSoluongsp(10);
                                }
                                //Cuối cùng ta update lại giá sản phẩm sữa trên số lượng sản phẩm mới
                                TrangChinhActivity.arrayGioHang.get(i).setTongtien(gia_chitietsanpham * TrangChinhActivity.arrayGioHang.get(i).getSoluongsp());
                                exits = true;
                            }
                        }
                        //Nếu như không tìm được cái id nào trùng thì ta ép giá trị mới rô
                        if (exits == false) {
                            long giamoi = soluong * gia_chitietsanpham;
                            TrangChinhActivity.arrayGioHang.add(new GioHang(id, ten_chitietsanpham, gia_chitietsanpham, anh_chitietsanpham, soluong, giamoi));
                        }
                    } else {//Nếu như nhỏ hơn 0, không có dữ liệu, ta cần ép vào dữ liệu mới
                        //Lấy ra số lượng là vị trí items trong spinner dưới dạng kiểu int
                        long giamoi = soluong * gia_chitietsanpham;
                        TrangChinhActivity.arrayGioHang.add(new GioHang(id, ten_chitietsanpham, gia_chitietsanpham, anh_chitietsanpham, soluong, giamoi));
                    }
                    Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
                    NoInternetActivity.manhinh = ChiTietSanPhamActivity.this;
                    startActivity(intent);
                }
            }
        });
    }
}