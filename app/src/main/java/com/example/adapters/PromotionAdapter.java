package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentproject.R;
import com.example.models.Promotion;

import java.util.List;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {

    private List<Promotion> promotionList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Promotion promotion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PromotionAdapter(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promotion, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        Promotion promotion = promotionList.get(position);
        holder.promotionName.setText(promotion.getPromotionName());
        holder.promotionDescription.setText(promotion.getDescription());
        holder.promotionDates.setText("Từ " + promotion.getStartDate() + " đến " + promotion.getEndDate());
        holder.promotionDiscount.setText("Giảm: " + promotion.getDiscountValue() +
                (promotion.getDiscountType().equalsIgnoreCase("Percentage") ? "% OFF" : " VNĐ"));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null && holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                listener.onItemClick(promotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    public void updatePromotions(List<Promotion> newPromotions) {
        this.promotionList.clear();
        this.promotionList.addAll(newPromotions);
        notifyDataSetChanged();
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {
        TextView promotionName, promotionDescription, promotionDates, promotionDiscount;

        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);
            promotionName = itemView.findViewById(R.id.text_promotion_name);
            promotionDescription = itemView.findViewById(R.id.text_promotion_description);
            promotionDates = itemView.findViewById(R.id.text_promotion_dates);
            promotionDiscount = itemView.findViewById(R.id.text_promotion_discount);
        }
    }
}
