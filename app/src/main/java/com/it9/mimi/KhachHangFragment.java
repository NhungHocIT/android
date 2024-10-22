package com.it9.mimi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.it9.mimi.API.Controller.AccountController;
import com.it9.mimi.API.Model.Account;
import com.it9.mimi.ui.AccountActionable;
import com.it9.mimi.adapter.KhachHangChoAdminAdapter;

import java.util.ArrayList;
import java.util.List;


public class KhachHangFragment extends Fragment implements AccountActionable {

    private RecyclerView recyclerViewKhachHang;
    private KhachHangChoAdminAdapter khachHangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        AccountController.Instance().SetCurrentView(this);
        // Khởi tạo RecyclerView
        recyclerViewKhachHang = view.findViewById(R.id.recyclerViewKhachHang);
        recyclerViewKhachHang.setLayoutManager(new LinearLayoutManager(getContext()));
        AccountController.Instance().show();

        return view;
    }

    @Override
    public void ShowSuccess(Account account) {
        Toast.makeText(getContext(), "TODO : SHOW SUCCESS KH FRAGMENT", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowSuccess(List<Account> taikhoanList) {
        if(taikhoanList == null){
            ShowError("taikhoanList null");
            return;
        }

        khachHangAdapter = new KhachHangChoAdminAdapter(taikhoanList);
        recyclerViewKhachHang.setAdapter(khachHangAdapter);
    }
}