package com.example.appbandienthoai.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.molder.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LapTop_Adapter extends BaseAdapter {
    //Truyền vào màn hình và mảng
    Context context;
    ArrayList<SanPham> arrayLapTop;

    //Khởi tạo constructor ban đầu

    public LapTop_Adapter(Context context, ArrayList<SanPham> arrayLapTop) {
        this.context = context;
        this.arrayLapTop = arrayLapTop;
    }

    @Override
    public int getCount() {
        return arrayLapTop.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayLapTop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_laptop_items, null);
            viewHolder.tvTenLapTop = (TextView) view.findViewById(R.id.tv_laptop_ten);
            viewHolder.tvGiaLapTop = (TextView) view.findViewById(R.id.tv_laptop_gia);
            viewHolder.tvMotoLapTop = (TextView) view.findViewById(R.id.tv_laptop_mota);
            viewHolder.imageLapTop = (ImageView) view.findViewById(R.id.image_laptop);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SanPham sanPham = (SanPham) getItem(i);
        viewHolder.tvTenLapTop.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvGiaLapTop.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        //sử dụng setMaxLines để chỉ cho hiện thị 2 dòng
        viewHolder.tvMotoLapTop.setMaxLines(2);
        //sử dụng setEllipsize nếu chữ nhiều quá thì hiện dưới dạng 3 chấm
        viewHolder.tvMotoLapTop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvMotoLapTop.setText(sanPham.getMoTaSP());
        Picasso.get().load(sanPham.getAnhSP())
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(viewHolder.imageLapTop);
        return view;
    }
    private class ViewHolder{
        ImageView imageLapTop;
        TextView tvTenLapTop, tvGiaLapTop, tvMotoLapTop;
    }
}
