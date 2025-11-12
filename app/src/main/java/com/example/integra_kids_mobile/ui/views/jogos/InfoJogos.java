package com.example.integra_kids_mobile.ui.views.jogos;

import java.util.Calendar;

public class InfoJogos {
    private Calendar comeco;
    private int duracao;
    private int tentativas, acertos, erros;
    private long infoJogosIdFk, dependenteId;

    public InfoJogos(long infoJogosIdFk, long dependenteId) {
        this.comeco = Calendar.getInstance();
        this.tentativas = 0;
        this.acertos = 0;
        this.erros = 0;
        this.infoJogosIdFk = infoJogosIdFk;
        this.dependenteId = dependenteId;
    }

    public Calendar getComeco() {
        return comeco;
    }

    public void setComeco(Calendar comeco) {
        this.comeco = comeco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
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

    public long getInfoJogosIdFk() {
        return infoJogosIdFk;
    }

    public void setInfoJogosIdFk(long infoJogosIdFk) {
        this.infoJogosIdFk = infoJogosIdFk;
    }

    public long getDependenteId() {
        return dependenteId;
    }

    public void setDependenteId(long dependenteId) {
        this.dependenteId = dependenteId;
    }

    public void terminarJogo() {
        this.setDuracao(Calendar.getInstance().compareTo(this.getComeco()));
    }
}
