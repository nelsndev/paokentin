package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Pao;

public class RepositorioPao implements InterfacePao {
  
  RepositorioPao() {
    
  }
  
  @Override
  public void salvar(Pao pao) throws SQLException {
    String sql = "insert into pao (tipo, tempoDePreparo) values (?,?)";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
    pstm.setString(1, pao.getTipo());
    pstm.setInt(2, pao.getTempoDePreparo());
    pstm.execute();
  }
  
  @Override
  public Pao ler(String tipo) throws SQLException {
    String sql = "select * from pao where tipo=?";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
    pstm.setString(1, tipo);
    ResultSet result = pstm.executeQuery();
    Pao pao = null;
    
    if (result.next()) {
      pao = new Pao();
      pao.setTipo(tipo);
      pao.setTempoDePreparo(result.getInt("tempoDePreparo"));
    }
    
    return pao;
  }
  
  @Override
  public List<Pao> lerTodos() throws SQLException {
    String sql = "select * from pao order by tipo";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
    ResultSet result = pstm.executeQuery();
    List<Pao> paes = new ArrayList<Pao>();
    
    while (result.next()) {
      Pao pao = new Pao();
      pao.setTipo(result.getString("tipo"));
      pao.setTempoDePreparo(result.getInt("tempoDePreparo"));
      paes.add(pao);
    }
    
    return paes;
  }
}
