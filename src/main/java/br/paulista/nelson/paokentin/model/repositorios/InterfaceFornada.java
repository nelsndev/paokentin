package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.SQLException;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Fornada;

public interface InterfaceFornada {
  public void salvar(Fornada fornada) throws SQLException;

  public Fornada ler(int codigo) throws SQLException;

  public void alterar(int codigo) throws SQLException;

  public void deletar(int codigo) throws SQLException;
  
  public List<Fornada> lerTodos() throws SQLException;
}
