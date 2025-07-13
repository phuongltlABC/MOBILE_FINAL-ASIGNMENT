package com.example.adapters; // Ensure this package matches your project structure

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R; // Ensure you have R.layout.item_review
import com.example.models.Review; // Ensure this import path matches your Review class

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    private List<Review> reviewList;

    // Constructor to initialize the adapter with context and data
    public ReviewAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    /**
     * ViewHolder class to hold references to the views in each item.
     */
    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView tvCustomerId;
        TextView tvProductId;
        TextView tvRating;
        TextView tvComment;
        TextView tvReviewDate;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerId = itemView.findViewById(R.id.tvCustomerId);
            tvProductId = itemView.findViewById(R.id.tvProductId);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvReviewDate = itemView.findViewById(R.id.tvReviewDate);
        }
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single review item
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        // Get the Review object for the current position
        Review currentReview = reviewList.get(position);

        // Bind data to the views
        holder.tvCustomerId.setText(String.format("Customer ID: %d", currentReview.getCustomerId()));
        holder.tvProductId.setText(String.format("Product ID: %d", currentReview.getProductId()));
        holder.tvRating.setText(String.format("⭐ %d.0", currentReview.getRating())); // Assuming rating is out of 5
        holder.tvComment.setText(currentReview.getComment());
        holder.tvReviewDate.setText(String.format("Reviewed on: %s", currentReview.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the review list
        return reviewList.size();
    }

    /**
     * Utility method to update the data in the adapter.
     * @param newReviewList The new list of Review objects.
     */
    public void updateData(List<Review> newReviewList) {
        this.reviewList = newReviewList;
        notifyDataSetChanged(); // Notify RecyclerView to refresh its display
    }
}