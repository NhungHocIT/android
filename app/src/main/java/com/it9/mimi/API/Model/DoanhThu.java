package com.it9.mimi.API.Model;

public class DoanhThu {
    private String tenMatHang;
    private int gia;
    private int doanhThu;

    public DoanhThu(String tenMatHang, int gia, int doanhThu) {
        this.tenMatHang = tenMatHang;
        this.gia = gia;
        this.doanhThu = doanhThu;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public int getGia() {
        return gia;
    }

    public int getDoanhThu() {
        return doanhThu;
    }
}

