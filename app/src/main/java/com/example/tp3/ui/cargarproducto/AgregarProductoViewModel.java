package com.example.tp3.ui.cargarproducto;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp3.MainActivity;
import com.example.tp3.models.Producto;

public class AgregarProductoViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMensajeError;
    private MutableLiveData<String> mMensajeExito;
    public AgregarProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMensajeError() {
        if (mMensajeError == null) {
            mMensajeError = new MutableLiveData<>();
        }

        return mMensajeError;
    }

    public LiveData<String> getMensajeExito() {
        if (mMensajeExito == null) {
            mMensajeExito = new MutableLiveData<>();
        }

        return mMensajeExito;
    }

    public void agregarProducto(String id, String descripcion, String precio){
        boolean datosValidos = validarDatos(id, descripcion, precio);



        if (datosValidos){
            double precioValido;

            try {
                precioValido = Double.parseDouble(precio);
            } catch (NumberFormatException e) {
                mMensajeError.setValue("El precio debe ser un numero valido");
                return;
            }

            MainActivity.agregarProducto(new Producto(id, descripcion, precioValido));
            mMensajeExito.setValue("¡Producto agregado con éxito!");
        }
    }

    private boolean validarDatos(String id, String descripcion, String precio){
        if (id == null || id.isEmpty()) {
            mMensajeError.setValue("El ID no puede estar vacio");
            return false;
        }

        if (descripcion == null || descripcion.isEmpty()) {
            mMensajeError.setValue("La descripcion no puede estar vacia");
            return false;
        }

        if (precio == null || precio.isEmpty()) {
            mMensajeError.setValue("El precio debe ser mayor a 0");
            return false;
        }

        if (MainActivity.listaProductos.contains(new Producto(id, "", 0))) {
            mMensajeError.setValue("Ya existe un producto con ese ID");
            return false;
        }

        return true;
    }
}