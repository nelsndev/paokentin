function longToHourAndMinute(long) {
  let date = new Date(long);
  let hourAndMinute = date.toLocaleTimeString("pt-BR", {hour: "2-digit", minute: "2-digit"});
  return hourAndMinute;
}

function createDivFornada(fornada) {
  let span1 = document.createElement("span");
  span1.textContent = fornada.pao.tipo;
  let span2 = document.createElement("span");
  span2.textContent = longToHourAndMinute(fornada.tempoInicio);
  let span3 = document.createElement("span");
  span3.textContent = longToHourAndMinute(fornada.tempoFim);
  let div = document.createElement("div");
  div.appendChild(span1);
  div.appendChild(span2);
  div.appendChild(span3);
  return div;
}

function appendAllFornada(listFornada) {
  let div = document.getElementById("container");
  for (let index = 0; index < listFornada.length; index++) {
    let fornada = createDivFornada(listFornada[index]);
    div.appendChild(fornada);
  }
}

function getListFornada() {
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:8080/fornada";
  xhr.open("get", url, true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      const listFornada = JSON.parse(xhr.responseText);
      appendAllFornada(listFornada);
    } else if (xhr.readyState === 4) {
      alert("Erro ao recuperar a lista de fornadas.");
      location.href = "index.html";
    }
  };
  xhr.send();
}

getListFornada();
