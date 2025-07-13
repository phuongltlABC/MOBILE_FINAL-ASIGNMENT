package com.example.myassignmentproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adapters.ProductGridAdapter;
import com.example.database.DatabaseHelper;
import com.example.models.Product;
import com.example.myassignmentproject.databinding.FragmentCatalogBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogFragment extends Fragment {
    FragmentCatalogBinding binding;
    private GridView gvProduct;
    private ProductGridAdapter adapter;
    private DatabaseHelper dbHelper;
    private List<Product> productList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CatalogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogFragment newInstance(String param1, String param2) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Sử dụng ViewBinding nếu bạn đã bật nó
        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHelper = new DatabaseHelper(getContext());
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        productList = dbHelper.getAllProducts();
        adapter = new ProductGridAdapter(getContext(), productList);
        binding.gvProduct.setAdapter(adapter);

        // Sự kiện khi nhấn vào 1 item sản phẩm
        binding.gvProduct.setOnItemClickListener((parent, view1, position, id) -> {
            Product selectedProduct = productList.get(position);
            Intent intent = new Intent(getContext(), ProductDetail.class);
            intent.putExtra("product", selectedProduct);
            startActivity(intent);
        });

        return view;
    }
}