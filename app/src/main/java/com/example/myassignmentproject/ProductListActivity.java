package com.example.myassignmentproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.adapters.ProductAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.Product;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);

        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = dbHelper.getAllProducts();
        if (products != null && !products.isEmpty()) {
            adapter = new ProductAdapter(this, products); // ✅ Truyền context đúng
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(product -> {
                Intent intent = new Intent(ProductListActivity.this, ProductVariantListActivity.class);
                intent.putExtra("PRODUCT_ID", product.getProductId());
                startActivity(intent);
                Toast.makeText(this, "Clicked: " + product.getProductName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy sản phẩm nào.", Toast.LENGTH_SHORT).show();
        }
    }
}
