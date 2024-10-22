package com.it9.mimi.API.Controller;

import com.it9.mimi.API.Model.ChiTietDonHangRequest;
import com.it9.mimi.API.Model.GioHang;
import com.it9.mimi.API.Model.GioHangRequest;
import com.it9.mimi.API.Model.GioHangResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.API.Model.chitietdonhang;
import com.it9.mimi.API.Model.chitietdonhangRespone;
import com.it9.mimi.ui.DonHangActionable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDonHangController {
    private static ChiTietDonHangController instance;
    public static ChiTietDonHangController Instance() {
        if (instance == null) {
            instance = new ChiTietDonHangController();
        }
        return instance;
    }

    private DonHangActionable view;
    public void SetCurrentView(DonHangActionable view) {
        this.view = view;
    }

    private void ShowError(String message){
        if(this.view != null ){
            this.view.ShowError(message);
        }
    }

    private static int counter = 0;
    private static String madonhang;
    public void Create(String madonhang, List<GioHang> items, Consumer<String> onSuccess) {
        ChiTietDonHangRequest dhRequest = new ChiTietDonHangRequest();
        counter = items.size();
        this.madonhang = madonhang;
        for (GioHang gh: items) {
            dhRequest.madonhang = madonhang;
            dhRequest.mamathang = gh.getMamathang();
            dhRequest.soluong = gh.getSoluong();

            RetrofitController.GetAPIService().chitietdonhang_create(dhRequest).enqueue(new Callback<GioHangResponse>() {
                @Override
                public void onResponse(Call<GioHangResponse> call, Response<GioHangResponse> response) {
                    if (response.isSuccessful()) {
                        GioHangResponse matHangReponse = response.body();
                        if (matHangReponse != null && matHangReponse.isSuccess()) {
                            counter--;
                            if (counter == 0){
                                GioHangController.Instance().Delete(new ArrayList<GioHang>());
                                onSuccess.accept(madonhang);
                            }
                        } else {
                            ShowError("Show failed!" +matHangReponse.getMessage());
                        }
                    } else {
                        ShowError("Lỗi server, vui lòng thử lại sau.");
                    }
                }

                @Override
                public void onFailure(Call<GioHangResponse> call, Throwable t) {
                    ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
                }
            });
        }
    }

    public void Show(String mamathang) {
        ChiTietDonHangRequest dhRequest = new ChiTietDonHangRequest();
        dhRequest.madonhang = mamathang;
        RetrofitController.GetAPIService().chitietdonhang_show(dhRequest).enqueue(new Callback<chitietdonhangRespone>() {
            @Override
            public void onResponse(Call<chitietdonhangRespone> call, Response<chitietdonhangRespone> response) {
                if (response.isSuccessful()) {
                    chitietdonhangRespone matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        ShowSuccess(matHangResponse.getchitietdonhangList());
                    } else {
                        ShowError("Show failed!" +matHangResponse.getMessage());
                    }
                } else {
                    ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<chitietdonhangRespone> call, Throwable t) {
                ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void ShowSuccess(List<chitietdonhang> chitietdonhangList){
        if(view != null){
            view.ShowChiTietDonHang(chitietdonhangList);
        }
    }
}
