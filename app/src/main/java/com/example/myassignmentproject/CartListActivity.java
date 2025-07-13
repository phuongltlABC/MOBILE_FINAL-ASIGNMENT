package com.example.myassignmentproject; // Đảm bảo package này khớp với package gốc của ứng dụng bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper; // Kiểm tra lại package nếu khác
import com.example.models.Cart; // Import model Cart của bạn
import com.example.adapters.CartAdapter; // Import CartAdapter của bạn

import java.util.List;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list); // Sử dụng layout chung activity_list.xml

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCarts();
    }

    private void loadCarts() {
        List<Cart> carts = dbHelper.getAllCarts();
        if (carts != null && !carts.isEmpty()) {
            adapter = new CartAdapter(carts);
            recyclerView.setAdapter(adapter);

            // Khi bấm vào một Cart, chuyển sang CartItemListActivity
            adapter.setOnItemClickListener(cart -> {
                Intent intent = new Intent(CartListActivity.this, CartItemListActivity.class);
                intent.putExtra("CART_ID", cart.getCartId()); // Truyền ID của giỏ hàng
                startActivity(intent);
                Toast.makeText(CartListActivity.this, "Bạn đã chọn Giỏ hàng ID: " + cart.getCartId(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy giỏ hàng nào.", Toast.LENGTH_SHORT).show();
        }
    }
}