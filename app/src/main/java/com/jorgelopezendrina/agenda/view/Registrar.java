package com.jorgelopezendrina.agenda.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.jorgelopezendrina.agenda.R;
import com.jorgelopezendrina.agenda.model.entity.Contacto;
import com.jorgelopezendrina.agenda.model.entity.ValidaDatos;
import com.jorgelopezendrina.agenda.viewmodel.AgendaViewModel;

import java.util.Calendar;

public class Registrar extends AppCompatActivity {

    public static final String CONTACTO = "CONTACTO";
    public static final String ACTUALIZAR = "ACTUALIZAR";
    private TextView tvTlf, tvNombre, tvApll, tvLoc, tvCalle, tvNum, fecha, tvPortada;
    private TextInputLayout tlfOut, nomOut, apllOut, locOut, calleOut, numOut;
    private Button btRegist, btReset;
    private CalendarView cal;
    private String date = "01/01/1900";
    private Contacto contAux;

    private void compruebaCampos() {
        boolean errorNom = true, errorTlf = true, errorApll = true, errorLoc = true, errorcall = true, errorNum = true;
        String nombre = "", apellidos = "", tlf = "", localidad = "", calle = "", numero = "";

        if (ValidaDatos.validaCadena(tvNombre.getText().toString())) {
            nomOut.setError("Nombre no valido");
            nomOut.setErrorEnabled(true);
            errorNom = true;
        } else if (errorNom) {
            nomOut.setErrorEnabled(false);
            errorNom = false;
            nombre = tvNombre.getText().toString();
        }

        if (!ValidaDatos.validaTlf(tvTlf.getText().toString())) {
            tlfOut.setError("Teléfono no valido");
            tlfOut.setErrorEnabled(true);
            errorTlf = true;
        } else if (errorTlf) {
            tlfOut.setErrorEnabled(false);
            errorTlf = false;
            tlf = tvTlf.getText().toString();
        }

        if (ValidaDatos.validaCadena(tvApll.getText().toString())) {
            apllOut.setError("Apellido no valido");
            apllOut.setErrorEnabled(true);
            errorApll = true;
        } else if (errorApll) {
            apllOut.setErrorEnabled(false);
            errorApll = false;
            apellidos = tvApll.getText().toString();
        }

        if (ValidaDatos.validaCadena(tvLoc.getText().toString())) {
            locOut.setError("Localidad no valida");
            locOut.setErrorEnabled(true);
            errorLoc = true;
        } else if (errorLoc) {
            locOut.setErrorEnabled(false);
            errorLoc = false;
            localidad = tvLoc.getText().toString();
        }

        if (ValidaDatos.validaCadena(tvCalle.getText().toString())) {
            calleOut.setError("calle no valida");
            calleOut.setErrorEnabled(true);
            errorcall = true;
        } else if (errorcall) {
            calleOut.setErrorEnabled(false);
            errorcall = false;
            calle = tvCalle.getText().toString();
        }

        if (ValidaDatos.validaNumero(tvNum.getText().toString())) {
            numOut.setError("Número no valido");
            numOut.setErrorEnabled(true);
            errorNum = true;
        } else if (errorNum) {
            nomOut.setErrorEnabled(false);
            errorNum = false;
            numero = tvNum.getText().toString();
        }

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !tlf.isEmpty() && !localidad.isEmpty() && !calle.isEmpty() && !numero.isEmpty()) {
            Contacto contactoAuxiliar = new Contacto(nombre, apellidos, tlf, date, localidad, calle, numero);
            darDeAlta(contactoAuxiliar);
        }
    }


    private Long convierteFecha(String nacimiento) {
        String parts[] = nacimiento.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTimeInMillis();
    }

    private void darDeAlta(Contacto contactoAuxiliar) {
        Intent intent = new Intent(Registrar.this, AgendaActivity.class);
        intent.putExtra(CONTACTO, contactoAuxiliar);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void init() {
        tvPortada = findViewById(R.id.textViewRe);
        tlfOut = findViewById(R.id.outTlf);
        tvTlf = findViewById(R.id.telefonoTv);
        nomOut = findViewById(R.id.outNom);
        tvNombre = findViewById(R.id.nombreTv);
        apllOut = findViewById(R.id.outApll);
        tvApll = findViewById(R.id.ApllTv);
        locOut = findViewById(R.id.outLoc);
        tvLoc = findViewById(R.id.LocTv);
        calleOut = findViewById(R.id.outCalle);
        tvCalle = findViewById(R.id.calleTv);
        numOut = findViewById(R.id.outNum);
        tvNum = findViewById(R.id.numTv);
        fecha = findViewById(R.id.tvfecha);
        cal = findViewById(R.id.calendarView);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month = month + 1) + "/" + year;
                fecha.setText(date);
            }
        });
        btReset = findViewById(R.id.buttonResetear);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reseteaCampos();
            }
        });
        btRegist = findViewById(R.id.button_Registrar);
        btRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaCampos();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        contAux = (Contacto) getIntent().getSerializableExtra(CONTACTO);
        if (contAux != null) {
            tvNombre.setText(contAux.getNombre());
            tvApll.setText(contAux.getApellidos());
            tvTlf.setText(contAux.getTlf());
            tvLoc.setText(contAux.getLocalidad());
            tvCalle.setText(contAux.getCalle());
            tvNum.setText(contAux.getNumero());
            fecha.setText(contAux.getNacimiento());
            Long fechalong = convierteFecha(contAux.getNacimiento());
            cal.setDate(fechalong);
            tvPortada.setText("ACTUALIZAR CONTACTO");
            btRegist.setText("actualizar");
        }
    }

    private void reseteaCampos() {
        nomOut.setErrorEnabled(false);
        tvNombre.setText("");
        tlfOut.setErrorEnabled(false);
        tvTlf.setText("");
        apllOut.setErrorEnabled(false);
        tvApll.setText("");
        locOut.setErrorEnabled(false);
        tvLoc.setText("");
        calleOut.setErrorEnabled(false);
        tvCalle.setText("");
        numOut.setErrorEnabled(false);
        tvNum.setText("");
    }
}