package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.it9.mimi.API.Controller.AccountController;
import com.it9.mimi.API.Model.Account;
import com.it9.mimi.ui.AccountActionable;

import java.util.List;

public class signUp extends AppCompatActivity implements AccountActionable {

    private Button btnBack, btnTrySignUp;
    private EditText edithovaten, ediSDT, edtPassword, edtRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sinup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edithovaten = findViewById(R.id.edthovaten);
        ediSDT = findViewById(R.id.ediSDT);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnTrySignUp = findViewById(R.id.btnTrySignUp);
        btnTrySignUp.setOnClickListener(v -> {
            DoTrySignUp();
        });
    }


    private void DoTrySignUp(){
        String hoTen = edithovaten.getText().toString();
        String sdt = ediSDT.getText().toString();
        String matKhau = edtPassword.getText().toString();
        String nhapLaiMatKhau = edtRePassword.getText().toString();

        if(hoTen.isEmpty() || sdt.isEmpty() || matKhau.isEmpty() || nhapLaiMatKhau.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!matKhau.equals(nhapLaiMatKhau)){
            Toast.makeText(this, "Mật khẩu không trùng khớp ", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountController.Instance().SetCurrentView(this);
        AccountController.Instance().signUp(sdt, matKhau, hoTen);
    }

    @Override
    public void ShowSuccess(Account account) {
        Toast.makeText(this, "Đăng nhập thành công! Xin chào " + account.getHovaten(), Toast.LENGTH_SHORT).show();
        Intent intent = null;
        intent = new Intent(signUp.this, TrangChuActivity.class);

        // Truyền dữ liệu user qua Intent
        intent.putExtra("account", account);

        startActivity(intent);
        finish(); // Đóng LoginActivity_QUAN sau khi chuyển sang MainActivity
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSuccess(List<Account> taikhoanList) {

    }
}