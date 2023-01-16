package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.SQLException;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Fornada;
import br.paulista.nelson.paokentin.model.classes.Pao;

public class Facade {
  private static Facade myself = null;
  private InterfacePao repoPao = null;
  private InterfaceFornada repoFornada = null;

  private Facade() {
    this.repoPao = new RepositorioPao();
    this.repoFornada = new RepositorioFornada();
  }

  public static Facade getInstance() {
    if (myself == null) {
      myself = new Facade();
    }

    return myself;
  }

  public void salvar(Pao pao) throws SQLException {
    this.repoPao.salvar(pao);
  }

  public void salvar(Fornada fornada) throws SQLException {
    this.repoFornada.salvar(fornada);
  }

  public Pao ler(String tipo) throws SQLException {
    return this.repoPao.ler(tipo);
  }

  public Fornada lerUltima(String tipo) throws SQLException {
    return this.repoFornada.lerUltima(tipo);
  }

  public List<Pao> lerTodosPao() throws SQLException {
    return this.repoPao.lerTodos();
  }

  public List<Fornada> lerTodosFornada() throws SQLException {
    return this.repoFornada.lerTodos();
  }
}
