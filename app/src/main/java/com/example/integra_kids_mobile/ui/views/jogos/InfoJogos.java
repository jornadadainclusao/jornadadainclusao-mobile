package com.example.integra_kids_mobile.ui.views.jogos;

import java.util.Calendar;

public class InfoJogos {
    private Calendar comeco;
    private int tempoTotal;
    private int tentativas, acertos, erros;
    private long infoJogos_id_fk, dependente;

    public InfoJogos(long infoJogosIdFk, long dependenteId) {
        this.comeco = Calendar.getInstance();
        this.tentativas = 0;
        this.acertos = 0;
        this.erros = 0;
        this.infoJogos_id_fk = infoJogosIdFk;
        this.dependente = dependenteId;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public void comecarJogo() {
        this.comeco = Calendar.getInstance();
    }
    public void terminarJogo() {
        this.tempoTotal = Calendar.getInstance().compareTo(this.comeco);
    }
}
