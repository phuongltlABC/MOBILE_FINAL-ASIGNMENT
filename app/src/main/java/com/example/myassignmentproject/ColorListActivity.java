package com.example.myassignmentproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.adapters.ColorAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.Color;

import java.util.List;

public class ColorListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ColorAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list); // Layout XML chứa RecyclerView với ID: my_recycler_view

        // Khởi tạo database và recycler view
        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Gọi hàm để load dữ liệu
        loadColors();
    }

    private void loadColors() {
        List<Color> colors = dbHelper.getAllColors();
        if (colors != null && !colors.isEmpty()) {
            adapter = new ColorAdapter(colors);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(color -> {
                Toast.makeText(ColorListActivity.this, "Clicked Color: " + color.getColorName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy màu sắc nào.", Toast.LENGTH_SHORT).show();
        }
    }
}
