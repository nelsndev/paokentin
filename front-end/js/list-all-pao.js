function appendAllPao(listPao) {
  let div = document.getElementById("container");
  for (let index = 0; index < listPao.length; index++) {
    let button = document.createElement("button");
    button.setAttribute("id", listPao[index].tipo);
    button.innerHTML = listPao[index].tipo;
    div.appendChild(button);
  }
}

function getListPao() {
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:8080/pao";
  xhr.open("get", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let listPao = JSON.parse(xhr.responseText);
      appendAllPao(listPao);
    } else if (xhr.readyState === 4) {
      alert("Erro ao recuperar a lista de paes.");
      location.href = "index.html";
    }
  };
  xhr.send();
}

getListPao();
