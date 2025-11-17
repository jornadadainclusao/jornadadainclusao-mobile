package com.example.integra_kids_mobile.adapter;

import android.util.Log;
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

    private List<Partida> listaPartidas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Partida partida);
    }

    public HistoricoAdapter(List<Partida> lista, OnItemClickListener listener) {
        this.listaPartidas = lista;
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
        Partida partida = listaPartidas.get(position);

        Log.d("HistoricoAdapter", "Bind -> Nome: " + partida.getNomeJogo() + ", ID: " + partida.getId());
        holder.textNomeJogo.setText(partida.getNomeJogo() + " (" + partida.getId() + ")");
        holder.textData.setText(partida.getUpdateDate()); // se Partida tiver data, senão pode remover

        holder.btnDeletar.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(partida);
        });
    }

    @Override
    public int getItemCount() {
        return listaPartidas.size();
    }

    // ================= NOVO MÉTODO =================
    public void atualizarLista(List<Partida> novaLista) {
        this.listaPartidas.clear();
        this.listaPartidas.addAll(novaLista);
        notifyDataSetChanged();
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
