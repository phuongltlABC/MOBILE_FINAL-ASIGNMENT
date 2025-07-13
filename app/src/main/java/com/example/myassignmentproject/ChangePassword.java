package com.example.myassignmentproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.database.DatabaseHelper;
import com.example.models.Account;
import com.example.models.Customer;
import com.example.myassignmentproject.databinding.ActivityChangePasswordBinding;

import java.util.List;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    private DatabaseHelper dbHelper;
    private String phoneReceived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Nhận dữ liệu phone từ Intent
        phoneReceived = getIntent().getStringExtra("phone");
        if (phoneReceived == null || phoneReceived.isEmpty()) {
            Toast.makeText(this, "Phone number is missing", Toast.LENGTH_SHORT).show();
            finish(); // thoát nếu không có số điện thoại
            return;
        }

        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Database error", Toast.LENGTH_SHORT).show();
            return;
        }

        addEvents();
    }

    private void addEvents() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.edtPassword.getText().toString().trim();
                String confirmPassword = binding.edtConfirmPass.getText().toString().trim();

                if (password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(ChangePassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tìm customer theo phone
                List<Customer> customers = dbHelper.getAllCustomers();
                Customer matchedCustomer = null;
                for (Customer c : customers) {
                    if (phoneReceived.equals(c.getPhoneNumber())) {
                        matchedCustomer = c;
                        break;
                    }
                }

                if (matchedCustomer == null) {
                    Toast.makeText(ChangePassword.this, "Customer not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                int customerId = matchedCustomer.getCustomerId();

                // Tìm account theo customer_id
                List<Account> accounts = dbHelper.getAllAccounts();
                Account matchedAccount = null;
                for (Account acc : accounts) {
                    if (acc.getCustomerId() == customerId) {
                        matchedAccount = acc;
                        break;
                    }
                }

                if (matchedAccount == null) {
                    Toast.makeText(ChangePassword.this, "Account not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cập nhật mật khẩu mới
                boolean success = dbHelper.updateAccountPassword(matchedAccount.getAccountId(), password);
                if (success) {
                    Dialog dialog = new Dialog(ChangePassword.this);
                    dialog.setContentView(R.layout.password_changed_dialog);

                    // tham chiếu đến nút trong dialog
                    Button btnSignin = dialog.findViewById(R.id.btnSignin);
                    btnSignin.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Intent intent = new Intent(ChangePassword.this, Sign_in.class);
                            startActivity(intent);
                        }
                    });
                    // builder.create().show()
                    // note set only interacting inside the dialog
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                } else {
                    Toast.makeText(ChangePassword.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}