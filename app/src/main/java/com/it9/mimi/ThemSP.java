package com.it9.mimi;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.it9.mimi.API.Controller.LoaiMatHangController;
import com.it9.mimi.API.Controller.MatHangController;
import com.it9.mimi.API.Model.loaimathang;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.databinding.ActivityThemSpBinding;
import com.it9.mimi.ui.LoaiMatHangActionable;
import com.it9.mimi.ui.MatHangActionable;

import java.util.ArrayList;
import java.util.List;

public class ThemSP extends AppCompatActivity implements MatHangActionable, LoaiMatHangActionable {
    Spinner spinner;
    int loai = 0;
    ActivityThemSpBinding binding;
    TextView txtTenMH, txtGia;
    List<loaimathang> loaimathangList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThemSpBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();
        setContentView(binding.getRoot());
        initView();

        MatHangController.Instance().SetCurrentView(this);
        LoaiMatHangController.Instance().SetCurrentView(this);
        LoaiMatHangController.Instance().Show();
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

    private void initView() {
        spinner = findViewById(R.id.spinner_loai);
        txtTenMH = findViewById(R.id.tensp);
        txtGia = findViewById(R.id.giatien);

        Button btnThem = findViewById(R.id.btn_them);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtTenMH.getText().toString().isEmpty() || txtGia.getText().toString().isEmpty()) {
                    ShowError("Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                MatHangController.Instance().Create(
                        txtTenMH.getText().toString(),
                        Integer.parseInt(txtGia.getText().toString()),
                        loaimathangList.get(loai).maloaimathang);
            }
        });
    }

    // Implementation for MatHangActionable
    @Override
    public void ShowSuccess(mathang mathang) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void ShowSuccess(List<mathang> mathangList) {
        Toast.makeText(this, "TODO SUCCESS MAT HANG LIST", Toast.LENGTH_SHORT).show();
    }

    // Implementation for LoaiMatHangActionable
    @Override
    public void ShowSuccessLMH(List<loaimathang> loaimathangList) {
        initData(loaimathangList);
    }

    // Shared implementation for ShowError
    @Override
    public void ShowError(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}