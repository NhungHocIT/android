package com.it9.mimi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Model.Account;
import com.it9.mimi.R;

import java.util.List;

public class KhachHangChoAdminAdapter extends RecyclerView.Adapter<KhachHangChoAdminAdapter.KhachHangViewHolder> {

    private List<Account> accountList;

    public KhachHangChoAdminAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public KhachHangChoAdminAdapter.KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_khach_hang_admin, parent, false);
        return new KhachHangChoAdminAdapter.KhachHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangChoAdminAdapter.KhachHangViewHolder holder, int position) {
        Account khachHang = accountList.get(position);
        holder.tenMatHang.setText(khachHang.getSodienthoai());
        holder.giaMatHang.setText(String.valueOf(50));
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class KhachHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenMatHang, giaMatHang;

        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMatHang = itemView.findViewById(R.id.tenMatHang);
            giaMatHang = itemView.findViewById(R.id.giaMatHang);
        }
    }
}