package com.it9.mimi.API.Model;

import java.util.List;

public class LoaiMatHangResponse {
    private String message;
    private String success;
    public List<loaimathang> LoaimathangList;
    public boolean isSuccess() {
        return success.equals("complete");
    }

    public String getMessage() {
        return message;
    }
}
