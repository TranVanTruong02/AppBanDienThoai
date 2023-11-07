package com.example.appbandienthoai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.molder.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Sử dụng viewholder
public class LoaiSanPhamAdapter extends BaseAdapter {
    //Biến giao diện
    ArrayList<LoaiSanPham> arrLoaisp = null;
    Context context; //Màn hình nào

    public LoaiSanPhamAdapter(ArrayList<LoaiSanPham> arrLoaisp, Context context) {
        this.arrLoaisp = arrLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrLoaisp.size();//muốn lấy hết dữ liệu trong mảng
    }

    @Override
    public Object getItem(int i) {
        return arrLoaisp.get(i); //Lấy ra từng thuộc tính trong mảng
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Khai báo class viewholder
        ViewHolder viewHolder = null;
        //Nếu như view rỗng
        if(view == null){
            //gọi lại viewholder để sử dụng các thuộc tính bên trong
            viewHolder = new ViewHolder();
            //Lấy ra layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Lấy view theo layout_loaisanpham
            view = inflater.inflate(R.layout.layout_loaisanpham,null);
            //Gán id cho các thuộc tính layout
            viewHolder.tvloaisp = (TextView) view.findViewById(R.id.tv_loaisp);
            viewHolder.imgloaisp = (ImageView) view.findViewById(R.id.img_loaisp);
            //Gán id vào lại viewholder
            view.setTag(viewHolder);
        }
        else {
            //Khi chạy lạy lần 2 ta không cần lấy lại id và chỉ việc vhayj thôi, giúp nhanh hơn và giảm thiểu bộ nhớ sử dụng
            viewHolder = (ViewHolder) view.getTag(); //Lấy lại các tag đã gán vào
        }
        //lấy dữ liệu theo loai san pham
        LoaiSanPham loaiSanPham = (LoaiSanPham) getItem(i);
        //Xét những dữ liệu đã có trong mảng
        viewHolder.tvloaisp.setText(loaiSanPham.getLoaisp());
        //Lấy hình ảnh bằng picasso
        Picasso.get().load(loaiSanPham.getAnhsanpham())
                .placeholder(R.drawable.ic_load_img) //Khi đang loat
                .error(R.drawable.ic_load_img_erro) //Nếu lỗi không ra ảnh
                .into(viewHolder.imgloaisp); //Khi loat xong
        return view;
    }

    public class ViewHolder{
        ImageView imgloaisp;
        TextView tvloaisp;
    }
}
