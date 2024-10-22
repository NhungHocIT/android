package com.it9.mimi.API.Model;

import java.util.List;

public class MatHangResponse {
    private String success;
    private String message;
    private List<mathang> mathangList;
    public List<mathang> getMathangList() {
        return mathangList;
    }

    public String getMessage() {
        return message;
    }


    public boolean isSuccess() {
        return success.equals("complete");
    }
}
