package br.paulista.nelson.paokentin.controller.fornada;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.paulista.nelson.paokentin.model.classes.Fornada;
import br.paulista.nelson.paokentin.model.classes.Pao;
import br.paulista.nelson.paokentin.model.repositorios.Facade;

@RestController
public class FornadaRestController {
  
  @CrossOrigin(origins = "*")
  @PostMapping("/fornada/{tipo}")
  public void salvar(@PathVariable("tipo") String tipo, @RequestBody Fornada fornada) {
    try {
      Pao pao = Facade.getInstance().ler(tipo);
      fornada.setPao(pao);
      // Setando o tempoFim da fornada, convertendo o tempoDePreparo do pao em minutos para milisegundos:
      fornada.setTempoFim(fornada.getTempoInicio() + (fornada.getPao().getTempoDePreparo() * 60000));
      Facade.getInstance().salvar(fornada);
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
          "Erro ao inserir o objeto fornada.");
    }
  }
  
  
}
