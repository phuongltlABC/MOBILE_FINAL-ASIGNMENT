package com.example.myassignmentproject; // Package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.CartItem;
import com.example.models.Product; // Cần để hiển thị tên sản phẩm
import com.example.adapters.CartItemAdapter; // Adapter của bạn

import java.util.List;

public class CartItemListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartItemAdapter adapter;
    private DatabaseHelper dbHelper;
    private TextView tvCartDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_item_list); // Layout riêng cho danh sách Cart Item

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view_cart_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvCartDetails = findViewById(R.id.tv_cart_details);

        int cartId = getIntent().getIntExtra("CART_ID", -1);
        if (cartId != -1) {
            tvCartDetails.setText("Các mục trong giỏ hàng ID: #" + cartId);
            loadCartItems(cartId);
        } else {
            Toast.makeText(this, "Không tìm thấy ID giỏ hàng.", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có ID
        }
    }

    private void loadCartItems(int cartId) {
        List<CartItem> cartItems = dbHelper.getCartItemsByCartId(cartId);
        if (cartItems != null && !cartItems.isEmpty()) {
            adapter = new CartItemAdapter(cartItems);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(cartItem -> {
                Product product = dbHelper.getProductById(cartItem.getProductId());
                String productName = (product != null) ? product.getProductName() : "Sản phẩm không xác định";
                Toast.makeText(CartItemListActivity.this,
                        "Mục: " + productName + ", SL: " + cartItem.getQuantity() + ", Giá: $" + String.format("%.2f", cartItem.getPrice()),
                        Toast.LENGTH_LONG).show();
            });
        } else {
            Toast.makeText(this, "Giỏ hàng này trống.", Toast.LENGTH_SHORT).show();
        }
    }
}