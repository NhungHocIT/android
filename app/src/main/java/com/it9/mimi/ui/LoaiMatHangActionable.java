package com.it9.mimi.ui;

import com.it9.mimi.API.Model.loaimathang;

import java.util.List;

public interface LoaiMatHangActionable {
    void ShowSuccessLMH(List<loaimathang> mathangList);
    void ShowError(String messs);
}
