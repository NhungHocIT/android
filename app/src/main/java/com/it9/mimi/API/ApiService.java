package com.it9.mimi.API;

import com.it9.mimi.API.Model.AccountRequest;
import com.it9.mimi.API.Model.AccountResponse;

import com.it9.mimi.API.Model.ChiTietDonHangRequest;
import com.it9.mimi.API.Model.DonHangRequest;
import com.it9.mimi.API.Model.DonHangResponse;
import com.it9.mimi.API.Model.GioHangRequest;
import com.it9.mimi.API.Model.GioHangResponse;

import com.it9.mimi.API.Model.LoaiMatHangResponse;

import com.it9.mimi.API.Model.MatHangRequest;
import com.it9.mimi.API.Model.MatHangResponse;
import com.it9.mimi.API.Model.ThongKeRequest;
import com.it9.mimi.API.Model.ThongKeResponse;
import com.it9.mimi.API.Model.chitietdonhangRespone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("more/login.php")
    Call<AccountResponse> login(@Body AccountRequest accountRequest);

    @POST("more/signup.php")
    Call<AccountResponse> signUp(@Body AccountRequest accountRequest);

    @POST("more/thongkedoanhso.php")
    Call<ThongKeResponse> thongkedoanhso(@Body ThongKeRequest thongKeRequest);

    @POST("taikhoan/update.php")
    Call<AccountResponse> taikhoan_update(@Body AccountRequest accountRequest);

    @POST("taikhoan/show.php")
    Call<AccountResponse> taikhoan_show();

    @POST("mathang/show.php")
    Call<MatHangResponse> mathang_show();

    @POST("mathang/show_admin.php")
    Call<MatHangResponse> mathang_show_admin();

    @POST("mathang/create.php")
    Call<MatHangResponse> mathang_create(@Body MatHangRequest matHangRequest);

    @POST("mathang/read.php")
    Call<MatHangResponse> mathang_read(@Body MatHangRequest matHangRequest);

    @POST("mathang/update_trangthai.php")
    Call<MatHangResponse> mathang_update_trangthai(@Body MatHangRequest mh);

    @POST("mathang/update.php")
    Call<MatHangResponse> mathang_update(@Body MatHangRequest mh);

    @POST("loaimathang/show.php")
    Call<LoaiMatHangResponse> loaimathang_show();

    @POST("giohang/show.php")
    Call<GioHangResponse> giohang_show(@Body GioHangRequest gioHangRequest);

    @POST("giohang/update.php")
    Call<GioHangResponse> giohang_update(@Body GioHangRequest gioHangRequest);

    @POST("giohang/search.php")
    Call<GioHangResponse> giohang_find(@Body GioHangRequest gioHangRequest);

    @POST("giohang/delete.php")
    Call<GioHangResponse> giohang_delete(@Body GioHangRequest gioHangRequest);

    @POST("giohang/create.php")
    Call<GioHangResponse> giohang_create(@Body GioHangRequest gioHangRequest);

    @POST("donhang/create.php")
    Call<DonHangResponse> donhang_create(@Body DonHangRequest dhRequest);

    @POST("chitietdonhang/create.php")
    Call<GioHangResponse> chitietdonhang_create(@Body ChiTietDonHangRequest dhRequest);

    @POST("chitietdonhang/show_bymadonhang.php")
    Call<chitietdonhangRespone> chitietdonhang_show(@Body ChiTietDonHangRequest dhRequest);

    @POST("mathang/show_withtype.php")
    Call<MatHangResponse> mathang_show_withtype(@Body MatHangRequest mh);
}
