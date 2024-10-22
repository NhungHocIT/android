package com.it9.mimi.API.Model;
import java.util.List;
public class GioHangResponse {
    private String success;
    private String message;
    private List<GioHang> giohangList;
    private GioHang giohang_obj;

    public GioHang getGioHangObj() {
        return giohang_obj;
    }

    public List<GioHang> getGioHangList() {
        return giohangList;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success.equals("complete");
    }
}
