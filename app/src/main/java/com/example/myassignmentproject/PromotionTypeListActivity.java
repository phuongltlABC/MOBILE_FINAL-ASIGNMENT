package com.example.myassignmentproject; // Thay đổi package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.PromotionType; // Import model PromotionType
import com.example.adapters.PromotionTypeAdapter; // Import PromotionTypeAdapter

import java.util.List;

public class PromotionTypeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PromotionTypeAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_type_list); // Sử dụng layout chung activity_list.xml

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadPromotionTypes();
    }

    private void loadPromotionTypes() {
        List<PromotionType> promotionTypes = dbHelper.getAllPromotionTypes();
        if (promotionTypes != null && !promotionTypes.isEmpty()) {
            adapter = new PromotionTypeAdapter(promotionTypes);
            recyclerView.setAdapter(adapter);

            // Xử lý click đơn giản (chỉ hiển thị Toast)
            adapter.setOnItemClickListener(promotionType -> {
                Toast.makeText(PromotionTypeListActivity.this, "Clicked Promotion Type: " + promotionType.getTypeName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy loại khuyến mãi nào.", Toast.LENGTH_SHORT).show();
        }
    }
}