package com.it9.mimi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Model.loaimathang;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.R;

import java.util.List;

public class LoaiMatHangChoAdminAdapter extends RecyclerView.Adapter<LoaiMatHangChoAdminAdapter.LoaiMatHangViewHolder>{
    private List<loaimathang> mathangList;

    public LoaiMatHangChoAdminAdapter(List<loaimathang> mathangList) {
        this.mathangList = mathangList;
    }

    @NonNull
    @Override
    public LoaiMatHangChoAdminAdapter.LoaiMatHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loai_mat_hang_admin, parent, false);
        return new LoaiMatHangChoAdminAdapter.LoaiMatHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiMatHangChoAdminAdapter.LoaiMatHangViewHolder holder, int position) {
        loaimathang loaiMatHang = mathangList.get(position);
        holder.tenMatHang.setText(loaiMatHang.tenloaimathang);
    }

    @Override
    public int getItemCount() {
        return mathangList.size();
    }

    public static class LoaiMatHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenMatHang, giaMatHang;

        public LoaiMatHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMatHang = itemView.findViewById(R.id.tenMatHang);
        }
    }
}
