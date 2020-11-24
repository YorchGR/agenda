package com.jorgelopezendrina.agenda.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.jorgelopezendrina.agenda.R;
import com.jorgelopezendrina.agenda.model.entity.Contacto;
import com.jorgelopezendrina.agenda.viewmodel.AgendaViewModel;

import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    private Button btRegistrar;
    private AgendaViewModel agViewModel;
    private Contacto contRegistrar, contUpdate;
    public static final int ACTIVITY_REQUEST_CODE = 1;
    private LiveData<List<String>> listatlf;

    private void init() {
        btRegistrar = findViewById(R.id.buttonRegistrar);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaActivity.this, Registrar.class);
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.AgendaRecyclerView);
        final AgendaAdapter adapter = new AgendaAdapter(getApplicationContext(), new AgendaAdapter.AgendaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        agViewModel.getListaContactos().observe(this, new Observer<List<Contacto>>() {
            @Override
            public void onChanged(List<Contacto> contactos) {
                adapter.submitList(contactos);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                contRegistrar = (Contacto) data.getSerializableExtra(Registrar.CONTACTO);
                agViewModel.insert(contRegistrar);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agViewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        init();
    }
}