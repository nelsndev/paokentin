package br.paulista.nelson.paokentin.model.classes;

public class Fornada {
  private int id;
  private long tempoInicio;
  private long tempoFim;
  private Pao pao;
  
  public Fornada() {
    this.tempoInicio = System.currentTimeMillis();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getTempoInicio() {
    return tempoInicio;
  }

  public void setTempoInicio(long tempoInicio) {
    this.tempoInicio = tempoInicio;
  }

  public long getTempoFim() {
    return tempoFim;
  }

  public void setTempoFim(long tempoFim) {
    this.tempoFim = tempoFim;
  }

  public Pao getPao() {
    return pao;
  }

  public void setPao(Pao pao) {
    this.pao = pao;
  }
}
