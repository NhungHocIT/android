package com.it9.mimi.API.Controller;

import com.it9.mimi.API.Model.Account;
import com.it9.mimi.API.Model.AccountRequest;
import com.it9.mimi.API.Model.AccountResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.ui.AccountActionable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountController {
    private static AccountController instance;
    public static AccountController Instance(){
        if(instance == null){
            instance = new AccountController();
        }
        return instance;
    }

    private Account account;
    private AccountActionable view;

    public void SetCurrentView(AccountActionable view) {
        this.view = view;
    }

    public void login(String phone, String password) {

        AccountRequest accountRequest = new AccountRequest(phone, password);

        //call api
        RetrofitController.GetAPIService().login(accountRequest).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    AccountResponse accountResponse = response.body();
                    if (accountResponse != null && accountResponse.isSuccess()) {
                        account = accountResponse.getAccount();
                        view.ShowSuccess(accountResponse.getAccount());
                    } else {
                        view.ShowError("Đăng nhập thất bại! " + accountResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void signUp(String sodienthoai, String matkhau, String hovaten) {
        AccountRequest accountRequest = new AccountRequest(sodienthoai, matkhau, hovaten);
        RetrofitController.GetAPIService().signUp(accountRequest).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    AccountResponse accountResponse = response.body();
                    if (accountResponse != null && accountResponse.isSuccess()) {
                        account = accountResponse.getAccount();
                        view.ShowSuccess(accountResponse.getAccount());
                    } else {
                        view.ShowError("Đăng nhập thất bại! " + accountResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void changeProfile(String name, String address) {
        if(account == null){
            view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            return;
        }

        AccountRequest accountRequest = new AccountRequest(account.getMataikhoan(), account.getSodienthoai(), name, address);
        RetrofitController.GetAPIService().taikhoan_update(accountRequest).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    AccountResponse accountResponse = response.body();
                    if (accountResponse != null && accountResponse.isSuccess()) {
                        account = accountResponse.getAccount();
                        view.ShowSuccess(accountResponse.getAccount());
                    } else {
                        view.ShowError("Thay đổi thông tin thất bại! " + accountResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }
    public void show () {

        RetrofitController.GetAPIService().taikhoan_show().enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    AccountResponse accountResponse = response.body();//mac dinh
                    if (accountResponse != null && accountResponse.isSuccess()) {//mac dinh
                        view.ShowSuccess(accountResponse.getListAccount());
                    } else {
                        view.ShowError("Thay đổi thông tin thất bại! " + accountResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }

            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public Account GetAccount() {
        return account;
    }
}
