package com.jorgelopezendrina.agenda.model.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.jorgelopezendrina.agenda.model.dao.AgendaDao;
import com.jorgelopezendrina.agenda.model.entity.Contacto;
/**
 * Clase abstracta que es en sí, es la base de datos.
 * */
@Database(entities = {Contacto.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract AgendaDao getDao();

    private static volatile AgendaDatabase INSTANCE;

    public static synchronized AgendaDatabase getDb(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AgendaDatabase.class, "AgendaDatabase.sqlite").build();
        }
        return INSTANCE;
    }
}
