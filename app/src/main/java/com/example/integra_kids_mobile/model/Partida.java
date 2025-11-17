package com.example.integra_kids_mobile.model;

public class Partida {
    private int id;
    private double tempoTotal;
    private int tentativas;
    private int acertos;
    private int erros;
    private String updateDate; // ← adicionar
    private InfoJogo infoJogos_id_fk; // ← adicionar isso
    // outros campos se houver, como 'dependente'

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getTempoTotal() { return tempoTotal; }
    public void setTempoTotal(double tempoTotal) { this.tempoTotal = tempoTotal; }

    public int getTentativas() { return tentativas; }
    public void setTentativas(int tentativas) { this.tentativas = tentativas; }

    public int getAcertos() { return acertos; }
    public void setAcertos(int acertos) { this.acertos = acertos; }

    public int getErros() { return erros; }
    public void setErros(int erros) { this.erros = erros; }

    public String getUpdateDate() { return updateDate; }
    public void setUpdateDate(String updateDate) { this.updateDate = updateDate; }

    public InfoJogo getInfoJogos_id_fk() { return infoJogos_id_fk; }
    public void setInfoJogos_id_fk(InfoJogo infoJogos_id_fk) { this.infoJogos_id_fk = infoJogos_id_fk; }

    // método para retornar nome do jogo
    public String getNomeJogo() {
        if (infoJogos_id_fk != null) {
            return infoJogos_id_fk.getNome();
        }
        return "Jogo desconhecido";
    }
}
