package com.it9.mimi.ui;

import com.it9.mimi.API.Model.Account;

import java.util.List;

public interface AccountActionable {
    void ShowSuccess(Account account);
    void ShowError(String mess);
    void ShowSuccess(List<Account> taikhoanList);

}
