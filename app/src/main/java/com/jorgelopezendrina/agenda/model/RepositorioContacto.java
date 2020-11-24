package com.jorgelopezendrina.agenda.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jorgelopezendrina.agenda.AgendaApplication;
import com.jorgelopezendrina.agenda.model.dao.AgendaDao;
import com.jorgelopezendrina.agenda.model.entity.Contacto;
import com.jorgelopezendrina.agenda.model.room.AgendaDatabase;

import java.util.List;

/**
 * Clse intermedia encargada de ejecutar las consultas que se crean en la clase Dao.
 * Todas las sentencias que se realizan, son creadas en métodos que se ejecutarán sobre
 * hebras, con la peculiaridad de que el objeto LiveData, no necesita de ninguna hebra, ya que por
 * su naturaleza, hace uso de una de manera automática, para realizar sus funciones.  Para ello usa
 * el método observe, que hace que siempre esté pendiente a cualquier cambio que se produzca.
 * */

public class RepositorioContacto {

    private AgendaDatabase agendaDb;
    private AgendaDao agendaDao;
    private LiveData<List<Contacto>> listaContactosLive;

    public RepositorioContacto(Application context) {
        agendaDb = AgendaDatabase.getDb(context);
        agendaDao = agendaDb.getDao();
        listaContactosLive = agendaDao.getAllContactos();
    }

    public LiveData<List<Contacto>> getListaContactosLive() {
        return listaContactosLive;
    }

    public void insert(Contacto contacto) {
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                agendaDao.insert(contacto);
            }
        });
    }

    public void update(Contacto contacto) {
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                agendaDao.upodate(contacto);
            }
        });
    }

    public void delete(Contacto contacto) {
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                agendaDao.delete(contacto);
            }
        });
    }

    public void getId(long id) {
        AgendaApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                agendaDao.getId(id);
            }
        });
    }
}
