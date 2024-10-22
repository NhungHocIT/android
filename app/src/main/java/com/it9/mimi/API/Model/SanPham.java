package com.it9.mimi.API.Model;

public class SanPham {
    private String tenSanPham;
    private int soLuong;
    private int gia;

    // Constructor
    public SanPham(String tenSanPham, int soLuong, int gia) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    // Getter cho từng thuộc tính
    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getGia() {
        return gia;
    }

    // Setter nếu cần (tùy chỉnh nếu muốn thay đổi dữ liệu sau khi tạo đối tượng)
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}

