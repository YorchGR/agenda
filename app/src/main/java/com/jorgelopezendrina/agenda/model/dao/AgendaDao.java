package com.jorgelopezendrina.agenda.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.jorgelopezendrina.agenda.model.entity.Contacto;

import java.util.List;

/**
 * Interfaz que tiene las consultas que se van a realizar sobre la base de datos
 * */
@Dao
public interface AgendaDao {

    @Delete
    void delete(Contacto contacto);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contacto contacto);

    @Query("select * from contacto order by nombre")
    LiveData<List<Contacto>> getAllContactos();


    @Query("select * from contacto where id = :id")
    Contacto getId(long id);

    @Update
    void upodate(Contacto contacto);
}
