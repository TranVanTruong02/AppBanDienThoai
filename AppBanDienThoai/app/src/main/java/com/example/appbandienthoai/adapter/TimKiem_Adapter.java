package com.example.appbandienthoai.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbandienthoai.R;
import com.example.appbandienthoai.activity.ChiTietSanPhamActivity;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TimKiem_Adapter extends RecyclerView.Adapter<TimKiem_Adapter.ItemsHolder> {
    Context context;
    ArrayList<SanPham> arrayTimKiem = null;

    public TimKiem_Adapter(Context context, ArrayList<SanPham> arrayTimKiem) {
        this.context = context;
        this.arrayTimKiem = arrayTimKiem;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tim_kiem_items, null);
        ItemsHolder itemsHolder = new ItemsHolder(v);
        return itemsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        SanPham sanPham = arrayTimKiem.get(position);
        holder.tensp.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        holder.mota.setMaxLines(2);
        holder.mota.setEllipsize(TextUtils.TruncateAt.END);
        holder.mota.setText(sanPham.getMoTaSP());
        Picasso.get().load(sanPham.getAnhSP())
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(holder.anhsp);
    }

    @Override
    public int getItemCount() {
        return arrayTimKiem.size();
    }


    public class ItemsHolder extends RecyclerView.ViewHolder {
        public ImageView anhsp;
        public TextView tensp, giasp, mota;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            anhsp = itemView.findViewById(R.id.image_timkiem);
            tensp = itemView.findViewById(R.id.tv_timkiem_ten);
            giasp = itemView.findViewById(R.id.tv_timkiem_gia);
            mota = itemView.findViewById(R.id.tv_timkiem_mota);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    //Vì các dữ liệu chuyển qua nó giống nhau lên ta có thể để chung 1 từ khoá
                    intent.putExtra("thongtinsanpham", arrayTimKiem.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Test
                    Chech_internet.ShowToast(context, arrayTimKiem.get(getPosition()).getTenSP());
                    context.startActivity(intent);
                }
            });
        }
    }

}
