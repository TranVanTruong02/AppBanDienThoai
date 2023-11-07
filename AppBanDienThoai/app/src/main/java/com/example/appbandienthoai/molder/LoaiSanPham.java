package com.example.appbandienthoai.molder;

public class LoaiSanPham {
    //Khai báo 3 biến có trong phpadmin của loại sản phẩm
    public int id;
    public String loaisp, anhsanpham;

    //Khởi tạo constructor ban đầu
    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, String loaisp, String anhsanpham) {
        this.id = id;
        this.loaisp = loaisp;
        this.anhsanpham = anhsanpham;
    }

    //Khởi tạo get and set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }
}
