package com.example.myassignmentproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myassignmentproject.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
    }

    private void addEvents() {
        binding.navBar.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.mnCatalog) {
                replaceFrag(new CatalogFragment());
            } else if (item.getItemId() ==R.id.mnPromo) {
                replaceFrag(new PromotionFragment());
            } else if (item.getItemId() == R.id.mnNoti){
                replaceFrag(new NotificationFragment());
            } else if (item.getItemId() ==R.id.mnProfile){
                replaceFrag(new ProfileFragment());
            } else fragment = null;
            if (fragment != null) {
                replaceFrag(fragment);
                return true; // báo là đã xử lý sự kiện
            }
            return false;
        });
        binding.txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchingActivity.class);
                startActivity(intent);
            }
        });
    }
    public void replaceFrag(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}