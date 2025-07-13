package com.example.myassignmentproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapters.ProductGridAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.Category;
import com.example.models.Product;
import com.example.models.Room;
import com.example.myassignmentproject.databinding.ActivitySearchingBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity {

    ActivitySearchingBinding binding;
    DatabaseHelper db;
    List<Product> allProducts;
    List<Product> filteredProducts;
    ProductGridAdapter adapter;
    List<Category> categoryList;
    List<Room> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(this);

        // Khởi tạo Spinner Category
        categoryList = db.getAllCategories();
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("Tất cả");
        for (Category c : categoryList) {
            categoryNames.add(c.getCategoryName());
        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnCategory.setAdapter(categoryAdapter);

        // Khởi tạo Spinner Room
        roomList = db.getAllRooms();
        List<String> roomNames = new ArrayList<>();
        roomNames.add("Tất cả");
        for (Room r : roomList) {
            roomNames.add(r.getRoomName());
        }

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomNames);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnRoom.setAdapter(roomAdapter);

        // Hiển thị tất cả sản phẩm
        allProducts = db.getAllProducts();
        filteredProducts = new ArrayList<>(allProducts);
        adapter = new ProductGridAdapter(this, filteredProducts);
        binding.gvSearchResult.setAdapter(adapter);

        // Realtime search
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Gọi filter khi user thay đổi Category Spinner
        binding.spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterProducts(binding.edtSearch.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Gọi filter khi user thay đổi Room Spinner
        binding.spnRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterProducts(binding.edtSearch.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void filterProducts(String keyword) {
        String selectedCategory = binding.spnCategory.getSelectedItem().toString();
        String selectedRoom = binding.spnRoom.getSelectedItem().toString();

        filteredProducts.clear();

        for (Product product : allProducts) {
            boolean matchKeyword = product.getProductName().toLowerCase().contains(keyword.toLowerCase());

            boolean matchCategory = selectedCategory.equals("Tất cả") ||
                    getCategoryNameById(product.getCategoryId()).equalsIgnoreCase(selectedCategory);

            boolean matchRoom = selectedRoom.equals("Tất cả") ||
                    getRoomNameById(product.getRoomId()).equalsIgnoreCase(selectedRoom);

            if (matchKeyword && matchCategory && matchRoom) {
                filteredProducts.add(product);
            }
        }

        adapter.notifyDataSetChanged();
    }

    private String getCategoryNameById(int categoryId) {
        for (Category c : categoryList) {
            if (c.getCategoryId() == categoryId) return c.getCategoryName();
        }
        return "";
    }

    private String getRoomNameById(int roomId) {
        for (Room r : roomList) {
            if (r.getRoomId() == roomId) return r.getRoomName();
        }
        return "";
    }
}
