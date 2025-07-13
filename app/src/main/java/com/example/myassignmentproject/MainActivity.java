package com.example.myassignmentproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.database.DatabaseHelper;
import com.example.myassignmentproject.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    public static int SPLASH_TIMER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding;

        // --- Khởi tạo và kiểm tra Database Helper ---
        // Đảm bảo DatabaseHelper.java nằm trong package com.example.cabana2.database
        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase(); // Sao chép DB từ assets nếu chưa tồn tại
            dbHelper.openDataBase();   // Mở DB để sẵn sàng sử dụng
            Log.d("MainActivity", "Database initialized and opened successfully.");
            // Toast.makeText(this, "Database sẵn sàng!", Toast.LENGTH_SHORT).show(); // Có thể bỏ comment để kiểm tra nhanh
        } catch (IOException e) {
            Log.e("MainActivity", "Lỗi khi sao chép/mở cơ sở dữ liệu từ assets: " + e.getMessage());
            Toast.makeText(this, "Lỗi khởi tạo DB: " + e.getMessage(), Toast.LENGTH_LONG).show();
            // Xử lý lỗi nghiêm trọng: có thể thoát ứng dụng hoặc hiển thị dialog lỗi
            finish();
            return; // Dừng onCreate nếu DB không sẵn sàng
        } catch (Exception e) { // Bao gồm các lỗi khác không phải IOException
            Log.e("MainActivity", "Lỗi tổng quát khi khởi tạo DB: " + e.getMessage());
            Toast.makeText(this, "Lỗi DB không xác định: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Onboarding.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
        // --- Ánh xạ và thiết lập Listener cho tất cả 19 nút ---

        // Nhóm Sản phẩm & Danh mục
//        findViewById(R.id.btn_view_products).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductListActivity.class)));
//        findViewById(R.id.btn_view_categories).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CategoryListActivity.class)));
//        findViewById(R.id.btn_view_brands).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BrandListActivity.class)));
//        findViewById(R.id.btn_view_colors).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ColorListActivity.class)));
//        findViewById(R.id.btn_view_sizes).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SizeListActivity.class)));
//        findViewById(R.id.btn_view_rooms).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RoomListActivity.class)));
//        findViewById(R.id.btn_view_variants).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductVariantListActivity.class))); // Đã đổi tên lớp thành ProductVariantListActivity
//        findViewById(R.id.btn_view_product_room).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RoomListActivity.class))); // Trỏ đến ProductRoomListActivity
//
//        // Nhóm Khách hàng & Đơn hàng
//        findViewById(R.id.btn_view_customers).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CustomerListActivity.class)));
//        findViewById(R.id.btn_view_orders).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrderListActivity.class)));
//        findViewById(R.id.btn_view_line_items).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LineItemListActivity.class)));
//        findViewById(R.id.btn_view_reviews).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReviewListActivity.class)));
//        findViewById(R.id.btn_view_address_info).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddressInfoListActivity.class)));
//        findViewById(R.id.btn_view_carts).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartListActivity.class)));
//
//        // Nhóm Thanh toán & Vận chuyển
//        findViewById(R.id.btn_view_payment_methods).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PaymentListActivity.class))); // Đã đổi tên lớp thành PaymentMethodListActivity
//        findViewById(R.id.btn_view_shipment_methods).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ShipmentListActivity.class))); // Đã đổi tên lớp thành ShipmentMethodListActivity
//
//        // Nhóm Khuyến mãi
//        findViewById(R.id.btn_view_promotions).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PromotionListActivity.class)));
//        findViewById(R.id.btn_view_promotion_types).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PromotionTypeListActivity.class)));
//        findViewById(R.id.btn_view_promotion_product_mapping).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PromotionListActivity.class))); // Trỏ đến PromotionProductMappingListActivity

        // Thêm các nút khác tại đây nếu có thêm bảng (ví dụ: ProductImage, OrderStatusHistory, v.v.)
        // findViewById(R.id.btn_view_product_images).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductImageListActivity.class)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đảm bảo đóng DatabaseHelper khi Activity bị hủy để tránh rò rỉ bộ nhớ
        if (dbHelper != null) {
            dbHelper.close();
            Log.d("MainActivity", "DatabaseHelper closed.");
        }
    }

}