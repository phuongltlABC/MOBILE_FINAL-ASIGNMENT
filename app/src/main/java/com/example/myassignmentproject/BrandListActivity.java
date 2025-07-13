package com.example.myassignmentproject; // Thay đổi package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.Brand; // Import model Brand
import com.example.adapters.BrandAdapter; // Import BrandAdapter

import java.util.List;

public class BrandListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BrandAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list); // Sử dụng layout chung activity_list.xml

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadBrands();
    }

    private void loadBrands() {
        List<Brand> brands = dbHelper.getAllBrands();
        if (brands != null && !brands.isEmpty()) {
            adapter = new BrandAdapter(brands);
            recyclerView.setAdapter(adapter);

            // Xử lý click đơn giản (chỉ hiển thị Toast)
            adapter.setOnItemClickListener(brand -> {
                Toast.makeText(BrandListActivity.this, "Clicked Brand: " + brand.getBrandName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy thương hiệu nào.", Toast.LENGTH_SHORT).show();
        }
    }
}