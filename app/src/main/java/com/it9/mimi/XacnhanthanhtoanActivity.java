package com.it9.mimi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Controller.ChiTietDonHangController;
import com.it9.mimi.API.Model.SanPham;
import com.it9.mimi.API.Model.chitietdonhang;
import com.it9.mimi.API.Model.donhang;
import com.it9.mimi.R;
import com.it9.mimi.adapter.SanPhamAdapter;
import com.it9.mimi.ui.DonHangActionable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class XacnhanthanhtoanActivity extends AppCompatActivity implements DonHangActionable {

    private RecyclerView recyclerViewSanPham;
    private SanPhamAdapter sanPhamAdapter;
    private TextView tvTotal, tvItemTotal;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_xacnhanthanhtoan);
        tvItemTotal = findViewById(R.id.tvItemTotal);
        tvTotal = findViewById(R.id.tvTotal);
        Button btn = findViewById(R.id.btnThanhToan);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(XacnhanthanhtoanActivity.this, TrangChuActivity.class);
            startActivity(intent);
        });

        recyclerViewSanPham = findViewById(R.id.recyclerView);
        recyclerViewSanPham.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String mamathang = intent.getStringExtra("mamathang");
        ChiTietDonHangController.Instance().SetCurrentView(this);
        ChiTietDonHangController.Instance().Show(mamathang);
    }

    @Override
    public void ShowSuccessHD(donhang donhang) {

    }

    @Override
    public void ShowSuccessHD(List<donhang> donhangList) {

    }

    @Override
    public void ShowError(String mess) {

    }

    @Override
    public void ShowChiTietDonHang(List<chitietdonhang> chitietdonhangList) {
        double tong = 0;
        int counter = 0;
        List<SanPham> sanPhamList = new ArrayList<>();
        for (chitietdonhang chitiet: chitietdonhangList) {
            sanPhamList.add(new SanPham(chitiet.tenmathang, chitiet.soluong, chitiet.gia));
            tong += chitiet.soluong * chitiet.gia;
            counter += chitiet.soluong;
        }
        
        String formattedTotal = String.format(Locale.getDefault(), "%,.0fđ", tong);
        formattedTotal = counter + " Món: " + formattedTotal;
        tvItemTotal.setText(formattedTotal);


        tong += 40000;
        formattedTotal = String.format(Locale.getDefault(), "%,.0f đ", tong);

        tvTotal.setText("Tổng tiền: " + formattedTotal);

        sanPhamAdapter = new SanPhamAdapter(sanPhamList);
        recyclerViewSanPham.setAdapter(sanPhamAdapter);
    }
}
