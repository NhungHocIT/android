package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.it9.mimi.API.Controller.MatHangController;
import com.it9.mimi.adapter.ProductAdapter;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ui.MatHangActionable;

import java.util.ArrayList;
import java.util.List;

public class dsdoanActivity extends AppCompatActivity implements MatHangActionable {
    GridView productGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doan);
        getSupportActionBar().hide();


        int maloaimathang = getIntent().getIntExtra("maloaimathang", 1);


        // Tìm ImageView với ID là cartIcon
        ImageView cartIcon = findViewById(R.id.cartIcon);

        // Thiết lập sự kiện onClick cho ImageView
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity giỏ hàng
                Intent intent = new Intent(dsdoanActivity.this, GiohangActivity.class);
                startActivity(intent);
            }
        });


        productGrid = findViewById(R.id.productGrid);
        MatHangController.Instance().ShowAllMatHangWithType(this, maloaimathang);

    }

    @Override
    public void ShowSuccess(mathang mathang) {

    }

    @Override
    public void ShowSuccess(List<mathang> mathangList) {
        List<mathang> productList = new ArrayList<>();
        productList.add(new mathang("Bánh tráng nướng", 150000, "3"));
        productList.add(new mathang("Bánh xèo", 150000, "4"));
        productList.add(new mathang("Chả mực", 100000, "5"));
        productList.add(new mathang("Sushi", 150000, "6"));
        productList.add(new mathang("Takoyaki", 100000, "7"));
        productList.add(new mathang("Beefsteak", 150000, "8"));

        ProductAdapter adapter = new ProductAdapter(this, mathangList);
        productGrid.setAdapter(adapter);
    }

    @Override
    public void ShowError(String mess) {

    }
}