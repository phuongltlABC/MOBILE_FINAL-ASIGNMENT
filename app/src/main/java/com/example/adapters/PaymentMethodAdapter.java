package com.example.adapters; // Ensure this package matches your project structure

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R; // Ensure you have R.layout.item_payment_method
import com.example.models.PaymentMethod; // Ensure this import path matches your PaymentMethod class

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

    private Context context;
    private List<PaymentMethod> paymentMethodList;

    // Constructor to initialize the adapter with context and data
    public PaymentMethodAdapter(Context context, List<PaymentMethod> paymentMethodList) {
        this.context = context;
        this.paymentMethodList = paymentMethodList;
    }

    /**
     * ViewHolder class to hold references to the views in each item.
     */
    public static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {
        TextView tvPaymentName;
        TextView tvPaymentType;
        TextView tvPaymentDescription;
        TextView tvTransactionFee;
        TextView tvPaymentStatus;

        public PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPaymentName = itemView.findViewById(R.id.tvPaymentName);
            tvPaymentType = itemView.findViewById(R.id.tvPaymentType);
            tvPaymentDescription = itemView.findViewById(R.id.tvPaymentDescription);
            tvTransactionFee = itemView.findViewById(R.id.tvTransactionFee);
            tvPaymentStatus = itemView.findViewById(R.id.tvPaymentStatus);
        }
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single payment method item
        View view = LayoutInflater.from(context).inflate(R.layout.item_payment_method, parent, false);
        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        // Get the PaymentMethod object for the current position
        PaymentMethod currentPaymentMethod = paymentMethodList.get(position);

        // Bind data to the views
        holder.tvPaymentName.setText(currentPaymentMethod.getPaymentName());
        holder.tvPaymentType.setText(String.format("Type: %s", currentPaymentMethod.getPaymentType()));
        holder.tvPaymentDescription.setText(currentPaymentMethod.getDescription());
        holder.tvTransactionFee.setText(String.format("Transaction Fee: %d %s", currentPaymentMethod.getTransactionFee(), currentPaymentMethod.getCurrency()));
        holder.tvPaymentStatus.setText(String.format("Status: %s", currentPaymentMethod.getPaymentStatus()));
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the payment method list
        return paymentMethodList.size();
    }

    /**
     * Utility method to update the data in the adapter.
     * @param newPaymentMethodList The new list of PaymentMethod objects.
     */
    public void updateData(List<PaymentMethod> newPaymentMethodList) {
        this.paymentMethodList = newPaymentMethodList;
        notifyDataSetChanged(); // Notify RecyclerView to refresh its display
    }
}