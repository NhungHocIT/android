package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.it9.mimi.API.Controller.AccountController;
import com.it9.mimi.API.Model.Account;
import com.it9.mimi.ui.AccountActionable;

import java.util.List;

public class login extends AppCompatActivity implements AccountActionable
{
    private EditText edtPhone, edtPassword;
    private Button btnLogin, btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AccountController.Instance().SetCurrentView(this);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            String phone = edtPhone.getText().toString();
            String password = edtPassword.getText().toString();
            AccountController.Instance().login(phone, password);
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, signUp.class);
            startActivity(intent);
        });
    }


    @Override
    public void ShowSuccess(Account account) {
        Toast.makeText(this, "Đăng nhập thành công! Xin chào " + account.getHovaten(), Toast.LENGTH_SHORT).show();
        Intent intent = null;
        if(account.getLevel() == 1){
            intent = new Intent(login.this, MainActivity.class);
        }else{
            intent = new Intent(login.this, TrangChuActivity.class);
        }

        //intent.putExtra("account", account);

        startActivity(intent);
        finish();
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSuccess(List<Account> taikhoanList) {

    }
}