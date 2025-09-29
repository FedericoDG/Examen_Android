package com.example.tp3.ui.modificarproducto;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp3.MainActivity;
import com.example.tp3.models.Producto;

public class ModificarProductoViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMensajeError;
    private MutableLiveData<String> mMensajeExito;
    public ModificarProductoViewModel(@NonNull Application application) {
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

    public void editarProducto(String id, String descripcion, String precio){
        boolean datosValidos = validarDatos(id, descripcion, precio);



        if (datosValidos){
            Producto producto = new Producto(id, descripcion, Double.parseDouble(precio));
            int index = MainActivity.listaProductos.indexOf(producto);
            if (index != -1) {
                MainActivity.listaProductos.set(index, producto);
                mMensajeExito.setValue("Producto modificado con exito");
            } else {
                // Se supone que nuinca debería entrar aca...
                mMensajeError.setValue("No se encontro el producto a modificar");
            }
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

        return true;
    }
}