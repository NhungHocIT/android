package com.it9.mimi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.Switch;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.it9.mimi.API.Controller.LoaiMatHangController;
import com.it9.mimi.API.Controller.MatHangController;
import com.it9.mimi.API.Model.loaimathang;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ui.LoaiMatHangActionable;
import com.it9.mimi.ui.MatHangActionable;

import java.util.ArrayList;
import java.util.List;

public class UploadimagemathangActivity extends AppCompatActivity implements MatHangActionable, LoaiMatHangActionable {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private String imageName;
    private Uri imageUri;
    private Switch switchStatus;
    private List<loaimathang> loaimathangList;
    private Spinner spinner;
    private int loai;
    private EditText gia, tenmathang;
    private mathang mathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimagemathang);

        Intent intent = getIntent();
        this.imageName = intent.getStringExtra("maMatHang"); // Đặt tên ảnh theo mã mặt hàng
        if(this.imageName == null){
            ShowError("missing mã mặt hàng");
            finish();
            return;
        }

        getSupportActionBar().setTitle("Thông tin mặt hàng");
        imageView = findViewById(R.id.imageView);
        tenmathang = findViewById(R.id.productName);
        gia = findViewById(R.id.gia);
        spinner = findViewById(R.id.spinner_loai);

        Button buttonChooseImage = findViewById(R.id.button_choose_image);
        buttonChooseImage.setOnClickListener(v -> openFileChooser());

        Button buttonUpload = findViewById(R.id.button_upload);
        buttonUpload.setOnClickListener(v -> uploadImage());

        switchStatus = findViewById(R.id.switch_status);
        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                MatHangController.Instance().UpdateTrangThai(this.imageName, 1);
            } else {
                MatHangController.Instance().UpdateTrangThai(this.imageName, 0);
            }
        });

        Button btnSave = findViewById(R.id.button_save);
        btnSave.setOnClickListener(v -> {
            CheckMatHang();
        });

        MatHangController.Instance().SetCurrentView(this);
        LoaiMatHangController.Instance().SetCurrentView(this);
        LoaiMatHangController.Instance().Show();
        displayImage();
    }

    private void CheckMatHang() {
        if (mathang.getTenmathang().isEmpty()){
            ShowError("Tên mặt hàng không được để trống");
            return;
        }

        if(gia.getText().toString().isEmpty()){
            ShowError("Giá không được để trống");
            return;
        }

        if(!mathang.getTenmathang().equals(tenmathang.getText().toString())
                || !gia.getText().toString().equals(String.valueOf(mathang.getGia()))
                || loai != mathang.getMaloaimathang())
        {
            MatHangController.Instance().Update(mathang.getMamathang(), tenmathang.getText().toString(), Integer.parseInt(gia.getText().toString()), loai);
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result_key", 100);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            // Hiển thị ảnh đã chọn trước khi upload
            imageView.setImageURI(imageUri);  // Hiển thị ảnh ngay lập tức trong ImageView
        }
    }

    private void uploadImage() {
        if (imageUri != null && !imageName.isEmpty()) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();

            // Đặt tên ảnh là tên người dùng đã nhập
            StorageReference fileReference = storageReference.child("uploads/mathang/" + imageName);

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Sau khi upload thành công, lấy URL của ảnh
                        fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            // Dùng Glide để hiển thị ảnh từ URL
                            Glide.with(UploadimagemathangActivity.this)
                                    .load(uri)
                                    .into(imageView);

                            Toast.makeText(UploadimagemathangActivity.this, "Upload thành công", Toast.LENGTH_LONG).show();

                            // Reset lại ImageView và EditText
                            imageUri = null; // Reset URI của ảnh
                        });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(UploadimagemathangActivity.this, "Upload thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Chưa chọn ảnh hoặc tên ảnh trống", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayImage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        // Đường dẫn tới file ảnh trên Firebase Storage
        StorageReference fileReference = storageReference.child("uploads/mathang/" + imageName);

        // Kiểm tra sự tồn tại của ảnh
        fileReference.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    // Nếu ảnh tồn tại, sử dụng Glide để hiển thị
                    Glide.with(UploadimagemathangActivity.this)
                            .load(uri)
                            .into(imageView);
                })
                .addOnFailureListener(e -> {
                    // Nếu không có ảnh, hiển thị thông báo "Chưa có ảnh"
                    imageView.setImageResource(R.drawable.placeholder_image); // Bạn cần tạo ảnh placeholder trong thư mục drawable
                    Toast.makeText(UploadimagemathangActivity.this, "Chưa có ảnh, thêm ảnh ngay", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void ShowSuccess(mathang mathang) {
        if(mathang == null){
            finish();
            return;
        }
        this.mathang  = mathang;
        switchStatus.setChecked(mathang.getTrangthai() == 1);

        loai = mathang.getMaloaimathang();
        spinner.setSelection(loai);

        tenmathang.setText(mathang.getTenmathang());
        gia.setText(String.valueOf(mathang.getGia()));
    }

    @Override
    public void ShowSuccess(List<mathang> mathangList) {

    }

    @Override
    public void ShowSuccessLMH(List<loaimathang> mathangList) {
        initData(mathangList);

        Intent intent = getIntent();
        String maMatHang = intent.getStringExtra("maMatHang");
        MatHangController.Instance().Read(maMatHang);
    }

    private void initData(List<loaimathang> loaimathangList) {
        this.loaimathangList  = loaimathangList;
        List<String> stringList = new ArrayList<>();
        for (loaimathang lmh : loaimathangList) {
            stringList.add(lmh.tenloaimathang);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,stringList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loai = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                loai = 0;
            }
        });
    }

    @Override
    public void ShowError(String mess) {

    }
}
