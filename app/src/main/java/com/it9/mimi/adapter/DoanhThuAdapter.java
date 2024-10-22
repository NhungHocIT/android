package com.it9.mimi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.it9.mimi.API.Model.DoanhThu;
import com.it9.mimi.R;

import java.util.List;

public class DoanhThuAdapter extends RecyclerView.Adapter<DoanhThuAdapter.DoanhThuViewHolder> {

    private List<DoanhThu> doanhThuList;

    public DoanhThuAdapter(List<DoanhThu> doanhThuList) {
        this.doanhThuList = doanhThuList;
    }

    @NonNull
    @Override
    public DoanhThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doanhthu, parent, false);
        return new DoanhThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoanhThuViewHolder holder, int position) {
        DoanhThu doanhThu = doanhThuList.get(position);
        holder.itemName.setText(doanhThu.getTenMatHang());
        holder.itemPrice.setText(String.valueOf(doanhThu.getGia()));
        holder.itemRevenue.setText(String.valueOf(doanhThu.getDoanhThu()));
    }

    @Override
    public int getItemCount() {
        return doanhThuList.size();
    }

    public static class DoanhThuViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemRevenue;

        public DoanhThuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemRevenue = itemView.findViewById(R.id.item_revenue);
        }
    }
}

