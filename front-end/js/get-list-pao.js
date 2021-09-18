const FALLBACK_PAGE = "index.html";

function getListPao() {
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:8080/pao";
  xhr.open("get", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      const listPao = JSON.parse(xhr.responseText);
      console.log(listPao)
      validateListPao(listPao);
    } else if (xhr.readyState === 4) {
      alert("Erro ao recuperar a lista de paes.");
      location.href = FALLBACK_PAGE;
    }
  };
  xhr.send();
}

function validateListPao(listPao) {
  if (listPao.length === 0) {
    alert("Nao existem paes cadastrados.");
    location.href = FALLBACK_PAGE;
  } else {
    appendAllPao(listPao);
  }
}

function appendAllPao(listPao) {
  let div = document.getElementById("container");
  for (let index = 0; index < listPao.length; index++) {
    const button = document.createElement("button");
    button.setAttribute("id", listPao[index].tipo);
    button.setAttribute("onclick", "postFornada(this)");
    button.textContent = listPao[index].tipo;
    div.appendChild(button);
  }
}

getListPao();
