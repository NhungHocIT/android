package com.it9.mimi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.R;
import com.it9.mimi.UploadimagemathangActivity;

import java.util.List;

public class MatHangChoAdminAdapter extends RecyclerView.Adapter<MatHangChoAdminAdapter.MatHangViewHolder> {

    private List<mathang> matHangList;
    private OnMatHangClickListener onMatHangClickListener;
    public MatHangChoAdminAdapter(List<mathang> matHangList, OnMatHangClickListener listener) {
        this.matHangList = matHangList;
        this.onMatHangClickListener = listener;
    }
    @NonNull
    @Override
    public MatHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mat_hang_admin, parent, false);
        return new MatHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatHangViewHolder holder, int position) {
        mathang matHang = matHangList.get(position);
        holder.tenMatHang.setText(matHang.getTenmathang());
        holder.giaMatHang.setText(String.valueOf(matHang.getGia()));
        holder.itemView.setOnClickListener(v -> onMatHangClickListener.onMatHangClick(matHang));
    }

    @Override
    public int getItemCount() {
        return matHangList.size();
    }

    public class MatHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenMatHang, giaMatHang;

        public MatHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMatHang = itemView.findViewById(R.id.tenMatHang);
            giaMatHang = itemView.findViewById(R.id.giaMatHang);
        }
    } 

    public interface OnMatHangClickListener {
        void onMatHangClick(mathang matHang);
    }
}