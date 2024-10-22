package com.it9.mimi.API.Model;

import java.io.Serializable;

public class GioHang implements Serializable {
    private String magiohang;
    private String mamathang;
    private String tenmathang;
    private int soluong;
    private int gia;

    // Constructor bao gồm hinhAnh
    public GioHang(String mamathang, String tenmathang, int gia, int soluong) {
        this.mamathang = mamathang;
        this.tenmathang = tenmathang;
        this.gia = gia;
        this.soluong = soluong;
    }    // Các getter và setter khác
    public String getMamathang() {
        return mamathang;
    }

    public String getMagiohang() {
        return magiohang;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getGia() {
        return gia;
    }

    public String getTenmathang() {
        return tenmathang;
    }

    public void setMagiohang(String magiohang) {
        this.magiohang = magiohang;
    }

    public void setMamathang(String mamathang) {
        this.mamathang = mamathang;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
