// INCLUDE FORNADAS.HTML FILE

function includeFornadas() {
  let allElementsList = document.getElementsByTagName("*");
  for (let index = 0; index < allElementsList.length; index++) {
    let currentElement = allElementsList[index];
    if (currentElement.hasAttribute("data-include-fornadas")) {
      const htmlFileName = currentElement.getAttribute("data-include-fornadas");
      const xhr = new XMLHttpRequest();
      xhr.open("get", htmlFileName, true);
      xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
          currentElement.innerHTML = xhr.responseText;
          console.log(xhr.responseText);
        } else if (xhr.readyState === 4) {
          currentElement.innerHTML = "<p>Página não encontrada.</p>";
        }
        currentElement.removeAttribute("data-include-fornadas");
        includeFornadas();
      }
      xhr.send();
      return;
    }
  }
}

// GET LIST FORNADAS

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

function appendAllFornada(listFornada) {
  let div = document.getElementById("container");
  for (let index = 0; index < listFornada.length; index++) {
    let fornada = createDivFornada(listFornada[index]);
    div.appendChild(fornada);
  }
}

function createDivFornada(fornada) {
  let header = document.createElement("h2");
  header.textContent = "Fornada";
  
  let spanPaoTipo = document.createElement("span");
  spanPaoTipo.textContent = fornada.pao.tipo;
  
  let spanCountdownTimer = document.createElement("span");
  spanCountdownTimer.setAttribute("class", "countdownTimer");
  spanCountdownTimer.setAttribute("data-tempoFim", `${fornada.tempoFim}`);
  
  let spanTempoInicio = document.createElement("span");
  spanTempoInicio.textContent = `Iníco: ${formatDateMillisecondsToTime(fornada.tempoInicio)}`;
  
  let spanTempoFim = document.createElement("span");
  spanTempoFim.textContent = `Fim: ${formatDateMillisecondsToTime(fornada.tempoFim)}`;

  let div = document.createElement("div");
  div.appendChild(header);
  div.appendChild(spanPaoTipo);
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

// COUNTDOWN TIMER:

function addCountdownTimer() {
  let listSpan = document.getElementsByClassName("countdownTimer");
  for (let index = 0; index < listSpan.length; index++) {
    let tempoFinal = Number(listSpan[index].getAttribute("data-tempoFim"));
    let tempoAtual = new Date().getTime();
    let tempoRestante = tempoFinal - tempoAtual;

    let dias = Math.floor(tempoRestante / (1000 * 60 * 60 * 24));
    let horas = Math.floor(tempoRestante / (1000 * 60 * 60));
    let minutos = Math.floor(tempoRestante / (1000 * 60));
    let segundos = Math.floor(tempoRestante / 1000);

    let d = dias;
    let h = horas - dias * 24;
    let m = minutos - horas * 60;
    let s = segundos - minutos * 60;

    let minString = (m >= 1) ? `${m}` : `0${m}`;
    let secString = (s >= 10) ? `${s}` : `0${s}`;
    listSpan[index].textContent = `${minString}:${secString}`;

    if (tempoRestante < 0) {
      listSpan[index].textContent = "Pronta!";
    }
  }
}

// FUNCTIONS' EXCUTION:

includeFornadas();
getListFornada();
setInterval(addCountdownTimer, 1000);
