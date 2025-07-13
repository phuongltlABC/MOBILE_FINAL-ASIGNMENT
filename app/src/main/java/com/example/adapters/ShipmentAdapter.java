package com.example.adapters; // Ensure this package matches your project structure

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R; // Ensure you have R.layout.item_shipment_method
import com.example.models.ShipmentMethod; // Ensure this import path matches your ShipmentMethod class

import java.util.List;

public class ShipmentAdapter extends RecyclerView.Adapter<ShipmentAdapter.ShipmentViewHolder> {

    private Context context;
    private List<ShipmentMethod> shipmentMethodList;

    public ShipmentAdapter(Context context, List<ShipmentMethod> shipmentMethodList) {
        this.context = context;
        this.shipmentMethodList = shipmentMethodList;
    }

    /**
     * ViewHolder holds the views for a single item in the RecyclerView.
     */
    public static class ShipmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvShipmentName;
        TextView tvShipmentDescription;
        TextView tvDeliveryFee;
        TextView tvDeliveryTime;
        TextView tvDeliveryStatus;

        public ShipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShipmentName = itemView.findViewById(R.id.tvShipmentName);
            tvShipmentDescription = itemView.findViewById(R.id.tvShipmentDescription);
            tvDeliveryFee = itemView.findViewById(R.id.tvDeliveryFee);
            tvDeliveryTime = itemView.findViewById(R.id.tvDeliveryTime);
            tvDeliveryStatus = itemView.findViewById(R.id.tvDeliveryStatus);
        }
    }

    @NonNull
    @Override
    public ShipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        View view = LayoutInflater.from(context).inflate(R.layout.item_shipment_method, parent, false);
        return new ShipmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipmentViewHolder holder, int position) {
        // Get the ShipmentMethod object at the current position
        ShipmentMethod currentShipment = shipmentMethodList.get(position);

        // Bind data from the object to the ViewHolder's views
        holder.tvShipmentName.setText(currentShipment.getShipmentName());
        holder.tvShipmentDescription.setText(currentShipment.getDescription());
        holder.tvDeliveryFee.setText(String.format("Delivery Fee: %.2f đ", currentShipment.getDeliveryFee()));
        holder.tvDeliveryTime.setText(String.format("Estimated Time: %.1f days", currentShipment.getDeliveryTime()));

        // Display active status
        String status = currentShipment.getDeliveryStatus() == 1 ? "Active" : "Inactive";
        holder.tvDeliveryStatus.setText(String.format("Status: %s", status));
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the list
        return shipmentMethodList.size();
    }

    /**
     * Utility method to update the Adapter's data.
     * @param newShipmentMethodList The new list of ShipmentMethod objects.
     */
    public void updateData(List<ShipmentMethod> newShipmentMethodList) {
        this.shipmentMethodList = newShipmentMethodList;
        notifyDataSetChanged(); // Notify the RecyclerView to refresh its display
    }
}