package com.it9.mimi.API.Model;

public class GioHangRequest {
    private int mataikhoan;
    private int soluong;
    private String magiohang;
    private String mamathang;

    public GioHangRequest(String magiohang){
        this.magiohang = magiohang;
    }

    public GioHangRequest(int mataikhoan){
        this.mataikhoan = mataikhoan;
    }

    public GioHangRequest(String magiohang, int soluong){
        this.magiohang = magiohang;
        this.soluong = soluong;
    }

    public GioHangRequest(int mataikhoan, String mamathang, int soluong){
        this.mataikhoan = mataikhoan;
        this.mamathang = mamathang;
        this.soluong = soluong;
    }

    public GioHangRequest(int mataikhoan, String mamathang) {
        this.mataikhoan = mataikhoan;
        this.mamathang = mamathang;
    }

    public String getMagiohang(){
        return magiohang;
    }

    public int getSoluong() {
        return soluong;
    }
}
