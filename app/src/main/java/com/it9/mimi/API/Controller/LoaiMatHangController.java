package com.it9.mimi.API.Controller;

import com.it9.mimi.API.Model.LoaiMatHangResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.ui.LoaiMatHangActionable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiMatHangController {
    private static LoaiMatHangController instance;
    public static LoaiMatHangController Instance() {
        if (instance == null) {
            instance = new LoaiMatHangController();
        }
        return instance;
    }

    private LoaiMatHangActionable view;
    public void SetCurrentView(LoaiMatHangActionable view) {
        this.view = view;
    }
    public void Show(){
        RetrofitController.GetAPIService().loaimathang_show().enqueue(new Callback<LoaiMatHangResponse>() {
            @Override
            public void onResponse(Call<LoaiMatHangResponse> call, Response<LoaiMatHangResponse> response) {
                if (response.isSuccessful()) {
                    LoaiMatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccessLMH(matHangResponse.LoaimathangList);
                    } else {
                        view.ShowError("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<LoaiMatHangResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }
}
