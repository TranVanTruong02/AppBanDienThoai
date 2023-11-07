package com.example.appbandienthoai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TatCaSanPham_Adapter extends RecyclerView.Adapter<TatCaSanPham_Adapter.IemsHolder> {
    Context context;
    ArrayList<SanPham> arrayTatCa = null;

    //Khởi tạo constructor
    public TatCaSanPham_Adapter(Context context, ArrayList<SanPham> arrayTatCa) {
        this.context = context;
        this.arrayTatCa = arrayTatCa;
    }

    @NonNull
    @Override //Gọi ra màn hình
    public IemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_sanpham_new_tatca, null);
        IemsHolder iemsHolder = new IemsHolder(v);
        return iemsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IemsHolder holder, int position) {
        SanPham sanPham = arrayTatCa.get(position);
        holder.tensp.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        Picasso.get().load(sanPham.getAnhSP())
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_load_img_erro)
                .into(holder.anhsp);
    }

    @Override
    public int getItemCount() {
        return arrayTatCa.size();
    }

    public class IemsHolder extends RecyclerView.ViewHolder{
        public ImageView anhsp;
        public TextView tensp, giasp;

        public IemsHolder(@NonNull View itemView) {
            super(itemView);
            anhsp = itemView.findViewById(R.id.image_sanpham_1);
            tensp = itemView.findViewById(R.id.tv_tensp_1);
            giasp = itemView.findViewById(R.id.tv_giasp_1);

            //sự kiện chuyển màn hình của sản phẩm mới nhất trong RecyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    //Vì các dữ liệu chuyển qua nó giống nhau lên ta có thể để chung 1 từ khoá
                    intent.putExtra("thongtinsanpham", arrayTatCa.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Test
                    Chech_internet.ShowToast(context, arrayTatCa.get(getPosition()).getTenSP());
                    context.startActivity(intent);
                }
            });
        }
    }

}
