package com.it9.mimi.API.Model;

import java.util.List;

public class AccountResponse {
    private String success; // Thay đổi kiểu dữ liệu từ boolean sang String để khớp với giá trị "complete" hoặc "error"
    private String message; // Thay đổi kiểu dữ liệu từ boolean sang String để khớp với giá trị "complete" hoặc "error"
    private Account account; // Sử dụng lớp Account để ánh xạ phần account trong JSON
    private List<Account> taikhoanList;

    public boolean isSuccess(){
        return success.equals("complete");
    }
    // Getter và Setter cho success
    public String getSuccess() {
        return success;
    }

    // Getter và Setter cho account
    public Account getAccount() {
        return account;
    }
    public List<Account> getListAccount() {
        return taikhoanList;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public String getMessage() {
        return message;
    }
}
