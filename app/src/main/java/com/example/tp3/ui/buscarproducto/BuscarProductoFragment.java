package com.example.tp3.ui.buscarproducto;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp3.R;
import com.example.tp3.databinding.FragmentBuscarProductoBinding;


public class BuscarProductoFragment extends Fragment {
    private FragmentBuscarProductoBinding binding;
    private BuscarProductoViewModel viewModel;

    public static BuscarProductoFragment newInstance() {
        return new BuscarProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBuscarProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(BuscarProductoViewModel.class);

        viewModel.getMutableMensajeError().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvMensaje.setText(mensaje);
            binding.tvMensaje.setTextColor(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
            );
        });

        viewModel.getMutableProducto().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_modificar_producto, bundle);
                viewModel.clearProducto(); // Limpiar el producto despué de navegar
                viewModel.clearMensajeError(); // Limpiar msj error
                binding.etId.setText(""); // Limpiar el et de id
            }
        });

        binding.btnBuscar.setOnClickListener(vista ->{
            viewModel.getProducto(binding.etId.getText().toString());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}