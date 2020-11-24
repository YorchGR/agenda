package com.jorgelopezendrina.agenda;

import com.jorgelopezendrina.agenda.view.AgendaActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AgendaApplication extends AgendaActivity {

    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService threadExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS + 2);
}
