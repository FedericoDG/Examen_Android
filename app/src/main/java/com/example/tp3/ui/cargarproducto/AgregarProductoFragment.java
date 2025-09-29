package com.example.tp3.ui.cargarproducto;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp3.R;
import com.example.tp3.databinding.FragmentAgregarProductoBinding;

public class AgregarProductoFragment extends Fragment {
    private FragmentAgregarProductoBinding binding;
    private AgregarProductoViewModel viewmodel;

    public static AgregarProductoFragment newInstance() {
        return new AgregarProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAgregarProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewmodel = new ViewModelProvider(this).get(AgregarProductoViewModel.class);

        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewmodel.agregarProducto(binding.etId.getText().toString(),
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