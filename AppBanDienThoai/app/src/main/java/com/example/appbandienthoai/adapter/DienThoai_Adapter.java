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

public class DienThoai_Adapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayDienThoai;

    public DienThoai_Adapter(Context context, ArrayList<SanPham> arrayDienThoai) {
        this.context = context;
        this.arrayDienThoai = arrayDienThoai;
    }

    //Khởi tạo constructor ban đầu
    @Override
    public int getCount() {//Kích thước mảng
        return arrayDienThoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayDienThoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_dienthoai_items,null);
            viewHolder.tvTenDT = view.findViewById(R.id.tv_dienthoai_ten);
            viewHolder.tvGiaDT = view.findViewById(R.id.tv_dienthoai_gia);
            viewHolder.tvMoTaDT = view.findViewById(R.id.tv_dienthoai_mota);
            viewHolder.imageAnhDT = view.findViewById(R.id.image_dienthoai);
            view.setTag(viewHolder);
        }
        else {
            Object tag = (ViewHolder) view.getTag();
        }
        SanPham sanPham = (SanPham) getItem(i);
        viewHolder.tvTenDT.setText(sanPham.getTenSP());
        //DecimalFormat giúp định dạng dấu phẩy cho giá
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvGiaDT.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        //sử dụng setMaxLines để chỉ cho hiện thị 2 dòng
        viewHolder.tvMoTaDT.setMaxLines(2);
        //sử dụng setEllipsize nếu chữ nhiều quá thì hiện dưới dạng 3 chấm
        viewHolder.tvMoTaDT.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvMoTaDT.setText(sanPham.getMoTaSP());
        Picasso.get().load(sanPham.getAnhSP())
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(viewHolder.imageAnhDT);
        return view;
    }
    private class ViewHolder{
        ImageView imageAnhDT;
        TextView tvTenDT, tvGiaDT, tvMoTaDT;
    }
}
