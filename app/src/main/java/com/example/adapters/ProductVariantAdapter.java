package com.example.adapters;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.ProductVariant; // Thay đổi theo package của bạn
import com.example.myassignmentproject.R; // Thay đổi theo package gốc của ứng dụng bạn

import java.util.List;

public class ProductVariantAdapter extends RecyclerView.Adapter<ProductVariantAdapter.ProductVariantViewHolder> {

    private List<ProductVariant> productVariantList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ProductVariant productVariant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProductVariantAdapter(List<ProductVariant> productVariantList) {
        this.productVariantList = productVariantList;
    }

    @NonNull
    @Override
    public ProductVariantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_variant, parent, false);
        return new ProductVariantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVariantViewHolder holder, int position) {
        ProductVariant variant = productVariantList.get(position);
        holder.variantId.setText("Variant ID: #" + variant.getVariantId());
        holder.variantPrice.setText(String.format("$%.2f", variant.getPrice()));
        holder.variantStock.setText("Stock: " + variant.getStockQuantity());

        if (variant.getImageBlob() != null) {
            holder.variantImage.setImageBitmap(BitmapFactory.decodeByteArray(variant.getImageBlob(), 0, variant.getImageBlob().length));
        } else {
            holder.variantImage.setImageResource(R.drawable.produ_lamp);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(variant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productVariantList.size();
    }

    public static class ProductVariantViewHolder extends RecyclerView.ViewHolder {
        ImageView variantImage;
        TextView variantId;
        TextView variantPrice;
        TextView variantStock;

        public ProductVariantViewHolder(@NonNull View itemView) {
            super(itemView);
            variantImage = itemView.findViewById(R.id.variant_image);
            variantId = itemView.findViewById(R.id.text_variant_id);
            variantPrice = itemView.findViewById(R.id.text_variant_price);
            variantStock = itemView.findViewById(R.id.text_variant_stock);
        }
    }

    public void updateProductVariants(List<ProductVariant> newVariants) {
        this.productVariantList.clear();
        this.productVariantList.addAll(newVariants);
        notifyDataSetChanged();
    }
}