package com.example.appbandienthoai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.activity.GioHangActivity;
import com.example.appbandienthoai.activity.MainActivity;
import com.example.appbandienthoai.activity.TrangChinhActivity;
import com.example.appbandienthoai.molder.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHang_Adapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;
    //Khởi tạo constructor

    public GioHang_Adapter() {
    }

    public GioHang_Adapter(Context context, ArrayList<GioHang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
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
            view = inflater.inflate(R.layout.new_items_giohang, null);
            viewHolder.tvTenGiohang = (TextView) view.findViewById(R.id.tv_items_ten_giohang);
            viewHolder.tvGiaGiohang = (TextView) view.findViewById(R.id.tv_items_gia_giohang);
            viewHolder.tvSoluongGiohang = (TextView) view.findViewById(R.id.tv_items_soluong_giohang);
            viewHolder.btTruGiohang = (Button) view.findViewById(R.id.bt_items_tru_giohang);
            viewHolder.btCongGiohang = (Button) view.findViewById(R.id.bt_items_cong_giohang);
            viewHolder.imageAnhGiohang = (ImageView) view.findViewById(R.id.image_items_giohang);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang gioHang = (GioHang) getItem(i);
        viewHolder.tvTenGiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvGiaGiohang.setText(decimalFormat.format(gioHang.getGiasp()) + " Đ");
        Picasso.get().load(gioHang.getAnhsp())
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(viewHolder.imageAnhGiohang);
        viewHolder.tvSoluongGiohang.setText(gioHang.getSoluongsp() + "");

        //Lấy số lượng có trong tv số lượng sản phẩm mua và so sánh
        int soluongsanpham = Integer.parseInt(viewHolder.tvSoluongGiohang.getText().toString());
        if(soluongsanpham >= 10){ //Nếu lớn hơn hoặc bằng 10 thì không hiển thị nút cộng
            viewHolder.btCongGiohang.setVisibility(view.INVISIBLE);
            viewHolder.btTruGiohang.setVisibility(view.VISIBLE);
        }
        else if(soluongsanpham <= 1){
            viewHolder.btCongGiohang.setVisibility(view.VISIBLE);
            viewHolder.btTruGiohang.setVisibility(view.INVISIBLE);
        }
        else { //Nếu nhỏ hơn hoặn bằng 1 thì không hiện thị nút trừ
            viewHolder.btCongGiohang.setVisibility(view.VISIBLE);
            viewHolder.btTruGiohang.setVisibility(view.VISIBLE);
        }

        //Khởi tại viewHolder cuối cùng
        ViewHolder finalViewHolder = viewHolder;
        //Bắt sự kiện cho bt cộng, và update lại giá
        viewHolder.btCongGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy ra số lượng hiện tại
                int soluonghientai = TrangChinhActivity.arrayGioHang.get(i).getSoluongsp();
                //Lấy ra số lượng mới nhất
                int soluongmoinhat = soluonghientai + 1;
                //Ép lại cho mảng số lượng mới nhất
                TrangChinhActivity.arrayGioHang.get(i).setSoluongsp(soluongmoinhat);
                //Lấy ra giá hiện tại
                long giasanpham = TrangChinhActivity.arrayGioHang.get(i).getGiasp();
                //Lấy ra tổng tiền hiện tại
                long tongtienhientai = TrangChinhActivity.arrayGioHang.get(i).getTongtien();
                //Tổng tiền mới nhất bằng tổng tiền hiện tại + giá sản phẩm
                long tongtienmoinhat = tongtienhientai + giasanpham;
                TrangChinhActivity.arrayGioHang.get(i).setTongtien(tongtienmoinhat);
                //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                //finalViewHolder.tvGiaGiohang.setText(decimalFormat.format(giamoinhat) + " Đ");
                //Update tổng tiền
                GioHangActivity.DataListView();
                //Sự kiện hiển, ẩn của cộng trừ
                if (soluongmoinhat > 9){
                    finalViewHolder.btCongGiohang.setVisibility(view.INVISIBLE);
                    finalViewHolder.btTruGiohang.setVisibility(view.VISIBLE);
                    finalViewHolder.tvSoluongGiohang.setText(String.valueOf(soluongmoinhat));//Truyển kiểu int thàng string
                }
                else { //Nếu nhỏ hơn hoặn bằng 1 thì không hiện thị nút trừ
                    finalViewHolder.btCongGiohang.setVisibility(view.VISIBLE);
                    finalViewHolder.btTruGiohang.setVisibility(view.VISIBLE);
                    finalViewHolder.tvSoluongGiohang.setText(String.valueOf(soluongmoinhat));
                }
            }
        });
        //Bắt sự kiện cho nút trừ
        viewHolder.btTruGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lấy ra số lượng sản phẩm hiện tại
                int soluonghientai = TrangChinhActivity.arrayGioHang.get(i).getSoluongsp();
                int soluongmoinhat = soluonghientai - 1;
                //lấy ra giá sản phẩm
                long giasanpham = TrangChinhActivity.arrayGioHang.get(i).getGiasp();
                //Lấy ra tổng tiền hiện tại
                long tongtienhientai = TrangChinhActivity.arrayGioHang.get(i).getTongtien();
                //Tổng tiền mới nhất bằng tổng tiền trừ đi giá sản phẩm
                long tongtienmoinhat = tongtienhientai - giasanpham;
                TrangChinhActivity.arrayGioHang.get(i).setSoluongsp(soluongmoinhat);
                TrangChinhActivity.arrayGioHang.get(i).setTongtien(tongtienmoinhat);
                GioHangActivity.DataListView();
                if (soluongmoinhat < 2){
                    finalViewHolder.btCongGiohang.setVisibility(view.VISIBLE);
                    finalViewHolder.btTruGiohang.setVisibility(view.INVISIBLE);
                    finalViewHolder.tvSoluongGiohang.setText(String.valueOf(soluongmoinhat));
                }
                else {
                    finalViewHolder.btCongGiohang.setVisibility(view.VISIBLE);
                    finalViewHolder.btTruGiohang.setVisibility(view.VISIBLE);
                    finalViewHolder.tvSoluongGiohang.setText(String.valueOf(soluongmoinhat));
                }
            }
        });
        return view;
    }
    public class ViewHolder{
        ImageView imageAnhGiohang;
        TextView tvTenGiohang, tvGiaGiohang, tvSoluongGiohang;
        Button btTruGiohang, btCongGiohang;
    }
}
