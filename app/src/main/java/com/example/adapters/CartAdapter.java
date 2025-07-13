package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.Cart; // Thay đổi theo package của bạn
import com.example.myassignmentproject.R; // Thay đổi theo package gốc của ứng dụng bạn

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> cartList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Cart cart);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.cartId.setText("Cart ID: #" + cart.getCartId());
        holder.accountId.setText("Account ID: " + cart.getAccountId());
        holder.createdAt.setText("Created At: " + cart.getCreatedAt());
        holder.updatedAt.setText("Last Updated: " + cart.getUpdatedAt());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartId;
        TextView accountId;
        TextView createdAt;
        TextView updatedAt;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartId = itemView.findViewById(R.id.text_cart_id);
            accountId = itemView.findViewById(R.id.text_account_id);
            createdAt = itemView.findViewById(R.id.text_created_at);
            updatedAt = itemView.findViewById(R.id.text_updated_at);
        }
    }

    public void updateCarts(List<Cart> newCarts) {
        this.cartList.clear();
        this.cartList.addAll(newCarts);
        notifyDataSetChanged();
    }
}