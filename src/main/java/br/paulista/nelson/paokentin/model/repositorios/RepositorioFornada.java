package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Fornada;

public class RepositorioFornada implements InterfaceFornada {
  
  @Override
  public void salvar(Fornada fornada) throws SQLException {
    String sql = "insert into fornada (tipoPao, tempoInicio, tempoFim) "
        + "values (?, ?, ?)";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
    pstm.setString(1, fornada.getPao().getTipo());
    pstm.setTimestamp(2, new Timestamp(fornada.getTempoInicio()));
    pstm.setTimestamp(3, new Timestamp(fornada.getTempoFim()));
    pstm.execute();
  }
  
  @Override
  public void deletar(int codigo) throws SQLException {
    
  }
  
  @Override
  public Fornada lerUltima(String tipoPao) throws SQLException {
    
    return null;
  }
  
  @Override
  public List<Fornada> lerTodos() throws SQLException {
    
    return null;
  }
}
