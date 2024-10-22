package com.it9.mimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it9.mimi.API.Controller.LoaiMatHangController;
import com.it9.mimi.API.Model.loaimathang;

import com.it9.mimi.adapter.LoaiMatHangChoAdminAdapter;
import com.it9.mimi.ui.LoaiMatHangActionable;

import java.util.ArrayList;
import java.util.List;


public class LoaiMatHangFragment extends Fragment implements LoaiMatHangActionable {
    private RecyclerView recyclerViewLoaiMatHang;
    private LoaiMatHangChoAdminAdapter loaiMatHangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_mat_hang, container, false);

        // Khởi tạo RecyclerView
        recyclerViewLoaiMatHang = view.findViewById(R.id.recyclerViewLoaiMatHang);
        recyclerViewLoaiMatHang.setLayoutManager(new LinearLayoutManager(getContext()));

        LoaiMatHangController.Instance().SetCurrentView(this);
        LoaiMatHangController.Instance().Show();

        return view;
    }

    @Override
    public void ShowSuccessLMH(List<loaimathang> mathangList) {
        if (mathangList == null ){
            ShowError("mathangList null");
            return;
        }

        loaiMatHangAdapter = new LoaiMatHangChoAdminAdapter(mathangList);
        recyclerViewLoaiMatHang.setAdapter(loaiMatHangAdapter);
    }

    @Override
    public void ShowError(String messs) {

    }
}