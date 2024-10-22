package com.it9.mimi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.it9.mimi.ChitietmathangActivity;
import com.it9.mimi.R;
import com.it9.mimi.API.Model.mathang;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<mathang> productList;

    public ProductAdapter(Context context, List<mathang> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
            holder = new ViewHolder();
            holder.productImage = convertView.findViewById(R.id.productImage);
            holder.productName = convertView.findViewById(R.id.productName);
            holder.productPrice = convertView.findViewById(R.id.productPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Lấy đối tượng sản phẩm từ danh sách
        mathang product = (mathang) getItem(position);

        // Hiển thị hình ảnh sản phẩm từ Firebase Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("uploads/mathang/" + product.getMamathang());
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(context)
                    .load(uri.toString())  // Sử dụng URI từ Firebase
                    .placeholder(R.drawable.kitty)
                    .error(R.drawable.placeholder_image)
                    .into(holder.productImage);
        });

        // Đặt tên và giá sản phẩm vào TextView
        holder.productName.setText(product.getTenmathang());
        holder.productPrice.setText(String.format("%sđ", product.getGia()));

        // Thiết lập sự kiện click vào mỗi mục sản phẩm
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChitietmathangActivity.class);
            intent.putExtra("mathang", product);  // Truyền đối tượng mathang sang Activity khác
            context.startActivity(intent);
        });

        return convertView;
    }

    // Sử dụng ViewHolder pattern để cải thiện hiệu suất
    private static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
    }
}
