// com.example.app.PaymentListActivity.java
package com.example.myassignmentproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapters.PaymentMethodAdapter; // Đảm bảo import đúng adapter
import com.example.database.DatabaseHelper;
import com.example.models.PaymentMethod; // Đảm bảo import đúng model

import java.util.List;

public class PaymentListActivity extends AppCompatActivity {

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
            Log.e("PaymentListActivity", "Lỗi khi mở cơ sở dữ liệu: " + e.getMessage());
            Toast.makeText(this, "Không thể tải dữ liệu thanh toán. Lỗi DB!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        tvListTitle = findViewById(R.id.tvListTitle);
        recyclerView = findViewById(R.id.recyclerViewList);

        tvListTitle.setText("Danh Sách Phương Thức Thanh Toán");

        List<PaymentMethod> paymentMethods = dbHelper.getAllPaymentMethods();
        if (paymentMethods == null || paymentMethods.isEmpty()) {
            Log.d("PaymentListActivity", "Không tìm thấy phương thức thanh toán nào trong cơ sở dữ liệu.");
            Toast.makeText(this, "Không có dữ liệu thanh toán để hiển thị.", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PaymentMethodAdapter adapter = new PaymentMethodAdapter(this, paymentMethods);
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