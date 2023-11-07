package com.example.appbandienthoai.molder;
//Ta cần sử dụng 1 mảng toàn cục để tránh mất dữ liệu trong mảng
public class GioHang {
    public int idsp;
    public String tensp;
    public long giasp;
    public String anhsp;
    public int soluongsp;
    public long tongtien;
    //Khơi tạo giáo trị contructer ban đầu

    public GioHang() {
    }

    public GioHang(int idsp, String tensp, long giasp, String anhsp, int soluongsp, long tongtien) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.anhsp = anhsp;
        this.soluongsp = soluongsp;
        this.tongtien = tongtien;
    }
    //Khởi tạo get và set

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

}
