package com.example.myassignmentproject; // Thay đổi package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.Promotion; // Import model Promotion
import com.example.adapters.PromotionAdapter; // Import PromotionAdapter

import java.util.List;

public class PromotionListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PromotionAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_list); // Sử dụng layout chung activity_list.xml

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadPromotions();
    }

    private void loadPromotions() {
        List<Promotion> promotions = dbHelper.getAllPromotions();
        if (promotions != null && !promotions.isEmpty()) {
            adapter = new PromotionAdapter(promotions);
            recyclerView.setAdapter(adapter);

            // Xử lý click đơn giản (chỉ hiển thị Toast)
            adapter.setOnItemClickListener(promotion -> {
                Toast.makeText(PromotionListActivity.this, "Clicked Promotion: " + promotion.getPromotionName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy khuyến mãi nào.", Toast.LENGTH_SHORT).show();
        }
    }
}