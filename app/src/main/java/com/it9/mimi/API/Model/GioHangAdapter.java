package com.it9.mimi.API.Model;

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
import com.it9.mimi.API.Controller.GioHangController;
import com.it9.mimi.R;
import com.it9.mimi.ui.GioHangActionable;
import com.it9.mimi.ui.OnUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    private List<GioHang> gioHangList;
    private GioHangActionable actionable;
    private OnUpdateListener onUpdateListener;
    public List<GioHang> getItems() {
        return gioHangList;
    }

    public GioHangAdapter(List<GioHang> gioHangList, GioHangActionable actionable, OnUpdateListener callback) {
        this.gioHangList = gioHangList;
        this.actionable = actionable;
        onUpdateListener = callback;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.bind(gioHang);
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public void UpdateGioHang(GioHangRequest giohangRequest) {
        for (int i = 0; i < gioHangList.size(); i++) {
            GioHang gioHang = gioHangList.get(i);
            if(giohangRequest.getMagiohang().equals(gioHang.getMagiohang())){
                gioHang.setSoluong(giohangRequest.getSoluong());
                notifyItemChanged(i);
                onUpdateListener.onUpdate(getItems());
                break;
            }
        }
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView hinhAnh;
        private TextView tenMathang, giaMathang, textViewQuantity;
        private ImageButton buttonSubtract, buttonAdd;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhAnh = itemView.findViewById(R.id.hinhAnhMathang);
            tenMathang = itemView.findViewById(R.id.tenMathang);
            giaMathang = itemView.findViewById(R.id.giaMathang);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            buttonSubtract = itemView.findViewById(R.id.buttonSubtract);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }

        public void bind(GioHang gioHang) {
            // Hiển thị thông tin mặt hàng
            tenMathang.setText(gioHang.getTenmathang());
            giaMathang.setText(String.format("%,d VND", gioHang.getGia()));
            textViewQuantity.setText(String.format("%,d", gioHang.getSoluong()));

            // Lấy ảnh từ Firebase Storage và hiển thị trong ImageView bằng Glide
            FirebaseStorage storage = FirebaseStorage.getInstance();
            String hinhAnhPath = gioHang.getMamathang(); // Đường dẫn ảnh được lấy từ đối tượng GioHang
            if (hinhAnhPath != null && !hinhAnhPath.isEmpty()) {
                StorageReference storageReference = storage.getReference().child("uploads/mathang/" +hinhAnhPath);
                Glide.with(itemView.getContext())
                        .load(storageReference)
                        .placeholder(R.drawable.kitty) // Ảnh chờ khi tải ảnh
                        .error(R.drawable.placeholder_image) // Ảnh hiển thị khi có lỗi
                        .into(hinhAnh);
            } else {
                hinhAnh.setImageResource(R.drawable.placeholder_image); // Nếu không có ảnh thì hiển thị ảnh mặc định
            }

            // Xử lý sự kiện tăng giảm số lượng sản phẩm
            buttonAdd.setOnClickListener(v -> {
                GioHangController.Instance().Update(gioHang.getMagiohang(), gioHang.getSoluong() + 1);
            });

            buttonSubtract.setOnClickListener(v -> {
                if (gioHang.getSoluong() - 1 <= 0) {
                    GioHangController.Instance().Delete(new ArrayList<GioHang>(List.of(gioHang)));
                } else {
                    GioHangController.Instance().Update(gioHang.getMagiohang(), gioHang.getSoluong() - 1);
                }

                onUpdateListener.onUpdate(getItems());
            });
        }
    }
}
