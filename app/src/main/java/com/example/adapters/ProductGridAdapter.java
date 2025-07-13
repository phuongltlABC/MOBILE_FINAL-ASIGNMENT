package com.example.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.models.Product;
import com.example.myassignmentproject.R;

import java.text.DecimalFormat;
import java.util.List;

public class ProductGridAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private LayoutInflater inflater;

    public ProductGridAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getProductId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductGridViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_item_gridview, parent, false);
            holder = new ProductGridViewHolder();
            holder.imgPhoto = convertView.findViewById(R.id.imvPhoto);
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);
            convertView.setTag(holder);
        } else {
            holder = (ProductGridViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);
        holder.txtName.setText(product.getProductName());

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = formatter.format(product.getPrice()) + " VND";
        holder.txtPrice.setText(formattedPrice);

        if (product.getImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
            holder.imgPhoto.setImageBitmap(bitmap);
        } else {
            holder.imgPhoto.setImageResource(R.drawable.produ_lamp);
        }

        return convertView;
    }

    static class ProductGridViewHolder {
        ImageView imgPhoto;
        TextView txtName;
        TextView txtPrice;
    }
}
