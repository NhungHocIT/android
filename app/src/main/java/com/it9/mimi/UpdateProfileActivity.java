package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class UpdateProfileActivity extends AppCompatActivity implements AccountActionable {
    private ImageButton btnBack;
    private Button btnSave;
    private EditText edtName, edtPhone, edtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_updateprofile);
        getSupportActionBar().hide();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Account account = AccountController.Instance().GetAccount();
        if(account == null){
            finish();
            return;
        }

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtaddress);
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.save_button);

        edtName.setText(account.getHovaten());
        edtPhone.setText(account.getSodienthoai());
        edtAddress.setText(account.getDiachi());

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnSave.setOnClickListener(v -> {
            SaveChange();
        });
    }

    private void SaveChange() {
        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();

        if(name.isEmpty() || address.isEmpty()){
            Toast.makeText(this, "Không được để trống thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        Account account = AccountController.Instance().GetAccount();
        if(account == null){
            Toast.makeText(this, "Không được truy cập được thông tin!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if(account.getHovaten().equals(name) && account.getDiachi().equals(address)){
            Toast.makeText(this, "Bạn chưa thực hiện thay đổi!", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountController.Instance().SetCurrentView(this);
        AccountController.Instance().changeProfile(name, address);
    }

    @Override
    public void ShowSuccess(Account account) {
        Toast.makeText(this, "Thay đổi thông tin thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, login.class);
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