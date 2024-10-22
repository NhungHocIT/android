package com.it9.mimi.API.Controller;

import android.widget.Toast;

import com.it9.mimi.API.Model.Account;
import com.it9.mimi.API.Model.DonHangRequest;
import com.it9.mimi.API.Model.DonHangResponse;
import com.it9.mimi.API.Model.GioHang;
import com.it9.mimi.API.Model.MatHangResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ui.DonHangActionable;
import com.it9.mimi.ui.MatHangActionable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangController {
    private static DonHangController instance;
    public static DonHangController Instance() {
        if (instance == null) {
            instance = new DonHangController();
        }
        return instance;
    }

    private DonHangActionable view;
    public void SetCurrentView(DonHangActionable view) {
        this.view = view;
    }


    public void Order(List<GioHang> items, Consumer<String> onSuccess) {
        if (items == null || items.size() == 0 || AccountController.Instance().GetAccount() == null){
            view.ShowError("Giỏ hàng trống!");
        }

        DonHangRequest dhRequest = new DonHangRequest();
        dhRequest.mataikhoan = AccountController.Instance().GetAccount().getMataikhoan();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        dhRequest.ngaythanhtoan = dateFormat.format(sqlDate);

        RetrofitController.GetAPIService().donhang_create(dhRequest).enqueue(new Callback<DonHangResponse>() {
            @Override
            public void onResponse(Call<DonHangResponse> call, Response<DonHangResponse> response) {
                if (response.isSuccessful()) {
                    DonHangResponse donHangResponse = response.body();
                    if (donHangResponse != null && donHangResponse.isSuccess() && donHangResponse.donhang_obj != null) {
                        ChiTietDonHangController.Instance().SetCurrentView(view);
                        ChiTietDonHangController.Instance().Create(donHangResponse.donhang_obj.madonhang, items, onSuccess);
                    } else {
                        view.ShowError("Show failed! " + donHangResponse.getMessage());
                    }
                } else {
                    view.ShowError("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<DonHangResponse> call, Throwable t) {
                view.ShowError("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }
}
