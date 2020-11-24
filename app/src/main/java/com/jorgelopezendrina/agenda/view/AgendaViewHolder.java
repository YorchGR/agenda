package com.jorgelopezendrina.agenda.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jorgelopezendrina.agenda.R;


public class AgendaViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvNombre;

    public AgendaViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvNombre = itemView.findViewById(R.id.tvNombreContacto);
    }

    public void bind(String text) {
        tvNombre.setText(text);
    }

    static AgendaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacto, parent, false);
        return new AgendaViewHolder(view);
    }
}
