package com.example.adapters; // Ensure this package matches your project structure

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R; // Ensure you have R.layout.item_order
import com.example.models.Order; // Ensure this import path matches your Order class

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    // Constructor to initialize the adapter with context and data
    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    /**
     * ViewHolder class to hold references to the views in each item.
     */
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId;
        TextView tvOrderStatus;
        TextView tvOrderDate;
        TextView tvDeliveryDate;
        TextView tvTotalPrice;
        // Add other TextViews here if you include them in item_order.xml

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvDeliveryDate = itemView.findViewById(R.id.tvDeliveryDate);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            // Initialize other TextViews
        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single order item
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // Get the Order object for the current position
        Order currentOrder = orderList.get(position);

        // Bind data to the views
        holder.tvOrderId.setText(String.format("Order #%d", currentOrder.getOrderId()));
        holder.tvOrderStatus.setText(String.format("Status: %s", currentOrder.getStatus()));
        holder.tvOrderDate.setText(String.format("Order Date: %s", currentOrder.getOrderDate()));
        holder.tvDeliveryDate.setText(String.format("Delivery Date: %s", currentOrder.getDeliveryDate()));
        holder.tvTotalPrice.setText(String.format("Total Price: %d VND", currentOrder.getTotalPrice()));
        // Bind other data if necessary
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the order list
        return orderList.size();
    }

    /**
     * Utility method to update the data in the adapter.
     * @param newOrderList The new list of Order objects.
     */
    public void updateData(List<Order> newOrderList) {
        this.orderList = newOrderList;
        notifyDataSetChanged(); // Notify RecyclerView to refresh its display
    }
}