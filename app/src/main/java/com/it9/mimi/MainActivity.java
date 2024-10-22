package com.it9.mimi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.it9.mimi.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new KhachHangFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.khachhang);
        }

        // Ensure the layout is correctly handling WindowInsets
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.khachhang) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new KhachHangFragment())
                    .commit();
            TextView title = findViewById(R.id.toolbar_title);
            title.setText("Khách Hàng");

        } else if (item.getItemId() == R.id.mathang) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MatHangFragment())
                    .commit();
            TextView title = findViewById(R.id.toolbar_title);
            title.setText("Mặt Hàng");
        } else if (item.getItemId() == R.id.loaimathang) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoaiMatHangFragment())
                    .commit();
            TextView title = findViewById(R.id.toolbar_title);
            title.setText("Loại Mặt Hàng");
        } else if (item.getItemId() == R.id.doanhso) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DoanhSoFragment())
                    .commit();
            TextView title = findViewById(R.id.toolbar_title);
            title.setText("Doanh Số");
        }else if (item.getItemId() == R.id.doanhthu) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new DoanhThuFragment())
                        .commit();
                TextView title = findViewById(R.id.toolbar_title);
                title.setText("Doanh Thu");
        }else if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
