package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R;
import com.example.models.LineItem;

import java.util.List;

public class LineItemAdapter extends RecyclerView.Adapter<LineItemAdapter.LineItemViewHolder> {

    private final Context context;
    private List<LineItem> lineItemList;

    public LineItemAdapter(Context context, List<LineItem> lineItemList) {
        this.context = context;
        this.lineItemList = lineItemList;
    }

    public static class LineItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvVariantInfo, tvQuantity, tvPrice, tvDiscountAmount, tvSubtotal;

        public LineItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvVariantInfo = itemView.findViewById(R.id.tvVariantInfo);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDiscountAmount = itemView.findViewById(R.id.tvDiscountAmount);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
        }
    }

    @NonNull
    @Override
    public LineItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_item, parent, false);
        return new LineItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineItemViewHolder holder, int position) {
        LineItem item = lineItemList.get(position);
        double subtotal = (item.getQuantity() * item.getPrice()) - item.getDiscountAmount();

        holder.tvProductName.setText("Product ID: " + item.getProductId());
        holder.tvVariantInfo.setText("Variant ID: " + item.getVariantId());
        holder.tvQuantity.setText("Quantity: " + item.getQuantity());
        holder.tvPrice.setText(String.format("Price: %.2f VND", item.getPrice()));
        holder.tvDiscountAmount.setText(String.format("Discount: %.2f VND", item.getDiscountAmount()));
        holder.tvSubtotal.setText(String.format("Subtotal: %.2f VND", subtotal));
    }

    @Override
    public int getItemCount() {
        return lineItemList.size();
    }

    public void updateData(List<LineItem> newList) {
        this.lineItemList = newList;
        notifyDataSetChanged();
    }
}
