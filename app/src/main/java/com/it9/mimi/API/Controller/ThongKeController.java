package com.it9.mimi.API.Controller;

import com.it9.mimi.API.Model.MatHangResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.API.Model.ThongKeRequest;
import com.it9.mimi.API.Model.ThongKeResponse;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.DoanhSoFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeController {
    public static void ShowThongKe(DoanhSoFragment view, String start, String end) {
        ThongKeRequest thongkeReq = new ThongKeRequest();
        thongkeReq.ngaybatdau = start;
        thongkeReq.ngayketthuc = end;
        RetrofitController.GetAPIService().thongkedoanhso(thongkeReq).enqueue(new Callback<ThongKeResponse>() {
            @Override
            public void onResponse(Call<ThongKeResponse> call, Response<ThongKeResponse> response) {
                if (response.isSuccessful()) {
                    ThongKeResponse thongkeRes = response.body();
                    if (thongkeRes != null && thongkeRes.isSuccess()) {
                        view.Show(thongkeRes.list);
                    } else {
                        view.ShowMessage("Show failed! " + thongkeRes.getMessage());
                    }
                } else {
                    view.ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<ThongKeResponse> call, Throwable t) {
                view.ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }
}
