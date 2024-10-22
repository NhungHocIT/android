package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.it9.mimi.API.Controller.MatHangController;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ui.MatHangActionable;
import com.it9.mimi.adapter.MatHangChoAdminAdapter;

import java.util.List;

public class MatHangFragment extends Fragment  implements MatHangActionable {
    private static final int REQUEST_CODE_THEM_SP = 100;

    private RecyclerView recyclerViewMatHang;
    private MatHangChoAdminAdapter matHangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mat_hang, container, false);

        recyclerViewMatHang = view.findViewById(R.id.recyclerViewMatHang);
        recyclerViewMatHang.setLayoutManager(new LinearLayoutManager(getContext()));

        MatHangController.Instance().SetCurrentView(this);
        MatHangController.Instance().ShowAllMatHang_Admin();
        Button btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThemSP.class);
                startActivityForResult(intent, REQUEST_CODE_THEM_SP);
                //startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        MatHangController.Instance().SetCurrentView(this);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_THEM_SP && resultCode == getActivity().RESULT_OK) {
            MatHangController.Instance().ShowAllMatHang();
        }
    }


    @Override
    public void ShowSuccess(mathang mathang) {

    }

    @Override
    public void ShowSuccess(List<mathang> mathangList) {
        matHangAdapter = new MatHangChoAdminAdapter(mathangList, matHang -> {
            Intent intent = new Intent(getContext(), UploadimagemathangActivity.class);
            intent.putExtra("tenMatHang", matHang.getTenmathang());
            intent.putExtra("giaMatHang", matHang.getGia());
            intent.putExtra("maMatHang", matHang.getMamathang());
            startActivityForResult(intent, REQUEST_CODE_THEM_SP);
        });

        recyclerViewMatHang.setAdapter(matHangAdapter);
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }
}
