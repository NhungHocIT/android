package com.it9.mimi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.it9.mimi.R;
import com.it9.mimi.API.Controller.AccountController;
import com.it9.mimi.API.Model.Account;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ProfileFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    private Button logoutButton, viewHistoryButton, changeInfoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView fullNameTextView = view.findViewById(R.id.full_name);
        TextView phoneNumberTextView = view.findViewById(R.id.phone_number);
        TextView addressTextView = view.findViewById(R.id.address);

//        Intent intent = getIntent();
//        Account account = intent.getParcelableExtra("account");
//        if(account == null){
//            intent = new Intent(trangcanhan_activity.this, login.class);
//            startActivity(intent);
//        }

        Account account = AccountController.Instance().GetAccount();

        if (account != null) {
            fullNameTextView.setText(account.getHovaten()); // Sử dụng getter method của Account để lấy tên
            phoneNumberTextView.setText(account.getSodienthoai()); // Sử dụng getter method của Account để lấy số điện thoại
            addressTextView.setText(account.getDiachi()); // Sử dụng getter method của Account để lấy địa chỉ
        }

        changeInfoButton = view.findViewById(R.id.button_change_info);
        changeInfoButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
            startActivity(intent);
        });

        viewHistoryButton = view.findViewById(R.id.button_view_history);
        viewHistoryButton.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "in developer progress", Toast.LENGTH_SHORT).show();
        });

        logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), login.class);
            startActivity(intent);
            getActivity().finish();
        });


        return view;
    }
}