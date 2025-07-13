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
import com.example.myassignmentproject.databinding.ActivitySignInBinding;

import java.util.List;

public class Sign_in extends AppCompatActivity {
    ActivitySignInBinding binding;
    private DatabaseHelper dbHelper;
    private List<Account> accountList;
    private AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Bước 1: Khởi tạo và mở database
        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.openDataBase(); // Không cần create lại nếu đã tạo ở Splash
        } catch (Exception e) {
            e.printStackTrace();
            binding.txtResult.setText("Database Loaded Failed!");
            return;
        }

        // Bước 2: Load dữ liệu account từ DB
        accountList = dbHelper.getAllAccounts();
        accountAdapter = new AccountAdapter(accountList);

        // Bước 3: Gắn sự kiện
        addEvents();
    }

    private void addEvents() {
        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.edtPhone.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();

                if (phone.isEmpty() || password.isEmpty()) {
                    binding.txtResult.setText("Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                Account account = accountAdapter.checkLogin(phone, password);

                if (account != null) {
                    Toast.makeText(Sign_in.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_in.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    binding.txtResult.setText("Số điện thoại hoặc mật khẩu không đúng");
                }
            }
        });
        binding.txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_in.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        binding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_in.this, SignUp.class);
                startActivity(intent);
            }
        });
        binding.txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_in.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

    }
}