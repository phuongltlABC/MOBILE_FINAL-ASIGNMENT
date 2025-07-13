package com.example.myassignmentproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.myassignmentproject.databinding.ActivitySignUpBinding;

import java.util.List;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private DatabaseHelper dbHelper;
    private List<Account> accountList;
    private AccountAdapter accountAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DatabaseHelper(this);
        addEvents();
    }

    private void addEvents() {
        binding.btnSignin.setOnClickListener(v -> {
            String name = binding.edtName.getText().toString().trim();
            String phone = binding.edtPhone.getText().toString().trim();
            String email = binding.edtEmail.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ Họ tên, Số điện thoại và Mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            createNewAccount(name, phone, email, password);
        });

        // Sự kiện nút "Back"
        binding.btnBack.setOnClickListener(v -> finish());
    }

//    private void createNewAccount(String name, String phone, String email, String password) {
//        Customer customer = new Customer();
//        customer.setFirstName(name);
//        customer.setLastName("");
//        customer.setEmail(email);
//        customer.setPhoneNumber(phone);
//        long customerId = dbHelper.insertCustomer(customer);
//
//        if (customerId == -1) {
//            Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Account account = new Account();
//        account.setCustomerId((int) customerId);
//        account.setUsername(phone);
//        account.setPassword(password);
//        account.setIsAnonymous(0);
//        long accountId = dbHelper.insertAccount(account);
//
//        if (accountId == -1) {
//            Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
//        } else {
//            // tạo dialog
//            Dialog dialog = new Dialog(SignUp.this);
//            dialog.setContentView(R.layout.account_created_dialog);
//
//            // tham chiếu đến nút trong dialog
//            Button btnSignin = dialog.findViewById(R.id.btnSignin);
//            btnSignin.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
//                    Intent intent = new Intent(SignUp.this, Sign_in.class);
//                    startActivity(intent);
//                }
//            });
//            // builder.create().show()
//            // note set only interacting inside the dialog
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.show();
private void createNewAccount(String name, String phone, String email, String password) {
    Customer customer = new Customer();
    customer.setFirstName(name);
    customer.setLastName("");
    customer.setEmail(email);
    customer.setPhoneNumber(phone);
    customer.setCredit(0); // set mặc định
    customer.setAvatar(null);

    long customerId = dbHelper.insertCustomer(customer);
    Log.d("DEBUG", "Insert Customer ID: " + customerId);

    if (customerId == -1) {
        Toast.makeText(this, "Failed to create customer", Toast.LENGTH_SHORT).show();
        return;
    }

    Account account = new Account();
    account.setCustomerId((int) customerId);
    account.setUsername(phone);
    account.setPassword(password);
    account.setIsAnonymous(0);
    long accountId = dbHelper.insertAccount(account);
    Log.d("DEBUG", "Insert Account ID: " + accountId);

    if (accountId == -1) {
        Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
    } else {
// tạo dialog
            Dialog dialog = new Dialog(SignUp.this);
            dialog.setContentView(R.layout.account_created_dialog);

            // tham chiếu đến nút trong dialog
            Button btnSignin = dialog.findViewById(R.id.btnSignin);
            btnSignin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(SignUp.this, Sign_in.class);
                    startActivity(intent);
                }
            });
            // builder.create().show()
            // note set only interacting inside the dialog
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
    }
}
}