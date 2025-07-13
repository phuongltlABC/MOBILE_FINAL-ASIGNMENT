package com.example.myassignmentproject; // Thay đổi package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.Product;
import com.example.models.ProductVariant;
import com.example.models.Color;
import com.example.models.Size;
import com.example.adapters.ProductVariantAdapter;

import java.util.List;

public class ProductVariantListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductVariantAdapter adapter;
    private DatabaseHelper dbHelper;
    private TextView tvProductDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_variant_list); // Tạo layout mới cho màn hình này

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view_product_variants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvProductDetails = findViewById(R.id.tv_product_details); // TextView để hiển thị tên sản phẩm

        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        if (productId != -1) {
            displayProductDetails(productId);
            loadProductVariants(productId);
        } else {
            Toast.makeText(this, "Không tìm thấy Product ID.", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có ID
        }
    }

    private void displayProductDetails(int productId) {
        Product product = dbHelper.getProductById(productId);
        if (product != null) {
            tvProductDetails.setText("Biến thể của sản phẩm: " + product.getProductName() + " (ID: " + product.getProductId() + ")");
        } else {
            tvProductDetails.setText("Thông tin sản phẩm không có sẵn.");
        }
    }

    private void loadProductVariants(int productId) {
        List<ProductVariant> variants = dbHelper.getProductVariantsByProductId(productId);
        if (variants != null && !variants.isEmpty()) {
            adapter = new ProductVariantAdapter(variants);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(variant -> {
                // Bạn có thể hiển thị thêm chi tiết về biến thể ở đây, ví dụ:
                Color color = dbHelper.getColorByCode(variant.getColorCode());
                Size size = dbHelper.getSizeByCode(variant.getSizeCode());

                String details = "Variant ID: " + variant.getVariantId() + "\n" +
                        "Color: " + (color != null ? color.getColorName() : "N/A") + "\n" +
                        "Size: " + (size != null ? size.getSizeName() : "N/A") + "\n" +
                        "Price: $" + String.format("%.2f", variant.getPrice()) + "\n" +
                        "Stock: " + variant.getStockQuantity();
                Toast.makeText(ProductVariantListActivity.this, details, Toast.LENGTH_LONG).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy biến thể nào cho sản phẩm này.", Toast.LENGTH_SHORT).show();
        }
    }
}