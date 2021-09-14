function postFornada(button) {
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:8080/fornada/" + button.id;
  xhr.open("post", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      alert("Fornada cadastrada com sucesso.");
    } else if (xhr.readyState === 4) {
      alert("Erro ao cadastrar a fornada.");
    }
  };
  xhr.send(JSON.stringify({})); // Obs
}

/* Obs.:
 * O back-end foi escrito de modo que nao e necessario enviar os dados do 
 * objeto fornada, por isso estou enviando um JSON vazio.
 */
