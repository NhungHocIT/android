package com.it9.mimi.API.Controller;

import com.it9.mimi.API.Model.MatHangRequest;
import com.it9.mimi.API.Model.MatHangResponse;
import com.it9.mimi.API.Model.RetrofitController;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ui.MatHangActionable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatHangController {
    private static MatHangController instance;
    public static MatHangController Instance() {
        if (instance == null) {
            instance = new MatHangController();
        }
        return instance;
    }

    private MatHangActionable view;
    public void SetCurrentView(MatHangActionable view) {
        this.view = view;
    }
    public void ShowMessage(String message){
        if(view != null){
            view.ShowError(message);
        }
    }

    public void ShowAllMatHang(){
        RetrofitController.GetAPIService().mathang_show().enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccess(matHangResponse.getMathangList());
                    } else {
                        ShowMessage("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }
    public void Create(String tenMatHang, int gia, int maLoaiMatHang){
        MatHangRequest mh = new MatHangRequest();
        mh.gia = gia;
        mh.maloaimathang = maLoaiMatHang;
        mh.tenmathang = tenMatHang;

        RetrofitController.GetAPIService().mathang_create(mh).enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccess(new mathang());
                    } else {
                        ShowMessage("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void Read(String mamathang) {
        MatHangRequest mh = new MatHangRequest();
        mh.mamathang = mamathang;

        RetrofitController.GetAPIService().mathang_read(mh).enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        mathang mathang = matHangResponse.getMathangList().get(0);
                        view.ShowSuccess(mathang);
                    } else {
                        ShowMessage("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void ShowAllMatHang_Admin() {
        RetrofitController.GetAPIService().mathang_show_admin().enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccess(matHangResponse.getMathangList());
                    } else {
                        ShowMessage("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void ShowAllMatHangWithType(MatHangActionable view, int maloaimathang) {
        MatHangRequest mh = new MatHangRequest();
        mh.maloaimathang = maloaimathang;

        RetrofitController.GetAPIService().mathang_show_withtype(mh).enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        view.ShowSuccess(matHangResponse.getMathangList());
                    } else {
                        ShowMessage("Show failed! " + matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void UpdateTrangThai(String maMatHang, int trangthai) {
        MatHangRequest mh = new MatHangRequest();
        mh.mamathang = maMatHang;
        mh.trangthai = trangthai;

        RetrofitController.GetAPIService().mathang_update_trangthai(mh).enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        ShowMessage(matHangResponse.getMessage());
                    } else {
                        ShowMessage(matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }

    public void Update(String mamathang, String tenmathang, int gia, int maloaimathang) {
        MatHangRequest mh = new MatHangRequest();
        mh.mamathang = mamathang;
        mh.tenmathang = tenmathang;
        mh.gia = gia;
        mh.maloaimathang = maloaimathang;

        RetrofitController.GetAPIService().mathang_update(mh).enqueue(new Callback<MatHangResponse>() {
            @Override
            public void onResponse(Call<MatHangResponse> call, Response<MatHangResponse> response) {
                if (response.isSuccessful()) {
                    MatHangResponse matHangResponse = response.body();
                    if (matHangResponse != null && matHangResponse.isSuccess()) {
                        ShowMessage(matHangResponse.getMessage());
                    } else {
                        ShowMessage(matHangResponse.getMessage());
                    }
                } else {
                    ShowMessage("Lỗi server, vui lòng thử lại sau.");
                }
            }

            @Override
            public void onFailure(Call<MatHangResponse> call, Throwable t) {
                ShowMessage("Không thể kết nối đến server, vui lòng kiểm tra kết nối mạng.");
            }
        });
    }
}
