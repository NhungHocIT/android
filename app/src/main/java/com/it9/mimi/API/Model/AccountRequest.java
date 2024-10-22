package com.it9.mimi.API.Model;

public class AccountRequest {
    private int mataikhoan;
    private String sodienthoai;
    private String matkhau;
    private String hovaten;
    private String diachi;

    public AccountRequest(String sodienthoai, String matkhau) {
        this.sodienthoai = sodienthoai;
        this.matkhau = matkhau;
    }

    public AccountRequest(String sodienthoai, String matkhau, String hovaten) {
        this.sodienthoai = sodienthoai;
        this.matkhau = matkhau;
        this.hovaten = hovaten;
    }

    public AccountRequest(int mataikhoan, String sodienthoai, String hovaten, String diachi) {
        this.sodienthoai = sodienthoai;
        this.hovaten = hovaten;
        this.diachi = diachi;
        this.mataikhoan = mataikhoan;
    }


    public String getSodienthoai() {
        return sodienthoai;
    }
    public String getMatkhau() {
        return matkhau;
    }
    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public String getHovaten() {
        return hovaten;
    }
    public String getDiachi() {
        return diachi;
    }
}

