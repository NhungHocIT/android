package com.it9.mimi.API.Model;

import java.util.List;

public class ThongKeResponse {
    public List<ThongKe> list;
    private String success;
    private String message;

    public boolean isSuccess(){
        return success.equals("complete");
    }

    public String getMessage(){
        return message;
    }
}
