package com.example.myassignmentproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapters.CustomerAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.Customer;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private TextView tvListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_common);

        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            Log.e("CustomerListActivity", "Lỗi khi mở cơ sở dữ liệu: " + e.getMessage());
            Toast.makeText(this, "Không thể tải dữ liệu khách hàng. Lỗi DB!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        tvListTitle = findViewById(R.id.tvListTitle);
        recyclerView = findViewById(R.id.recyclerViewList);

        tvListTitle.setText("Danh Sách Khách Hàng");

        List<Customer> customers = dbHelper.getAllCustomers();
        if (customers == null || customers.isEmpty()) {
            Log.d("CustomerListActivity", "Không tìm thấy khách hàng nào trong cơ sở dữ liệu.");
            Toast.makeText(this, "Không có dữ liệu khách hàng để hiển thị.", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomerAdapter adapter = new CustomerAdapter(this, customers);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}