package com.it9.mimi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Model.SanPham;
import com.it9.mimi.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    private List<SanPham> sanPhamList;

    public SanPhamAdapter(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham_hoadon, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        holder.tvSoLuongSanPham.setText(String.valueOf(sanPham.getSoLuong()));
        holder.tvGiaSanPham.setText(String.format("%,dđ", sanPham.getGia()));
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvSoLuongSanPham, tvGiaSanPham;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tenMathang);
            tvSoLuongSanPham = itemView.findViewById(R.id.giaMathang);
            tvGiaSanPham = itemView.findViewById(R.id.textViewQuantity);
        }
    }
}

