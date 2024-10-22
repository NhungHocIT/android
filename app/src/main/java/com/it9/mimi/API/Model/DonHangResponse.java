package com.it9.mimi.API.Model;

import java.io.Serializable;
import java.util.List;

public class DonHangResponse implements Serializable {
    private String success;
    private String message;
    private List<donhang> donhangList;
    public donhang donhang_obj;

    public boolean isSuccess() {
        return success.equals("complete");
    }

    public String getMessage() {
        return message;
    }
}
