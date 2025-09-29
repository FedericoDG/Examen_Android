package com.example.tp3.ui.buscarproducto;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp3.MainActivity;
import com.example.tp3.models.Producto;

public class BuscarProductoViewModel extends AndroidViewModel {
    private MutableLiveData<Producto> mProducto;
    private MutableLiveData<String> mMensajeError;
    public BuscarProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getMutableProducto() {
        if (mProducto == null) {
            mProducto = new MutableLiveData<>();
        }

        return mProducto;
    }

    public LiveData<String> getMutableMensajeError() {
        if (mMensajeError == null) {
            mMensajeError = new MutableLiveData<>();
        }

        return mMensajeError;
    }

    public void getProducto(String id) {
        Producto producto = MainActivity.obtenerProductoPorId(id);
        if (producto == null) {
            mMensajeError.setValue("No se encontró el producto con ID: " + id);
        } else{
            mProducto.setValue(producto);
        }
    }

    void clearProducto() {
        if (mProducto != null) {
            mProducto.setValue(null);
        }
    }
    
    void clearMensajeError() {
        if (mMensajeError != null) {
            mMensajeError.setValue("");
        }
    }
}