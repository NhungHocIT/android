package com.it9.mimi;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.it9.mimi.API.Model.DoanhThu;
import com.it9.mimi.adapter.DoanhThuAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoanhThuFragment extends Fragment {

    private RecyclerView recyclerView;
    private DoanhThuAdapter adapter;
    private List<DoanhThu> doanhThuList;
    private EditText startDate, endDate;
    private Button confirmButton;
    private TextView revenueRangeTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);

        // Khởi tạo RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dữ liệu mẫu
        doanhThuList = new ArrayList<>();
        doanhThuList.add(new DoanhThu("Gà rán", 30000, 30000000));
        doanhThuList.add(new DoanhThu("Nước cam", 15000, 15000000));

        // Khởi tạo adapter và gán cho RecyclerView
        adapter = new DoanhThuAdapter(doanhThuList);
        recyclerView.setAdapter(adapter);

        super.onViewCreated(view, savedInstanceState);

        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        confirmButton = view.findViewById(R.id.btn_confirm);

        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        confirmButton = view.findViewById(R.id.btn_confirm);
        revenueRangeTextView = view.findViewById(R.id.tv_revenue_range);

        // Xử lý sự kiện khi nhấn vào ô nhập ngày bắt đầu
        startDate.setOnClickListener(v -> showDatePickerDialog(startDate));

        // Xử lý sự kiện khi nhấn vào ô nhập ngày kết thúc
        endDate.setOnClickListener(v -> showDatePickerDialog(endDate));

        confirmButton.setOnClickListener(v -> {
            String start = startDate.getText().toString();
            String end = endDate.getText().toString();

            // Cập nhật TextView với khoảng thời gian doanh thu
            revenueRangeTextView.setText("Doanh thu từ " + start + " đến " + end);
        });

        return view;
    }
    private void showDatePickerDialog(final EditText dateField) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    dateField.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }
}
