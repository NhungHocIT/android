package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Controller.GioHangController;
import com.it9.mimi.API.Controller.DonHangController;
import com.it9.mimi.API.Model.GioHang;
import com.it9.mimi.API.Model.GioHangRequest;
import com.it9.mimi.API.Model.chitietdonhang;
import com.it9.mimi.API.Model.donhang;
import com.it9.mimi.ui.DonHangActionable;
import com.it9.mimi.ui.GioHangActionable;
import com.it9.mimi.API.Model.GioHangAdapter;
import com.it9.mimi.ui.OnUpdateListener;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GiohangActivity extends AppCompatActivity implements GioHangActionable, DonHangActionable {

    private TextView tamtinh;
    private RecyclerView recyclerView;
    private TextView somathang;
    private GioHangAdapter adapter;
    private Button buttonOrder;
    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        getSupportActionBar().hide();
        GioHangController.Instance().SetCurrentActionable(this);
        DonHangController.Instance().SetCurrentView(this);
        recyclerView = findViewById(R.id.recyclerViewMathang);
        tamtinh = findViewById(R.id.textViewPrice);
        somathang = findViewById(R.id.textViewCartHeader);
        buttonOrder = findViewById(R.id.buttonOrder);

        buttonOrder.setOnClickListener(v -> {
            if(isBusy) return;
            isBusy = true;
            DonHangController.Instance().Order(adapter.getItems(), this::ShowXacNhan);
        });

        GioHangController.Instance().ShowAllGioHang();
    }

    private void ShowXacNhan(String mamathang){
        Intent intent = new Intent(GiohangActivity.this, XacnhanthanhtoanActivity.class);
        intent.putExtra("mamathang", mamathang);
        startActivity(intent);
    }

    @Override
    public void ShowSuccessGH(GioHangRequest giohang) {
        adapter.UpdateGioHang(giohang);
    }
    @Override
    public void ShowSuccessGH(List<GioHang> giohangList) {
        ShowError("Cập nhật giỏ hàng");
        adapter = new GioHangAdapter(giohangList, this, new OnUpdateListener() {
            @Override
            public void onUpdate(List<GioHang> mhList) {
                tinhTongTien(mhList);
            }
        });

        tinhTongTien(giohangList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        somathang.setText("Giỏ hàng (" + giohangList.size() + ")");

        recyclerView.setAdapter(adapter);
        isBusy = false;
    }

    @Override
    public void ShowSuccessHD(donhang donhang) {
        isBusy = false;
    }

    @Override
    public void ShowSuccessHD(List<donhang> donhangList) {
        isBusy = false;
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
        isBusy = false;
    }

    @Override
    public void ShowChiTietDonHang(List<chitietdonhang> chitietdonhangList) {

    }

    @Override
    public List<GioHang> getGioHangList() {
        return adapter.getItems();
    }

    private void tinhTongTien(List<GioHang> giohangList) {
        double tongTien = 0;
        for (GioHang item : giohangList) {
            tongTien += item.getGia() * item.getSoluong();
        }
        tamtinh.setText(String.format(Locale.getDefault(), "%,.0fđ", tongTien));
    }

    @Override
    public void onBackPressed() {
        if (isBusy) {
            Toast.makeText(this, "Please wait, action in progress...", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}