package com.it9.mimi;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.it9.mimi.ui.LoadingDialog;

public class LoadingAnimation extends AppCompatActivity {

    private Button btn_Start;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_animation);

        btn_Start = findViewById(R.id.btn_Start);

        loadingDialog = new LoadingDialog(this);

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.show();

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.cancel();
                    }
                };
                handler.postDelayed(runnable,3000);
            }
        });
    }
}