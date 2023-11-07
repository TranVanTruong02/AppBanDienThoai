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
    //Khi sửu dụng RecyclerView ta bắt buộc phải sử dụng VewHoder. Lúc này ta phải tạo ItemsHolder trước
public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemsHolder> {
    //Truyền vào các đối tượng ta sử dụng
    Context context; //màn hình sử dụng
    ArrayList<SanPham> listSanPham; //Truyền vào mảng ta cần sử dụng dữ liệu

    //Khởi tạo constructor
    public SanPhamAdapter(Context context, ArrayList<SanPham> listSanPham) {
        this.context = context;
        this.listSanPham = listSanPham;
    }

    @NonNull
    @Override //KhỞi tạo lại layout gọi bên ngoài
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Ta lấy biến view là cái màn hình layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_sanpham_new,null);
        ItemsHolder itemsHolder = new ItemsHolder(v);
        return itemsHolder;
    }

    @Override //Hỗ trợ get và set lại dữu liệu trên layout
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        SanPham sanPham = listSanPham.get(position);
        holder.tensp.setText(sanPham.getTenSP());
        //DecimalFormat giúp định dạng dấu phẩy cho giá
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        //Lấy đường link cho ảnh ta sử dụng thư viện picasso
        Picasso.get().load(sanPham.getAnhSP())
                .placeholder(R.drawable.ic_load_img) //Khi ảnh đang loat
                .error(R.drawable.ic_load_img_erro) //Khi không loat được
                .into(holder.anhsp); //Khi loat được
    }

    @Override
    public int getItemCount() { //get ra toàn bộ giá trị trong mảng
        return listSanPham.size();
    }

    //Khi sử dụng RecyclerView ta bắt buộc phải sử dụng ViewHolder
    //Đầu tiên phải khởi tạo 1 ViewHolder
    public class ItemsHolder extends RecyclerView.ViewHolder{
        public ImageView anhsp;
        public TextView tensp, giasp;

        public ItemsHolder(@NonNull View itemView) {//Ở đây ta sẽ đi tạo layout cho từng dòng
            super(itemView);
            //Ánh xạ rồi sử dụng ItemsHolder cho RecyclerView
            anhsp = itemView.findViewById(R.id.image_sanpham);
            tensp = itemView.findViewById(R.id.tv_tensp);
            giasp = itemView.findViewById(R.id.tv_giasp);
            //sự kiện chuyển màn hình của sản phẩm mới nhất trong RecyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    //Vì các dữ liệu chuyển qua nó giống nhau lên ta có thể để chung 1 từ khoá
                    intent.putExtra("thongtinsanpham", listSanPham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Test
                    Chech_internet.ShowToast(context, listSanPham.get(getPosition()).getTenSP());
                    context.startActivity(intent);
                }
            });
        }
    }
}
