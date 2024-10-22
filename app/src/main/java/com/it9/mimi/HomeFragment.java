package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.it9.mimi.API.Controller.MatHangController;
import com.it9.mimi.adapter.ProductAdapter;
import com.it9.mimi.API.Model.mathang;
import com.it9.mimi.ui.MatHangActionable;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements MatHangActionable {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private GridView productGrid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        InitView(view);


        productGrid = view.findViewById(R.id.bestSellerGrid);
        MatHangController.Instance().SetCurrentView(this);
        MatHangController.Instance().ShowAllMatHang();

        return view;
    }


    private void InitView(View view) {
        ImageView cartIcon = view.findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một Intent để mở GioHangActivity
                Intent intent = new Intent(getActivity(), GiohangActivity.class);
                startActivity(intent);
            }
        });

        ImageView doanicon = view.findViewById(R.id.category1);
        doanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchActivity(1);
            }
        });

        doanicon = view.findViewById(R.id.category2);
        doanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchActivity(2);
            }
        });

        doanicon = view.findViewById(R.id.category3);
        doanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchActivity(3);
            }
        });

        doanicon = view.findViewById(R.id.category4);
        doanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchActivity(4);
            }
        });
    }

    private void SwitchActivity(int maloaimathang){
        Intent intent = new Intent(getActivity(),dsdoanActivity.class);
        intent.putExtra("maloaimathang", maloaimathang);
        startActivity(intent);
    }

    @Override
    public void ShowSuccess(mathang mathang) {

    }

    @Override
    public void ShowSuccess(List<mathang> mathangList) {
        ProductAdapter adapter = new ProductAdapter(getActivity(), mathangList);
        productGrid.setAdapter(adapter);
    }

    @Override
    public void ShowError(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }


}