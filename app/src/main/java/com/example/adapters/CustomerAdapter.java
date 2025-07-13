package com.example.adapters; // Ensure this package matches your project structure

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R; // Ensure you have R.layout.item_customer and R.drawable.ic_default_avatar
import com.example.models.Customer; // Ensure this import path matches your Customer class

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private Context context;
    private List<Customer> customerList;

    // Constructor to initialize the adapter with context and data
    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    /**
     * ViewHolder class to hold references to the views in each item.
     */
    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCustomerAvatar;
        TextView tvCustomerName;
        TextView tvCustomerEmail;
        TextView tvCustomerPhone;
        TextView tvCustomerCredit;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCustomerAvatar = itemView.findViewById(R.id.ivCustomerAvatar);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvCustomerEmail = itemView.findViewById(R.id.tvCustomerEmail);
            tvCustomerPhone = itemView.findViewById(R.id.tvCustomerPhone);
            tvCustomerCredit = itemView.findViewById(R.id.tvCustomerCredit);
        }
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single customer item
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        // Get the Customer object for the current position
        Customer currentCustomer = customerList.get(position);

        // Bind data to the views
        holder.tvCustomerName.setText(String.format("%s %s", currentCustomer.getFirstName(), currentCustomer.getLastName()));
        holder.tvCustomerEmail.setText(currentCustomer.getEmail());
        holder.tvCustomerPhone.setText(String.format("Phone: %s", currentCustomer.getPhoneNumber()));
        holder.tvCustomerCredit.setText(String.format("Credit: %d VND", currentCustomer.getCredit()));

        // Set avatar image
        byte[] avatarBytes = currentCustomer.getAvatar();
        if (avatarBytes != null && avatarBytes.length > 0) {
            Bitmap avatarBitmap = BitmapFactory.decodeByteArray(avatarBytes, 0, avatarBytes.length);
            holder.ivCustomerAvatar.setImageBitmap(avatarBitmap);
        } else {
            // Set a default image if no avatar is available
            holder.ivCustomerAvatar.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the customer list
        return customerList.size();
    }

    /**
     * Utility method to update the data in the adapter.
     * @param newCustomerList The new list of Customer objects.
     */
    public void updateData(List<Customer> newCustomerList) {
        this.customerList = newCustomerList;
        notifyDataSetChanged(); // Notify RecyclerView to refresh its display
    }
}