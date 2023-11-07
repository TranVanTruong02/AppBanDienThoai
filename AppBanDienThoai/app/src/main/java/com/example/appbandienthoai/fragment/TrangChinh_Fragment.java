package com.example.appbandienthoai.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.activity.DienThoaiActivity;
import com.example.appbandienthoai.activity.TrangChinhActivity;
import com.example.appbandienthoai.adapter.SanPhamAdapter;
import com.example.appbandienthoai.adapter.TatCaSanPham_Adapter;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrangChinh_Fragment extends Fragment {

    View view; //gán layout cho fragment

    //Khai báo biến giao diện
    ViewFlipper viewFlipper_main;
    RecyclerView recyclerView_main, recyclerView_main_1;

    //Truyền dữ liệu sản phẩm đến RecyclerView
    ArrayList<SanPham> listSP;
    SanPhamAdapter sanPhamAdapter;

    ArrayList<SanPham> listTatCa;
    TatCaSanPham_Adapter tatCaSanPham_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chinh, container, false);

        if(Chech_internet.haveNetWorkConnection((AppCompatActivity)getActivity())){
            Anhxa_TrangChinh_Fragment();
            //Tạo slideshow
            ActionFlipper();
            //Đổ dữ liệu lên RecyclerView
            GetSanPham();
            GetTatCaSanPham();
        }
        else {
            Chech_internet.ShowToast(getActivity(), "Không có kết nối Internet!");
            getActivity().finish();
        }
        return view;
    }

    private void Anhxa_TrangChinh_Fragment(){
        viewFlipper_main = (ViewFlipper) view.findViewById(R.id.viewflipper_main);
        recyclerView_main = (RecyclerView) view.findViewById(R.id.recyclerview_main);
        recyclerView_main_1 = (RecyclerView) view.findViewById(R.id.recyclerview_main_1);
        //Truyền dữ liệu sản phẩm đến RecyclerView
        listSP = new ArrayList<>();
        //Truyền vào adapter màn hình và mảng arraylist
        sanPhamAdapter = new SanPhamAdapter(getActivity(),listSP);
        //Ta sử dụng recyslerview hiện thị dưới dạng 2 cột
        recyclerView_main.setHasFixedSize(true);
        //Truyền vào màn hình và muốn hiển thị ở 2 cột
        recyclerView_main.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView_main.setAdapter(sanPhamAdapter);

        listTatCa = new ArrayList<>();
        tatCaSanPham_adapter = new TatCaSanPham_Adapter(getActivity(), listTatCa);
        recyclerView_main_1.setHasFixedSize(true);
        recyclerView_main_1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView_main_1.setAdapter(tatCaSanPham_adapter);

        //------------------Cấp phát vùng bộ nhớ cho mảng giỏ hàng
        if(TrangChinhActivity.arrayGioHang != null){}
        else {
            TrangChinhActivity.arrayGioHang = new ArrayList<>();
        }
    }

    //Quảng Cáo
    private void ActionFlipper() {
        //Mảng lưu đường dẫn của các tấm hình
        ArrayList<Integer> advertissement = new ArrayList<>();
        advertissement.add(R.drawable.slideshow_2);
        advertissement.add(R.drawable.slideshow_3);
        advertissement.add(R.drawable.slideshow_4);
        advertissement.add(R.drawable.slideshow_5);
        advertissement.add(R.drawable.slideshow_6);
        //Gán link vào imageview
        for (int i = 0; i < advertissement.size(); i++) {
            ImageView imageView = new ImageView((AppCompatActivity)getActivity());
            Picasso.get().load(advertissement.get(i)).into(imageView);
            //Căn vừa ảnh với khung
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //Ép ImageView vào viewFlipper
            viewFlipper_main.addView(imageView);
        }
        //Tạo sự kiện viewFlipper tự chạy, với thời gian 5s
        viewFlipper_main.setFlipInterval(5000);
        viewFlipper_main.setAutoStart(true);
        //Truyền 2 hiệu ứng từ xml vào-Tạo đối tượng view động
        Animation animation_in = AnimationUtils.loadAnimation((AppCompatActivity)getActivity(), R.anim.slide_in_right);
        Animation animation_out = AnimationUtils.loadAnimation((AppCompatActivity)getActivity(), R.anim.slide_out_right);
        //Truyền 2 animatin vào viewflipper
        viewFlipper_main.setInAnimation(animation_in);
        viewFlipper_main.setOutAnimation(animation_out);
    }

    //Đọc dữ liệu sản phẩm
    private void GetSanPham(){
        //Đọc nội dung trên location
        RequestQueue requestQueue = Volley.newRequestQueue((AppCompatActivity)getActivity());
        //Đọc json
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sever_link.duongdanSanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Nếu như ó dữ liệu json thì mới thực hiện
                if(response != null){
                    //Khai báo các biến của sản phẩm
                    int id = 0, idsp = 0;
                    String tensp = "", motasp = "", anhsanpham = "";
                    Integer giasp = 0;
                    //Sử dụng vòng lặp for cho việc đọc dữ liệu json
                    for (int i = 0; i < response.length(); i++){
                        //Sử dụng json object để đọc dữ liệu json của từng object
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensp = jsonObject.getString("tenSP");
                            giasp = jsonObject.getInt("giaSP");
                            anhsanpham = jsonObject.getString("anhSP");
                            motasp = jsonObject.getString("moTaSP");
                            idsp = jsonObject.getInt("idSP");
                            listSP.add(new SanPham(id, tensp, giasp, anhsanpham, motasp, idsp));
                            //Cập nhật màn hình
                            sanPhamAdapter.notifyDataSetChanged();
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
        //Thực hiện requestQueue
        requestQueue.add(jsonArrayRequest);
    }

    private void GetTatCaSanPham(){
        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(sever_link.duongdanTatCaSanPham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0, idsp = 0;
                    String tensp = "", motasp = "", anhsanpham = "";
                    Integer giasp = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensp = jsonObject.getString("tenSP");
                            giasp = jsonObject.getInt("giaSP");
                            anhsanpham = jsonObject.getString("anhSP");
                            motasp = jsonObject.getString("moTaSP");
                            idsp = jsonObject.getInt("idSP");
                            listTatCa.add(new SanPham(id, tensp, giasp, anhsanpham, motasp, idsp));
                            tatCaSanPham_adapter.notifyDataSetChanged();
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
        requestQueue1.add(jsonArrayRequest1);
    }

}
