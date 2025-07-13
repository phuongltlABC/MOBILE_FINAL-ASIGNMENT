package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.CartItem; // Thay đổi theo package của bạn
import com.example.myassignmentproject.R; // Thay đổi theo package gốc của ứng dụng bạn

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private List<CartItem> cartItemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CartItem cartItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CartItemAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.productId.setText("Product ID: #" + cartItem.getProductId());
        holder.variantId.setText("Variant ID: #" + cartItem.getVariantId());
        holder.quantity.setText("Quantity: " + cartItem.getQuantity());
        holder.price.setText(String.format("Price: $%.2f", cartItem.getPrice())); // Định dạng tiền tệ

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(cartItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView productId;
        TextView variantId;
        TextView quantity;
        TextView price;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productId = itemView.findViewById(R.id.text_cart_item_product_id);
            variantId = itemView.findViewById(R.id.text_cart_item_variant_id);
            quantity = itemView.findViewById(R.id.text_cart_item_quantity);
            price = itemView.findViewById(R.id.text_cart_item_price);
        }
    }

    public void updateCartItems(List<CartItem> newCartItems) {
        this.cartItemList.clear();
        this.cartItemList.addAll(newCartItems);
        notifyDataSetChanged();
    }
}