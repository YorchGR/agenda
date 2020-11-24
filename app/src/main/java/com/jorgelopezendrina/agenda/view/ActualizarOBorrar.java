package com.jorgelopezendrina.agenda.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.jorgelopezendrina.agenda.R;
import com.jorgelopezendrina.agenda.model.entity.Contacto;
import com.jorgelopezendrina.agenda.model.entity.ValidaDatos;
import com.jorgelopezendrina.agenda.viewmodel.AgendaViewModel;
import java.util.Calendar;

public class ActualizarOBorrar extends AppCompatActivity {

    private TextView tvTlf, tvNomb, tvApll, tvLoc, tvCall, tvNum, fecha;
    private TextInputLayout telefOut, nombOut, apllOut, localOut, calleOut, numeroOut;
    private Button btEditar, btReset, btBorrar;
    private CalendarView cal;
    private String date = "01/01/1900";
    private Contacto contAux;
    private AgendaViewModel agviewModel;
    private Intent intent;

    private MaterialAlertDialogBuilder alertaBorrado() {
        MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(ActualizarOBorrar.this);
        alertDialog.setTitle("Eliminar");
        alertDialog.setIcon(android.R.drawable.ic_delete);
        alertDialog.setMessage("¿Quieres eliminar a " + contAux.getNombre() + " ?");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                agviewModel.delete(contAux);
                finish();
            }
        });
        alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return alertDialog;
    }

    private void compruebaCampos() {
        boolean errorNom = true, errorTlf = true, errorApll = true, errorLoc = true, errorcall = true, errorNum = true;
        String nombre = "", apellidos = "", tlf = "", localidad = "", calle = "", numero = "";

        if (ValidaDatos.validaCadena(tvNomb.getText().toString())) {
            nombOut.setError("Nombre no valido");
            nombOut.setErrorEnabled(true);
            errorNom = true;
        } else if (errorNom) {
            nombOut.setErrorEnabled(false);
            errorNom = false;
            nombre = tvNomb.getText().toString();
        }

        if (!ValidaDatos.validaTlf(tvTlf.getText().toString())) {
            telefOut.setError("Teléfono no valido");
            telefOut.setErrorEnabled(true);
            errorTlf = true;
        } else if (errorTlf) {
            telefOut.setErrorEnabled(false);
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
            localOut.setError("Localidad no valida");
            localOut.setErrorEnabled(true);
            errorLoc = true;
        } else if (errorLoc) {
            localOut.setErrorEnabled(false);
            errorLoc = false;
            localidad = tvLoc.getText().toString();
        }

        if (ValidaDatos.validaCadena(tvCall.getText().toString())) {
            calleOut.setError("calle no valida");
            calleOut.setErrorEnabled(true);
            errorcall = true;
        } else if (errorcall) {
            calleOut.setErrorEnabled(false);
            errorcall = false;
            calle = tvCall.getText().toString();
        }

        if (ValidaDatos.validaNumero(tvNum.getText().toString())) {
            numeroOut.setError("Número no valido");
            numeroOut.setErrorEnabled(true);
            errorNum = true;
        } else if (errorNum) {
            nombOut.setErrorEnabled(false);
            errorNum = false;
            numero = tvNum.getText().toString();
        }

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !tlf.isEmpty() && !localidad.isEmpty() && !calle.isEmpty() && !numero.isEmpty()) {
            Contacto contactoActualizado = new Contacto(nombre, apellidos, tlf, date, localidad, calle, numero);
            contactoActualizado.setId(contAux.getId());
            agviewModel.update(contactoActualizado);
            startActivity(new Intent(ActualizarOBorrar.this, AgendaActivity.class));
            finish();
        }
    }


    private void colocaContacto() {
        tvNomb.setText(contAux.getNombre());
        tvApll.setText(contAux.getApellidos());
        tvTlf.setText(contAux.getTlf());
        tvLoc.setText(contAux.getLocalidad());
        tvCall.setText(contAux.getCalle());
        tvNum.setText(contAux.getNumero());
        fecha.setText(contAux.getNacimiento());
        long fechalong = convierteFecha(contAux.getNacimiento());
        cal.setDate(fechalong);
    }

    private long convierteFecha(String nacimiento) {
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

    private void init() {
        telefOut = findViewById(R.id.tlfOut);
        tvTlf = findViewById(R.id.tvTelefono);
        nombOut = findViewById(R.id.nomOut);
        tvNomb = findViewById(R.id.tvNombre);
        apllOut = findViewById(R.id.apellidoOut);
        tvApll = findViewById(R.id.tvApellido);
        localOut = findViewById(R.id.locOut);
        tvLoc = findViewById(R.id.tvLocal);
        calleOut = findViewById(R.id.calleAout);
        tvCall = findViewById(R.id.tvCalle);
        numeroOut = findViewById(R.id.numOut);
        tvNum = findViewById(R.id.tvNumero);
        fecha = findViewById(R.id.tvfecha);
        cal = findViewById(R.id.cvEditar);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month = month + 1) + "/" + year;
                fecha.setText(date);
            }
        });
        btReset = findViewById(R.id.resetearBt);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reseteaCampos();
            }
        });
        btEditar = findViewById(R.id.editarBt);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaCampos();
            }
        });
        btBorrar = findViewById(R.id.buttonBorrar);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder al = alertaBorrado();
                al.show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_borrar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        agviewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        intent = getIntent();
        contAux = new Contacto();
        contAux = (Contacto) intent.getSerializableExtra(AgendaAdapter.PRUEBA);
        init();
        colocaContacto();
    }

    private void reseteaCampos() {
        nombOut.setErrorEnabled(false);
        tvNomb.setText("");
        telefOut.setErrorEnabled(false);
        tvTlf.setText("");
        apllOut.setErrorEnabled(false);
        tvApll.setText("");
        localOut.setErrorEnabled(false);
        tvLoc.setText("");
        calleOut.setErrorEnabled(false);
        tvCall.setText("");
        numeroOut.setErrorEnabled(false);
        tvNum.setText("");
    }
}