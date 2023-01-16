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
    String query = "INSERT INTO fornada (tipoPao, tempoInicio, tempoFim) VALUES (?, ?, ?)";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(query);
    pstm.setString(1, fornada.getPao().getTipo());
    pstm.setTimestamp(2, new Timestamp(fornada.getTempoInicio()));
    pstm.setTimestamp(3, new Timestamp(fornada.getTempoFim()));
    pstm.execute();
  }

  @Override
  public Fornada lerUltima(String tipoPao) throws SQLException {
    String query = "SELECT * FROM fornada JOIN pao ON (fornada.tipoPao = pao.tipo)"
        + " WHERE id = (SELECT MAX(id) FROM fornada WHERE tipoPao = ?)";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(query);
    pstm.setString(1, tipoPao);
    ResultSet result = pstm.executeQuery();

    Fornada fornada = null;
    if (result.next()) {
      Pao pao = new Pao();
      pao.setTipo(result.getString("tipo"));
      pao.setTempoDePreparo(result.getInt("tempoDePreparo"));

      fornada = new Fornada();
      fornada.setId(result.getInt("id"));
      fornada.setTempoInicio(result.getTimestamp("tempoInicio").getTime());
      fornada.setTempoFim(result.getTimestamp("tempoFim").getTime());
      fornada.setPao(pao);
    }

    return fornada;
  }

  @Override
  public List<Fornada> lerTodos() throws SQLException {
    String query = "SELECT * FROM fornada AS f JOIN pao AS p ON (f.tipoPao = p.tipo) ORDER BY id";
    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(query);
    ResultSet result = pstm.executeQuery();

    List<Fornada> fornadas = new ArrayList<Fornada>();
    while (result.next()) {
      Pao pao = new Pao();
      pao.setTipo(result.getString("tipo"));
      pao.setTempoDePreparo(result.getInt("tempoDePreparo"));

      Fornada fornada = new Fornada();
      fornada.setId(result.getInt("id"));
      fornada.setTempoInicio(result.getTimestamp("tempoInicio").getTime());
      fornada.setTempoFim(result.getTimestamp("tempoFim").getTime());
      fornada.setPao(pao);
      fornadas.add(fornada);
    }

    return fornadas;
  }
}
