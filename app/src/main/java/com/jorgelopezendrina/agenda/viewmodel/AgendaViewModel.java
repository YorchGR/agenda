package com.jorgelopezendrina.agenda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jorgelopezendrina.agenda.model.RepositorioContacto;
import com.jorgelopezendrina.agenda.model.entity.Contacto;

import java.util.List;

/**
 * Es la clase que ejecuta las sentencias del repositorio.
 * */
public class AgendaViewModel extends AndroidViewModel {

    private RepositorioContacto repositorioContacto;
    private LiveData<List<Contacto>> listaContactos;

    public AgendaViewModel(@NonNull Application application) {
        super(application);
        repositorioContacto = new RepositorioContacto(application);
        listaContactos = repositorioContacto.getListaContactosLive();
    }

    public void delete(Contacto contacto) {
        repositorioContacto.delete(contacto);
    }

    public void getId(long id) {
        repositorioContacto.getId(id);
    }

    public LiveData<List<Contacto>> getListaContactos() {
        return listaContactos;
    }

    public void insert(Contacto contacto) {
        repositorioContacto.insert(contacto);
    }

    public void update(Contacto contacto) {
        repositorioContacto.update(contacto);
    }
}
