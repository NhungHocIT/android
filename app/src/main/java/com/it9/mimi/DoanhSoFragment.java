package com.it9.mimi;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.it9.mimi.API.Controller.ThongKeController;
import com.it9.mimi.API.Model.ThongKe;
import com.it9.mimi.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DoanhSoFragment extends Fragment {

    private EditText startDate, endDate;
    private Button confirmButton;
    private TextView salesRangeTextView;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_so, container, false);
        // Áp dụng WindowInsets cho RelativeLayout
        View mainView = view.findViewById(R.id.DoanhSoFragment);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        super.onViewCreated(view, savedInstanceState);

        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        confirmButton = view.findViewById(R.id.btn_confirm);
        salesRangeTextView = view.findViewById(R.id.tv_sales_range);
        barChart = view.findViewById(R.id.chart);

        // Sự kiện khi nhấn vào ô nhập ngày bắt đầu
        startDate.setOnClickListener(v -> showDatePickerDialog(startDate));

        // Sự kiện khi nhấn vào ô nhập ngày kết thúc
        endDate.setOnClickListener(v -> showDatePickerDialog(endDate));

        // Xác nhận khoảng thời gian và cập nhật TextView
        confirmButton.setOnClickListener(v -> {
            String start = startDate.getText().toString();
            String end = endDate.getText().toString();

            // Cập nhật TextView hiển thị khoảng thời gian doanh số
            salesRangeTextView.setText("Doanh số từ " + start + " đến " + end);
            ThongKeController.ShowThongKe(this, start, end);
            // TODO: Cập nhật biểu đồ với dữ liệu mới
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
                    String selectedDate = year1 + "-" + (month1 + 1) + "-" + dayOfMonth;
                    dateField.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    public void ShowMessage(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void Show(List<ThongKe> list) {
        if (getView() == null) return;

        List<String> xValues = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        long max = 0;
        for (int i = 0; i < list.size(); i++) {
            xValues.add(list.get(i).tenmathang);
            entries.add(new BarEntry(i, list.get(i).doanhso));
            if (max < list.get(i).doanhso){
                max = list.get(i).doanhso;
            }
        }


        MyChart myChart = new MyChart();
        myChart.setupChart(getView(), xValues, entries, max * 1.3f);
    }

    public class MyChart {
        private BarChart barChart;

        public void setupChart(View view, List<String> xValues, ArrayList<BarEntry> entries, float max) {
            barChart = view.findViewById(R.id.chart);
            barChart.getAxisRight().setEnabled(false);

            YAxis yAxis = barChart.getAxis(YAxis.AxisDependency.LEFT);
            yAxis.setAxisMinimum(0f);  // Đặt giá trị tối thiểu
            yAxis.setAxisMaximum(max); // Đặt giá trị tối đa
            yAxis.setAxisLineColor(Color.BLACK);
            yAxis.setLabelCount(10);

            BarDataSet dataSet = new BarDataSet(entries, "Subject");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            BarData barData = new BarData(dataSet);
            barChart.setData(barData);

            barChart.getDescription().setEnabled(false);
            barChart.invalidate();
            barData.setBarWidth(0.5f); // Đặt độ rộng của cột là 50% chiều rộng của mỗi khoảng
            barChart.setData(barData);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);          // Đặt granularity cho trục X
            xAxis.setGranularityEnabled(true); // Bật tính năng granularity cho trục X
        }
    }
}
