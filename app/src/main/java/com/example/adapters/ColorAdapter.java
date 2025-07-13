package com.example.adapters;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.Color; // Thay đổi theo package của bạn
import com.example.myassignmentproject.R; // Thay đổi theo package gốc của ứng dụng bạn

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    private List<Color> colorList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Color color);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ColorAdapter(List<Color> colorList) {
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        Color color = colorList.get(position);
        holder.colorName.setText(color.getColorName());

        if (color.getImageBlob() != null) {
            holder.colorImage.setImageBitmap(BitmapFactory.decodeByteArray(color.getImageBlob(), 0, color.getImageBlob().length));
        } else {
            // Hiển thị một hình ảnh placeholder nếu không có ảnh màu
            holder.colorImage.setImageResource(R.drawable.produ_lamp);
            // Hoặc bạn có thể tạo một hình vuông màu bằng cách lập trình:
            // holder.colorImage.setBackgroundColor(android.graphics.Color.parseColor("#" + Integer.toHexString(color.getColorCode())));
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(color);
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder {
        ImageView colorImage;
        TextView colorName;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            colorImage = itemView.findViewById(R.id.color_image);
            colorName = itemView.findViewById(R.id.text_color_name);
        }
    }

    public void updateColors(List<Color> newColors) {
        this.colorList.clear();
        this.colorList.addAll(newColors);
        notifyDataSetChanged();
    }
}