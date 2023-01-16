package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.SQLException;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Pao;

public interface InterfacePao {

  public void salvar(Pao pao) throws SQLException;

  public Pao ler(String tipo) throws SQLException;

  public List<Pao> lerTodos() throws SQLException;
}
