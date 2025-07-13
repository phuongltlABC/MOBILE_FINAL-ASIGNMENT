package com.example.myassignmentproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.AccountAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.Account;
import com.example.models.Customer;
import com.example.myassignmentproject.databinding.ActivityForgotPasswordBinding;
import com.example.myassignmentproject.databinding.ActivitySignInBinding;

import java.util.List;

public class ForgotPassword extends AppCompatActivity {
    ActivityForgotPasswordBinding binding;
    private DatabaseHelper dbHelper;
    private List<Account> accountList;
    private AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Bước 1: Khởi tạo và mở database
        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ForgotPassword.this, "Failed to load database", Toast.LENGTH_SHORT).show();
            return;
        }

        // Bước 2: Load dữ liệu account từ DB
        accountList = dbHelper.getAllAccounts();
        accountAdapter = new AccountAdapter(accountList);
        addEvents();
    }

    private void addEvents() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.edtPhone.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(ForgotPassword.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Customer> customerList = dbHelper.getAllCustomers();
                Customer matchedCustomer = null;

                for (Customer c : customerList) {
                    if (c.getPhoneNumber().equals(phone)) {
                        matchedCustomer = c;
                        break;
                    }
                }

                if (matchedCustomer != null) {
                    // Nếu tìm thấy → chuyển sang màn hình ChangePassword
                    Intent intent = new Intent(ForgotPassword.this, ChangePassword.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", matchedCustomer.getPhoneNumber()); // hoặc putInt("customer_id", ...)
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    // Không tìm thấy
                    Toast.makeText(ForgotPassword.this, "Phone number not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}