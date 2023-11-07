package com.example.appbandienthoai.molder;

public class LichSuDonHang {
    int iddonhang;
    int idchitietdonhang;
    String anhsanpham;
    String tensanpham;
    long giasanpham;
    int soluongsanpham;
    String tenkhachhang;
    String sodienthoai;
    String diachi;
    String phuongthucthanhtoan;
    long tongtien;

    //Khởi tạo constructor ban đầu
    public LichSuDonHang() {
    }

    public LichSuDonHang(int iddonhang, int idchitietdonhang, String anhsanpham, String tensanpham, long giasanpham, int soluongsanpham, String tenkhachhang, String sodienthoai, String diachi, String phuongthucthanhtoan, long tongtien) {
        this.iddonhang = iddonhang;
        this.idchitietdonhang = idchitietdonhang;
        this.anhsanpham = anhsanpham;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.soluongsanpham = soluongsanpham;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.tongtien = tongtien;
    }

    //Khởi tạo get and set
    public int getIddonhang() {
        return iddonhang;
    }

    public void setIddonhang(int iddonhang) {
        this.iddonhang = iddonhang;
    }

    public int getIdchitietdonhang() {
        return idchitietdonhang;
    }

    public void setIdchitietdonhang(int idchitietdonhang) {
        this.idchitietdonhang = idchitietdonhang;
    }
    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public long getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(long giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(int soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(String phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }
}