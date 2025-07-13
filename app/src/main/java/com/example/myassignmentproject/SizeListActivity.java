package com.example.myassignmentproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.Size;
import com.example.adapters.SizeAdapter;

import java.util.List;

public class SizeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SizeAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_list); // Đảm bảo bạn đã tạo layout này

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadSizes();
    }

    private void loadSizes() {
        List<Size> sizes = dbHelper.getAllSizes();
        if (sizes != null && !sizes.isEmpty()) {
            adapter = new SizeAdapter(sizes);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(size -> {
                Toast.makeText(SizeListActivity.this, "Clicked Size: " + size.getSizeName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy kích cỡ nào.", Toast.LENGTH_SHORT).show();
        }
    }
}
