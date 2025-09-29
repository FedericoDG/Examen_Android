package com.example.tp3.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tp3.MainActivity;
import com.example.tp3.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getListaProductos().observe(getViewLifecycleOwner(), productos -> {
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false );
            binding.lista.setLayoutManager(glm);

            ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), inflater);
            binding.lista.setAdapter(adapter);
        });

        viewModel.cargarProdutos();

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Productos", "onResume: " + MainActivity.listaProductos.size());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}