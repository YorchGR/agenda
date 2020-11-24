package com.jorgelopezendrina.agenda.view;


import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.jorgelopezendrina.agenda.model.entity.Contacto;


public class AgendaAdapter extends ListAdapter<Contacto, AgendaViewHolder> {

    public static final String PRUEBA = "Prueba";
    private Context context;

    public AgendaAdapter(Context context, @NonNull DiffUtil.ItemCallback<Contacto> diffCallback) {
        super(diffCallback);
        this.context = context;
    }

    public AgendaAdapter(@NonNull AsyncDifferConfig<Contacto> config) {
        super(config);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        Contacto current = getItem(position);
        Contacto contAux = current;
        holder.bind(current.getNombre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActualizarOBorrar.class);
                intent.putExtra(PRUEBA, contAux);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AgendaViewHolder.create(parent);
    }

    public static class AgendaDiff extends DiffUtil.ItemCallback<Contacto> {

        @Override
        public boolean areItemsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}