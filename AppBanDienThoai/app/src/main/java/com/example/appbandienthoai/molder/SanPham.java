package com.example.appbandienthoai.molder;

import java.io.Serializable;

public class SanPham implements Serializable{
    public int id, idloaiSP;
    public String tenSP, moTaSP, anhSP;
    public Integer giaSP;

    //Khởi tạo constructor ban đầu
    public SanPham() {
    }

    public SanPham(int id,String tenSP, Integer giaSP, String anhSP, String moTaSP, int idloaiSP) {
        this.id = id;
        this.giaSP = giaSP;
        this.idloaiSP = idloaiSP;
        this.tenSP = tenSP;
        this.moTaSP = moTaSP;
        this.anhSP = anhSP;
    }

    //Khởi tạo get and set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdloaiSP() {
        return idloaiSP;
    }

    public void setIdloaiSP(int idloaiSP) {
        this.idloaiSP = idloaiSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public Integer getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(Integer giaSP) {
        this.giaSP = giaSP;
    }

}
