const FALLBACK_PAGE = "index.html";

function getListFornada() {
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:8080/fornada";
  xhr.open("get", url, true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      appendAllFornada(JSON.parse(xhr.responseText));
    } else if (xhr.readyState === 4) {
      alert("Erro ao recuperar a lista de fornadas.");
      location.href = FALLBACK_PAGE;
    }
  };
  xhr.send();
}

function appendAllFornada(listFornada) {
  const div = document.getElementById("container");
  for (let index = 0; index < listFornada.length; index++) {
    div.appendChild(createDivFornada(listFornada[index]));
  }
}

function createDivFornada(fornada) {
  const header = document.createElement("h2");
  header.textContent = `${fornada.pao.tipo}`;
  
  const spanTempoInicio = document.createElement("span");
  spanTempoInicio.textContent = `Iníco: ${formatDateMillisecondsToTime(fornada.tempoInicio)}`;
  
  const spanTempoFim = document.createElement("span");
  spanTempoFim.textContent = `Fim: ${formatDateMillisecondsToTime(fornada.tempoFim)}`;

  const spanCountdownTimer = document.createElement("span");
  spanCountdownTimer.setAttribute("class", "countdownTimer");
  spanCountdownTimer.setAttribute("data-tempoFim", `${fornada.tempoFim}`);

  const div = document.createElement("div");
  div.appendChild(header);
  div.appendChild(spanTempoInicio);
  div.appendChild(spanTempoFim);
  div.appendChild(spanCountdownTimer);
  
  return div;
}

function formatDateMillisecondsToTime(dateMilliseconds) {
  const date = new Date(dateMilliseconds);
  const time = date.toLocaleTimeString("pt-BR", { hour: "2-digit", minute: "2-digit" });
  return time;
}

getListFornada();
