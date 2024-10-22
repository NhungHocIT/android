package com.it9.mimi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ChitietmathangActivity;
import com.it9.mimi.R;

import java.util.List;

public class GioiHangAdapter extends RecyclerView.Adapter<GioiHangAdapter.MathangViewHolder> {

    private List<mathang> mathangList;
    private OnQuantityChangeListener onQuantityChangeListener;

    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }

    public GioiHangAdapter(List<mathang> mathangList, OnQuantityChangeListener listener) {
        this.mathangList = mathangList;
        this.onQuantityChangeListener = listener;
    }

    @NonNull
    @Override
    public MathangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new MathangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MathangViewHolder holder, int position) {
        mathang item = mathangList.get(position);
        holder.tenMathang.setText(item.getTenmathang());
        holder.giaMathang.setText(String.valueOf(item.getGia()));
        holder.textViewQuantity.setText(String.valueOf(item.getSoluongtronggiohang()));

        // Tải ảnh từ Firebase Storage bằng Glide
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("uploads/mathang/" + item.getImageUrl());
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(holder.imageMathang.getContext())
                    .load(uri.toString())
                    .placeholder(R.drawable.kitty) // Ảnh chờ khi tải
                    .error(R.drawable.placeholder_image) // Ảnh hiển thị khi lỗi
                    .into(holder.imageMathang);
        }).addOnFailureListener(e -> {
            // Xử lý khi không thể tải ảnh
            holder.imageMathang.setImageResource(R.drawable.placeholder_image);
        });

        // Xử lý khi người dùng nhấn vào nút "Thêm" hoặc "Trừ"
        holder.buttonAdd.setOnClickListener(v -> {
            item.tangSoluong();
            holder.textViewQuantity.setText(String.valueOf(item.getSoluongtronggiohang()));
            onQuantityChangeListener.onQuantityChanged();
        });

        holder.buttonSubtract.setOnClickListener(v -> {
            item.giamSoluong();
            holder.textViewQuantity.setText(String.valueOf(item.getSoluongtronggiohang()));
            onQuantityChangeListener.onQuantityChanged();
        });

        // Mở ChitietmathangActivity khi nhấn vào item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ChitietmathangActivity.class);
            intent.putExtra("mathang", item);
            v.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return mathangList.size();
    }

    public static class MathangViewHolder extends RecyclerView.ViewHolder {
        TextView tenMathang, giaMathang, textViewQuantity;
        ImageView imageMathang;
        ImageButton buttonAdd, buttonSubtract;

        public MathangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMathang = itemView.findViewById(R.id.tenMathang);
            giaMathang = itemView.findViewById(R.id.giaMathang);
            imageMathang = itemView.findViewById(R.id.hinhAnhMathang);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
            buttonSubtract = itemView.findViewById(R.id.buttonSubtract);
        }
    }
}
