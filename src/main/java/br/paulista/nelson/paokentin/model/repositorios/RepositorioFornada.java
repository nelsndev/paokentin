package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.paulista.nelson.paokentin.model.classes.Fornada;
import br.paulista.nelson.paokentin.model.classes.Pao;

public class RepositorioFornada implements InterfaceFornada {
  
  RepositorioFornada() {
    
  }
  
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
    // TODO
  }
  
  @Override
  public Fornada lerUltima(String tipoPao) throws SQLException {
    String sql = "select * from fornada join pao on (fornada.tipoPao = pao.tipo) "
                + "where id = (select max(id) from fornada where tipoPao = ?)";
    
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
    pstm.setString(1, tipoPao);
    ResultSet result = pstm.executeQuery();
    Fornada fornada = null;
    
    if (result.next()) {
      fornada = new Fornada();
      fornada.setId(result.getInt("id"));
      fornada.setTempoInicio(result.getTimestamp("tempoInicio").getTime());
      fornada.setTempoFim(result.getTimestamp("tempoFim").getTime());
      
      Pao pao = new Pao();
      pao.setTipo(result.getString("tipo"));
      pao.setTempoDePreparo(result.getInt("tempoDePreparo"));
      
      fornada.setPao(pao);
    }
    
    return fornada;
  }
  
  @Override
  public List<Fornada> lerTodos() throws SQLException {
    String sql = "select * from fornada as f join pao as p on (f.tipoPao = p.tipo)";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
    ResultSet result = pstm.executeQuery();
    List<Fornada> fornadas = new ArrayList<Fornada>();
    
    while (result.next()) {
      Fornada fornada = new Fornada();
      fornada.setId(result.getInt("id"));
      fornada.setTempoInicio(result.getTimestamp("tempoInicio").getTime());
      fornada.setTempoFim(result.getTimestamp("tempoFim").getTime());
      
      Pao pao = new Pao();
      pao.setTipo(result.getString("tipo"));
      pao.setTempoDePreparo(result.getInt("tempoDePreparo"));
      
      fornada.setPao(pao);
      fornadas.add(fornada);
    }
    
    return fornadas;
  }
}
