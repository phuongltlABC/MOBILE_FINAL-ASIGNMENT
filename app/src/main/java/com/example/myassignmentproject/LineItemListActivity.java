package com.example.myassignmentproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapters.LineItemAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.LineItem;

import java.util.List;

public class LineItemListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private TextView tvListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_common); // Layout dùng chung với TextView tiêu đề + RecyclerView

        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            Log.e("LineItemListActivity", "Lỗi mở DB: " + e.getMessage());
            Toast.makeText(this, "Không thể mở dữ liệu chi tiết đơn hàng.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        tvListTitle = findViewById(R.id.tvListTitle);
        recyclerView = findViewById(R.id.recyclerViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvListTitle.setText("Danh sách chi tiết đơn hàng");

        List<LineItem> lineItems = dbHelper.getAllLineItems();
        if (lineItems == null || lineItems.isEmpty()) {
            Toast.makeText(this, "Không có chi tiết đơn hàng để hiển thị.", Toast.LENGTH_SHORT).show();
        }

        LineItemAdapter adapter = new LineItemAdapter(this, lineItems);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) dbHelper.close();
    }
}
