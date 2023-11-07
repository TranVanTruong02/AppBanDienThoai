package com.example.appbandienthoai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.molder.LichSuDonHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LichSuDonHang_Adapter extends BaseAdapter {
    //Mảng và màn hình
    Context context;
    ArrayList<LichSuDonHang> arrayLichsudonhang = null;

    //Khởi tạo constructor ban đầu
    public LichSuDonHang_Adapter() {
    }

    public LichSuDonHang_Adapter(Context context, ArrayList<LichSuDonHang> arrayLichsudonhang) {
        this.context = context;
        this.arrayLichsudonhang = arrayLichsudonhang;
    }

    @Override
    public int getCount() {
        return arrayLichsudonhang.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayLichsudonhang.get(i);
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
            view = inflater.inflate(R.layout.activity_lich_su_don_hang_items, null);
            viewHolder.imagesanpham = (ImageView) view.findViewById(R.id.lichsu_item_image);
            viewHolder.tvtensanpham = (TextView) view.findViewById(R.id.lichsu_item_tensanpham);
            viewHolder.tvgiasanpham = (TextView) view.findViewById(R.id.lichsu_item_gia);
            viewHolder.tvsoluongsanpham = (TextView) view.findViewById(R.id.lichsu_item_soluong);
            viewHolder.tvtenkhachhang = (TextView) view.findViewById(R.id.lichsu_item_tenkhachhang);
            viewHolder.tvsodienthoai = (TextView) view.findViewById(R.id.lichsu_item_sodienthoai);
            viewHolder.tvdiachi = (TextView) view.findViewById(R.id.lichsu_item_diachi);
            viewHolder.tvptthanhtoan = (TextView) view.findViewById(R.id.lichsu_item_thanhtoan);
            viewHolder.tvtongtien = (TextView) view.findViewById(R.id.lichsu_item_tongtien);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LichSuDonHang lichSuDonHang = (LichSuDonHang) getItem(i);
        viewHolder.tvtensanpham.setText(lichSuDonHang.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvgiasanpham.setText(decimalFormat.format(lichSuDonHang.getGiasanpham()) + " Đ");
        viewHolder.tvsoluongsanpham.setText(lichSuDonHang.getSoluongsanpham() + "");
        Picasso.get().load(lichSuDonHang.getAnhsanpham())
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(viewHolder.imagesanpham);
        viewHolder.tvtenkhachhang.setText(lichSuDonHang.getTenkhachhang());
        viewHolder.tvsodienthoai.setText(lichSuDonHang.getSodienthoai() + "");
        viewHolder.tvdiachi.setText(lichSuDonHang.getDiachi());
        viewHolder.tvptthanhtoan.setText(lichSuDonHang.getPhuongthucthanhtoan());
        DecimalFormat decimalFormat1 = new DecimalFormat("###,###,###");
        viewHolder.tvtongtien.setText(decimalFormat1.format(lichSuDonHang.getTongtien()) + " Đ");
        return view;
    }

    public class ViewHolder{
        ImageView imagesanpham;
        TextView tvtensanpham, tvgiasanpham, tvsoluongsanpham, tvtenkhachhang, tvsodienthoai, tvdiachi, tvptthanhtoan, tvtongtien;
    }
}
