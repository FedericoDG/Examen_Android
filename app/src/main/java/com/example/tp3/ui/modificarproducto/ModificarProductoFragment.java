package com.example.tp3.ui.modificarproducto;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp3.R;
import com.example.tp3.databinding.FragmentModificarProductoBinding;
import com.example.tp3.models.Producto;

public class ModificarProductoFragment extends Fragment {
    private FragmentModificarProductoBinding binding;
    private ModificarProductoViewModel viewmodel;

    public static ModificarProductoFragment newInstance() {
        return new ModificarProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentModificarProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewmodel = new ViewModelProvider(this).get(ModificarProductoViewModel.class);

        Producto producto = getArguments().getSerializable("producto") != null ? (Producto) getArguments().getSerializable("producto") : new Producto();

        binding.etId.setText(producto.getId());
        binding.etId.setEnabled(false);
        binding.etDescripcion.setText(producto.getDescripcion());
        binding.etPrecio.setText(String.valueOf(producto.getPrecio()));

        binding.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewmodel.editarProducto(binding.etId.getText().toString(),
                        binding.etDescripcion.getText().toString(),
                        binding.etPrecio.getText().toString());
            }
        });

        viewmodel.getMensajeExito().observe(getViewLifecycleOwner(), mensanje -> {
            binding.tvMensaje.setText(mensanje);
            binding.tvMensaje.setTextColor(getResources().getColor(R.color.teal_700));

            binding.etId.setText("");
            binding.etDescripcion.setText("");
            binding.etPrecio.setText("");
            binding.btnModificar.setEnabled(false);

            // Vuelve al fragmento anterior despues de 2 segundo
            binding.tvMensaje.postDelayed(() -> {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }, 2000);

        });

        viewmodel.getMensajeError().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvMensaje.setText(mensaje);
            binding.tvMensaje.setTextColor(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
            );
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}