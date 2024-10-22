package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.it9.mimi.API.Controller.GioHangController;
import com.it9.mimi.API.Controller.MatHangController;
import com.it9.mimi.API.Model.GioHang;
import com.it9.mimi.API.Model.GioHangRequest;
import com.it9.mimi.ui.GioHangActionable;
import com.it9.mimi.ui.MatHangActionable;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.it9.mimi.API.Model.mathang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChitietmathangActivity extends AppCompatActivity implements MatHangActionable, GioHangActionable {

    private TextView textViewProductName, textViewProductPrice;
    private ImageView imageViewProductImage, cartIcon;
    private Button buttonAddToCart;
    private mathang mathang;
    private String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietmathang);
        getSupportActionBar().hide();

        textViewProductName = findViewById(R.id.textViewProductName);
        textViewProductPrice = findViewById(R.id.textViewProductPrice);
        imageViewProductImage = findViewById(R.id.imageViewProductImage);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        cartIcon = findViewById(R.id.cartIcon);

        // Cài đặt sự kiện cho icon giỏ hàng
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ChitietmathangActivity.this, GiohangActivity.class);
            startActivity(intent);
        });

        // Lấy đối tượng mathang từ Intent
        mathang = (mathang) getIntent().getSerializableExtra("mathang");
        if (mathang == null) {
            finish();
            return;
        }

        // Hiển thị thông tin mặt hàng
        textViewProductName.setText(mathang.getTenmathang());
        textViewProductPrice.setText(String.format("%sđ", mathang.getGia()));

        // Hiển thị hình ảnh mặt hàng
        displayImage();

        buttonAddToCart.setOnClickListener(v -> {
            GioHangController.Instance().Find(mathang.getMamathang(),
                    () -> {
                        Toast.makeText(this, "Đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                    },
                    () -> {
                        GioHangController.Instance().SetCurrentActionable(this);
                        GioHangController.Instance().Create(mathang.getMamathang(), 1);
                    });
        });
    }


    private void displayImage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        // Đường dẫn tới file ảnh trên Firebase Storage
        StorageReference fileReference = storageReference.child("uploads/mathang/" + mathang.getMamathang());

        // Kiểm tra sự tồn tại của ảnh
        fileReference.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    // Nếu ảnh tồn tại, sử dụng Glide để hiển thị
                    Glide.with(ChitietmathangActivity.this)
                            .load(uri)
                            .into(imageViewProductImage);
                })
                .addOnFailureListener(e -> {
                    // Nếu không có ảnh, hiển thị thông báo "Chưa có ảnh"
                    imageViewProductImage.setImageResource(R.drawable.placeholder_image); // Bạn cần tạo ảnh placeholder trong thư mục drawable
                    Toast.makeText(ChitietmathangActivity.this, "Chưa có ảnh, thêm ảnh ngay", Toast.LENGTH_SHORT).show();
                });
    }


    @Override
    public void ShowSuccess(mathang mathang) {
        if (mathang != null) {
            textViewProductName.setText(mathang.getTenmathang());
            textViewProductPrice.setText(String.format("%sđ", mathang.getGia()));
            //imageViewProductImage.setImageResource(mathang.getImageResource());
            // TODO LOAD IMAGE
        }
    }

    @Override
    public void ShowSuccess(List<mathang> mathangList) {
        Toast.makeText(this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSuccessGH(GioHangRequest giohang) {
        Toast.makeText(this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSuccessGH(List<GioHang> giohangList) {
        Toast.makeText(this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<GioHang> getGioHangList() {
        return new ArrayList<>();
    }
}
