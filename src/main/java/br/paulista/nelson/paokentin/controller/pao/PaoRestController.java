package br.paulista.nelson.paokentin.controller.pao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.paulista.nelson.paokentin.model.classes.Pao;
import br.paulista.nelson.paokentin.model.repositorios.Facade;

@RestController
public class PaoRestController {
  
  @CrossOrigin(origins = "*")
  @PostMapping("/pao")
  public void salvar(@RequestBody Pao pao) {
    try {
      Facade.getInstance().salvar(pao);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Erro ao inserir o objeto pao.");
    }
  }
  
  @CrossOrigin(origins = "*")
  @GetMapping("/pao/{tipo}")
  public Pao ler(@PathVariable("tipo") String tipo) {
    try {
      return Facade.getInstance().ler(tipo);
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
          "Erro ao recuperar o objeto pao.");
    }
  }
  
  @CrossOrigin(origins = "*")
  @GetMapping("/pao")
  public List<Pao> lerTodos() {
    try {
      return Facade.getInstance().lerTodosPao();
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
          "Erro ao recuperar a lista de objetos pao.");
    }
  }
}
