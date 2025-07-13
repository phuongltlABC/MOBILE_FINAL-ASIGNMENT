package com.example.myassignmentproject; // Thay đổi package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.Category; // Import model Category
import com.example.adapters.CategoryAdapter; // Import CategoryAdapter

import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list); // Sử dụng layout chung activity_list.xml

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCategories();
    }

    private void loadCategories() {
        List<Category> categories = dbHelper.getAllCategories();
        if (categories != null && !categories.isEmpty()) {
            adapter = new CategoryAdapter(categories);
            recyclerView.setAdapter(adapter);

            // Xử lý click đơn giản (chỉ hiển thị Toast)
            adapter.setOnItemClickListener(category -> {
                Toast.makeText(CategoryListActivity.this, "Clicked Category: " + category.getCategoryName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy danh mục nào.", Toast.LENGTH_SHORT).show();
        }
    }
}