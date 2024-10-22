package com.it9.mimi.ui;

import com.it9.mimi.API.Model.*;

import java.util.List;

public interface DonHangActionable {
    void ShowSuccessHD(donhang donhang);
    void ShowSuccessHD(List<donhang> donhangList);
    void ShowError(String mess);
    void ShowChiTietDonHang(List<chitietdonhang> chitietdonhangList);
}
