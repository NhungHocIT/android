package com.it9.mimi.API.Model;

import java.io.Serializable;
import java.util.List;

public class chitietdonhangRespone implements Serializable {
    private String success;
    private String message;
    private List<chitietdonhang> chitietdonhangList;

    public boolean isSuccess() {
        return success.equals("complete");
    }

    public String getMessage() {
        return message;
    }

    public List<chitietdonhang> getchitietdonhangList() {
        return chitietdonhangList;
    }
}
