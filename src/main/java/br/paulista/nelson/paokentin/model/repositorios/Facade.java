package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.SQLException;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Pao;

public class Facade {
  private static Facade myself = null;
  private InterfacePao repoPao = null;

  private Facade() {
    this.repoPao = new RepositorioPao();
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

  public Pao ler(String tipo) throws SQLException {
    return this.repoPao.ler(tipo);
  }

  public List<Pao> lerTodos() throws SQLException {
    return this.repoPao.lerTodos();
  }
}
