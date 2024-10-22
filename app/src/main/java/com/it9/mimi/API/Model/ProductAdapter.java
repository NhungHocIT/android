package com.it9.mimi.API.Model;

// Java
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it9.mimi.ChitietmathangActivity;
import com.it9.mimi.R;

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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        }

        mathang product = productList.get(position);

        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        // Thiết lập hình ảnh và thông tin sản phẩm
        // productImage.setImageResource(product.getImageResource()); // Nếu bạn dùng hình ảnh từ drawable
        // TODO LOAD IMAGE

        productName.setText(product.getTenmathang());
        productPrice.setText(String.format("%d VND", product.getGia()));

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChitietmathangActivity.class);
            intent.putExtra("mathang", product);
            context.startActivity(intent);
        });

        return convertView;
    }
}


