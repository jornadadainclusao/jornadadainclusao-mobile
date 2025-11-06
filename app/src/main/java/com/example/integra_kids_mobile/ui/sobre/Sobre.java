package com.example.integra_kids_mobile.ui.sobre;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.integra_kids_mobile.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.integra_kids_mobile.databinding.SobreBinding;
import com.example.integra_kids_mobile.utils.EnviarSheetMonkey;

public class Sobre extends Fragment {

    private SobreBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SobreViewModel sobreViewModel = new ViewModelProvider(this).get(SobreViewModel.class);

        binding = SobreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnContact = root.findViewById(R.id.btnContact);
        btnContact.setOnClickListener(v -> {
            // Infla o layout do formulário
            View dialogView = getLayoutInflater().inflate(R.layout.sobre_formulario, null);

            // Cria o AlertDialog com o layout customizado
            AlertDialog dialog = new AlertDialog.Builder(getContext())
                    .setView(dialogView)
                    .create();

            // Referência aos campos e botão
            EditText campoNome = dialogView.findViewById(R.id.campoNome);
            EditText campoEmail = dialogView.findViewById(R.id.campoEmail);
            EditText campoMsg = dialogView.findViewById(R.id.campoMensagem);
            Button btnSalvar = dialogView.findViewById(R.id.btnSalvar);

            // Ação do botão "Salvar"
            btnSalvar.setOnClickListener(view -> {
                String nome = campoNome.getText().toString().trim();
                String email = campoEmail.getText().toString().trim();
                String mensagem = campoMsg.getText().toString().trim();

                if (mensagem.isEmpty() || nome.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostra o modal de loading
                    View loadingView = getLayoutInflater().inflate(R.layout.loading, null);
                    ImageView imgLoading = loadingView.findViewById(R.id.imgLoading);

                    AlertDialog loadingDialog = new AlertDialog.Builder(getContext())
                            .setView(loadingView)
                            .setCancelable(false)
                            .create();

                    Glide.with(getContext())
                            .asGif()
                            .load(R.drawable.loading)
                            .into(imgLoading);

                    loadingDialog.show();

                    // Chama a classe que envia os dados
                    EnviarSheetMonkey.enviarDados(getContext(), nome, email, mensagem, new EnviarSheetMonkey.Callback() {
                        @Override
                        public void onSuccess() {
                            requireActivity().runOnUiThread(() -> {
                                loadingDialog.dismiss();
                                Toast.makeText(getContext(), "Formulário enviado com sucesso!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            });
                        }
                        @Override
                        public void onError() {
                            requireActivity().runOnUiThread(() -> {
                                loadingDialog.dismiss();
                                Toast.makeText(getContext(), "Erro ao enviar formulário.", Toast.LENGTH_SHORT).show();
                            });
                        }
                    });
                }
            });
            dialog.show();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
