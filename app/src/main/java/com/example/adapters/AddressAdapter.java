package com.example.adapters; // Ensure this package matches your project structure

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R; // Ensure you have R.layout.item_address_info
import com.example.models.AddressInfo; // Ensure this import path matches your AddressInfo class

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Context context;
    private List<AddressInfo> addressInfoList;

    // Constructor to initialize the adapter with context and data
    public AddressAdapter(Context context, List<AddressInfo> addressInfoList) {
        this.context = context;
        this.addressInfoList = addressInfoList;
    }

    /**
     * ViewHolder class to hold references to the views in each item.
     */
    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView tvDeliveryName;
        TextView tvAddressDetails;
        TextView tvContactNumber;
        TextView tvEmail;
        TextView tvDeliveryFee;
        TextView tvIsDefault;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeliveryName = itemView.findViewById(R.id.tvDeliveryName);
            tvAddressDetails = itemView.findViewById(R.id.tvAddressDetails);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvDeliveryFee = itemView.findViewById(R.id.tvDeliveryFee);
            tvIsDefault = itemView.findViewById(R.id.tvIsDefault);
        }
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single address item
        View view = LayoutInflater.from(context).inflate(R.layout.item_address_info, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        // Get the AddressInfo object for the current position
        AddressInfo currentAddress = addressInfoList.get(position);

        // Bind data to the views
        holder.tvDeliveryName.setText(currentAddress.getDeliveryName());
        holder.tvAddressDetails.setText(currentAddress.getAddress());
        holder.tvContactNumber.setText(String.format("Phone: %s", currentAddress.getContactNumber()));
        holder.tvEmail.setText(String.format("Email: %s", currentAddress.getEmail()));
        holder.tvDeliveryFee.setText(String.format("Delivery Fee: %d VND", currentAddress.getDeliveryFee()));

        String defaultStatus = currentAddress.getIsDefault() == 1 ? "Yes" : "No";
        holder.tvIsDefault.setText(String.format("Default: %s", defaultStatus));
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the address list
        return addressInfoList.size();
    }

    /**
     * Utility method to update the data in the adapter.
     * @param newAddressInfoList The new list of AddressInfo objects.
     */
    public void updateData(List<AddressInfo> newAddressInfoList) {
        this.addressInfoList = newAddressInfoList;
        notifyDataSetChanged(); // Notify RecyclerView to refresh its display
    }
}