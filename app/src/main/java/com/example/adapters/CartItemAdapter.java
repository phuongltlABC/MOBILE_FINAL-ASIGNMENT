package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.widget.BaseAdapter;

import com.example.models.CartItem;
import com.example.myassignmentproject.R;

import java.util.List;

public class CartItemAdapter extends BaseAdapter {

    private Context context;
    private List<CartItem> cartItemList;
    private LayoutInflater inflater;

    public CartItemAdapter(Context context, List<CartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<CartItem> getCartItems() {
        return cartItemList;
    }

    public void selectAll(boolean isChecked) {
        for (CartItem item : cartItemList) {
            item.setSelected(isChecked);
        }
        notifyDataSetChanged();
    }

    public void clearAll() {
        cartItemList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CartItem item = cartItemList.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cart, parent, false);
            holder = new ViewHolder();

            holder.txtProductName = convertView.findViewById(R.id.txtProductName);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);
            holder.txtQuantity = convertView.findViewById(R.id.txtQuantity);
            holder.btnIncrease = convertView.findViewById(R.id.btnIncrease);
            holder.btnDecrease = convertView.findViewById(R.id.btnDecrease);
            holder.checkbox = convertView.findViewById(R.id.checkboxItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtProductName.setText("Product #" + item.getProductId());
        holder.txtPrice.setText(String.format("%,.0f₫", item.getPrice()));
        holder.txtQuantity.setText(String.valueOf(item.getQuantity()));
        holder.checkbox.setChecked(item.isSelected());

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
        });

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyDataSetChanged();
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtProductName, txtPrice, txtQuantity;
        Button btnIncrease, btnDecrease;
        CheckBox checkbox;
    }
    public interface OnItemClickListener {
        void onItemClick(CartItem cartItem);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
