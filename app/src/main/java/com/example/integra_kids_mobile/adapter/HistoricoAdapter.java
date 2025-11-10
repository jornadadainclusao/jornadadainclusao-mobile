package com.example.integra_kids_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.model.Partida;

import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private final List<Partida> partidas;
    private final OnDeleteClickListener listener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Partida partida);
    }

    public HistoricoAdapter(List<Partida> partidas, OnDeleteClickListener listener) {
        this.partidas = partidas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historico_partida, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partida partida = partidas.get(position);
        holder.textNomeJogo.setText(partida.getNomeJogo());
        holder.textData.setText(partida.getData());

        holder.btnDeletar.setOnClickListener(v -> listener.onDeleteClick(partida));
    }

    @Override
    public int getItemCount() {
        return partidas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNomeJogo, textData;
        Button btnDeletar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNomeJogo = itemView.findViewById(R.id.textNomeJogo);
            textData = itemView.findViewById(R.id.textData);
            btnDeletar = itemView.findViewById(R.id.btnDeletar);
        }
    }
}
