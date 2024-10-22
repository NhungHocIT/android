package com.it9.mimi.API.Model;

import com.it9.mimi.R;

import java.io.Serializable;

public class mathang implements Serializable {
    private String tenmathang;
    private String mamathang;
    private int maloaimathang;
    private int trangthai;
    private int gia;
    private int imageResource = 1; // Thêm thuộc tính này để lưu trữ hình ảnh
    private int soluongtronggiohang = 1;
    private String imageUrl;

    public mathang(){ }

    public mathang(String tenmathang, int gia, String imageUrl) {
        this.tenmathang = tenmathang;
        this.gia = gia;
        this.imageResource = R.drawable.bantrangnuong;
        this.imageUrl = imageUrl;
    }


    public String getTenmathang() {
        return tenmathang;
    }
    public int getMaloaimathang() {
        return maloaimathang;
    }
    public int getTrangthai() {
        return trangthai;
    }

    public int getGia() {
        return gia;
    }
    public String getMamathang() {
        return mamathang;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getSoluongtronggiohang() {
        return soluongtronggiohang;
    }

    public void tangSoluong() {
        soluongtronggiohang++;
    }

    public void giamSoluong() {
        if (soluongtronggiohang > 1) {
            soluongtronggiohang--;
        }
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
