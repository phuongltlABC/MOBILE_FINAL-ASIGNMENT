package com.example.myassignmentproject;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.models.Product;

public class ProductDetail extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView txtProductName, txtPrice, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

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
    }
}
