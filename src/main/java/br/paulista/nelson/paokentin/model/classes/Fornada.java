package br.paulista.nelson.paokentin.model.classes;

public class Fornada {
  private int id;
  private Pao pao;
  private boolean isFinalizada;
  private long tempoInicio;
  private long tempoFim;
  
  public Fornada() {
    this.tempoInicio = System.currentTimeMillis();
    this.tempoFim = this.tempoInicio + (this.pao.getTempoDePreparo() * 60000); // Convertendo minutos para milisegundos
    this.isFinalizada = false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public Pao getPao() {
    return pao;
  }

  public void setPao(Pao pao) {
    this.pao = pao;
  }
  
  public boolean isFinalizada() {
    return isFinalizada;
  }

  public void setFinalizada(boolean isFinalizada) {
    this.isFinalizada = isFinalizada;
  }

  public long getTempoInicio() {
    return tempoInicio;
  }

  public long getTempoFim() {
    return tempoFim;
  }
}
