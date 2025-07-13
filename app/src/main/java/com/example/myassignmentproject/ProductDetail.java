package com.example.myassignmentproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.models.CartItem;
import com.example.models.Product;
import com.example.myassignmentproject.databinding.ActivityProductDetailBinding;

public class ProductDetail extends AppCompatActivity {
    ActivityProductDetailBinding binding;
    private ImageView imgProduct;
    private TextView txtProductName, txtPrice, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);

        Product product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            txtProductName.setText(product.getProductName());
            txtPrice.setText(String.format("%,.0f₫", product.getPrice()));
            txtDescription.setText(product.getDescription());

            if (product.getImage() != null) {
                imgProduct.setImageBitmap(BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length));
            }
        }
        addEvents();
    }

    private void addEvents() {
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = (Product) getIntent().getSerializableExtra("product");
                if (product != null) {
                    CartItem cartItem = new CartItem();
                    cartItem.setCartId(1); // Giả định cartId là 1
                    cartItem.setProductId(product.getProductId());
                    cartItem.setVariantId(0); // Nếu không có biến thể, đặt 0 hoặc -1
                    cartItem.setQuantity(1);
                    cartItem.setPrice(product.getPrice());
//
//                    Intent intent = new Intent(ProductDetail.this, CartActivity.class);
//                    intent.putExtra("cart_item", cartItem);
//                    startActivity(intent);
                }
            }
        });
        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
