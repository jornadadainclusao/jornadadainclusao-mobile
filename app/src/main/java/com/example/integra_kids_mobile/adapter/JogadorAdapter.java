package com.example.integra_kids_mobile.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.model.Jogador;
import com.example.integra_kids_mobile.profile.PerfilTrocarPlayer;

import java.util.List;

public class JogadorAdapter extends RecyclerView.Adapter<JogadorAdapter.JogadorViewHolder> {

    private final List<Jogador> listaJogadores;
    private final Context context;

    public JogadorAdapter(List<Jogador> listaJogadores, Context context) {
        this.listaJogadores = listaJogadores;
        this.context = context;
    }

    @NonNull
    @Override
    public JogadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jogador, parent, false);
        return new JogadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JogadorViewHolder holder, int position) {
        Jogador jogador = listaJogadores.get(position);

        holder.txtNomeJogador.setText(jogador.getNome());
        holder.imgJogador.setImageResource(jogador.getImagemResId());

        // Animação de cor suave (iniciada apenas uma vez)
        if (holder.animator == null) {
            int[] cores = new int[]{
                    context.getColor(R.color.blue),
                    context.getColor(R.color.green),
                    context.getColor(R.color.yellow),
                    context.getColor(R.color.orange),
                    context.getColor(R.color.red),
                    context.getColor(R.color.purple)
            };

            holder.animator = ValueAnimator.ofFloat(0, cores.length - 1);
            holder.animator.setDuration(10000);
            holder.animator.setRepeatCount(ValueAnimator.INFINITE);
            holder.animator.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                int index = (int) Math.floor(value);
                int nextIndex = (index + 1) % cores.length;
                float fraction = value - index;

                int blended = (int) new ArgbEvaluator().evaluate(fraction, cores[index], cores[nextIndex]);
                holder.fundoAnimado.getBackground().setTint(blended);
            });
            holder.animator.start();
        }

        holder.btnSelecionar.setOnClickListener(v -> {
            Intent intent = new Intent(context, PerfilTrocarPlayer.class);
            intent.putExtra("JOGADOR_ID", jogador.getId());
            intent.putExtra("JOGADOR_NOME", jogador.getNome());
            intent.putExtra("JOGADOR_IMAGEM", jogador.getImagemResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaJogadores.size();
    }

    public static class JogadorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgJogador;
        TextView txtNomeJogador;
        Button btnSelecionar;
        LinearLayout fundoAnimado;
        ValueAnimator animator; // para manter referência e evitar múltiplas animações

        public JogadorViewHolder(@NonNull View itemView) {
            super(itemView);
            fundoAnimado = itemView.findViewById(R.id.fundoAnimado);
            imgJogador = itemView.findViewById(R.id.imgJogador);
            txtNomeJogador = itemView.findViewById(R.id.txtNomeJogador);
            btnSelecionar = itemView.findViewById(R.id.btnSelecionar);
        }
    }
}
