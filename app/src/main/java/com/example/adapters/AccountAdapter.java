// com.example.adapter.AccountAdapter.java
package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.Account; // Import model Account
import com.example.myassignmentproject.R; // Đảm bảo đúng package của R

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accountList;
    public Account checkLogin(String phone, String password) {
        for (Account acc : accountList) {
            if (acc.getUsername().equals(phone) && acc.getPassword().equals(password)) {
                return acc;
            }
        }
        return null; // sai thông tin
    }
    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void setAccountList(List<Account> newAccountList) {
        this.accountList = newAccountList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountList.get(position);

        holder.tvAccountUsername.setText("Tên đăng nhập: " + account.getUsername());
        holder.tvAccountStatus.setText("Trạng thái: " + account.getStatus());
        holder.tvAccountRank.setText("Hạng: " + account.getRank());
        holder.tvAccountPoints.setText("Điểm tích lũy: " + account.getPoints());
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView tvAccountUsername;
        TextView tvAccountStatus;
        TextView tvAccountRank;
        TextView tvAccountPoints;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAccountUsername = itemView.findViewById(R.id.tv_account_username);
            tvAccountStatus = itemView.findViewById(R.id.tv_account_status);
            tvAccountRank = itemView.findViewById(R.id.tv_account_rank);
            tvAccountPoints = itemView.findViewById(R.id.tv_account_points);
        }

    }
}