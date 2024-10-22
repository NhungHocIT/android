package com.it9.mimi.ui;

import com.it9.mimi.API.Model.GioHang;
import com.it9.mimi.API.Model.GioHangRequest;

import java.util.List;

public interface GioHangActionable {
    void ShowSuccessGH(GioHangRequest giohang);
    void ShowSuccessGH(List<GioHang> giohangList);
    void ShowError(String mess);
    List<GioHang> getGioHangList();
}
