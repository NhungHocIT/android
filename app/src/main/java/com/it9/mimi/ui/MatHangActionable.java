package com.it9.mimi.ui;

import com.it9.mimi.API.Model.mathang;

import java.util.List;

public interface MatHangActionable{
    void ShowSuccess(mathang mathang);
    void ShowSuccess(List<mathang> mathangList);
    void ShowError(String mess);
}
