package com.it9.mimi.API.Controller;

import android.content.Intent;

import com.it9.mimi.API.Model.Account;
import com.it9.mimi.API.Model.GioHang;
import com.it9.mimi.API.Model.GioHangRequest;
import com.it9.mimi.API.Model.GioHangResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.login;
import com.it9.mimi.signUp;
import com.it9.mimi.ui.GioHangActionable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangController {
    private static GioHangController instance;
    public static GioHangController Instance(){
        if(instance == null){
            instance = new GioHangController();
        }
        return instance;
    }

    private GioHangActionable view;

    public void SetCurrentActionable(GioHangActionable view) {
        this.view = view;
    }

    public void ShowAllGioHang(){
        Account account = AccountController.Instance().GetAccount();
        GioHangRequest gioHangRequest = new GioHangRequest(account.getMataikhoan());
        RetrofitController.GetAPIService().giohang_show(gioHangRequest).enqueue(new Callback<GioHangResponse>() {
            @Override
            public void onResponse(Call<GioHangResponse> call, Response<GioHangResponse> response) {
                if (response.isSuccessful()) {
                    GioHangResponse matHangReponse = response.body();
                    if (matHangReponse != null && matHangReponse.isSuccess() && matHangReponse.getGioHangList() != null) {
                        view.ShowSuccessGH(matHangReponse.getGioHangList());
                    } else {
                        view.ShowError("Show failed! " + matHangReponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<GioHangResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void Update(String magiohang, int soluong) {
        GioHangRequest gioHangRequest = new GioHangRequest(magiohang, soluong);
        RetrofitController.GetAPIService().giohang_update(gioHangRequest).enqueue(new Callback<GioHangResponse>() {
            @Override
            public void onResponse(Call<GioHangResponse> call, Response<GioHangResponse> response) {
                if (response.isSuccessful()) {
                    GioHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccessGH(gioHangRequest);
                    } else {
                        view.ShowError("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<GioHangResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void Find(String mamathang, Runnable  onSuccess, Runnable  onError){
        Account account = AccountController.Instance().GetAccount();
        GioHangRequest gioHangRequest = new GioHangRequest(account.getMataikhoan(), mamathang);
        RetrofitController.GetAPIService().giohang_find(gioHangRequest).enqueue(new Callback<GioHangResponse>() {
            @Override
            public void onResponse(Call<GioHangResponse> call, Response<GioHangResponse> response) {
                if (response.isSuccessful()) {
                    GioHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        if (onSuccess != null) onSuccess.run();
                    } else {
                        if (onError != null) onError.run();
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<GioHangResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void Create(String mamathang, int soluong) {
        Account account = AccountController.Instance().GetAccount();
        GioHangRequest gioHangRequest = new GioHangRequest(account.getMataikhoan(), mamathang, soluong);
        RetrofitController.GetAPIService().giohang_create(gioHangRequest).enqueue(new Callback<GioHangResponse>() {
            @Override
            public void onResponse(Call<GioHangResponse> call, Response<GioHangResponse> response) {
                if (response.isSuccessful()) {
                    GioHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccessGH(new GioHangRequest(mamathang, soluong));
                    } else {
                        view.ShowError("Show failed!" +matHangResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<GioHangResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    private static int counter = 0;
    public void Delete(List<GioHang> gioHangList) {
        if(gioHangList == null || gioHangList.size() == 0){
            if (view == null) return;

            gioHangList = view.getGioHangList();
            if (gioHangList == null || gioHangList.size() == 0) return;
        }

        counter = gioHangList.size();
        for (GioHang gioHang : gioHangList) {
            GioHangRequest gioHangRequest = new GioHangRequest(gioHang.getMagiohang());
            RetrofitController.GetAPIService().giohang_delete(gioHangRequest).enqueue(new Callback<GioHangResponse>() {
                @Override
                public void onResponse(Call<GioHangResponse> call, Response<GioHangResponse> response) {
                    if (response.isSuccessful()) {
                        GioHangResponse matHangResponse = response.body();
                        if (matHangResponse != null && matHangResponse.isSuccess()) {
                            counter--;
                            if(counter == 0){
                                ShowAllGioHang();
                            }
                        } else {
                            counter--;
                            if(counter == 0){
                                ShowAllGioHang();
                            }
                            view.ShowError("Show failed!" +matHangResponse.getMessage());
                        }
                    } else {
                        view.ShowError("Lỗi server, vui lòng thử lại sau.");
                    }
                }

                @Override
                public void onFailure(Call<GioHangResponse> call, Throwable t) {
                    view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
                }
            });
        }
    }
}
