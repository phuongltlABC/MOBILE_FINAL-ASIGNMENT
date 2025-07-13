// com.example.app.ReviewListActivity.java
package com.example.myassignmentproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapters.ReviewAdapter; // Đảm bảo import đúng adapter
import com.example.database.DatabaseHelper;
import com.example.models.Review; // Đảm bảo import đúng model

import java.util.List;

public class ReviewListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private TextView tvListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_common);

        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            Log.e("ReviewListActivity", "Lỗi khi mở cơ sở dữ liệu: " + e.getMessage());
            Toast.makeText(this, "Không thể tải dữ liệu đánh giá. Lỗi DB!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        tvListTitle = findViewById(R.id.tvListTitle);
        recyclerView = findViewById(R.id.recyclerViewList);

        tvListTitle.setText("Danh Sách Đánh Giá");

        List<Review> reviews = dbHelper.getAllReviews();
        if (reviews == null || reviews.isEmpty()) {
            Log.d("ReviewListActivity", "Không tìm thấy đánh giá nào trong cơ sở dữ liệu.");
            Toast.makeText(this, "Không có dữ liệu đánh giá để hiển thị.", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReviewAdapter adapter = new ReviewAdapter(this, reviews);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}