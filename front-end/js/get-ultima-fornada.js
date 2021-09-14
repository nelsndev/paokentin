function getUltimaFornada(button) {
  const xhr = new XMLHttpRequest();
  const url = "http://localhost:8080/fornada/" + button.id;
  xhr.open("get", url, true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      const ultimaFornada = JSON.parse(xhr.responseText);
      openModal(ultimaFornada);
    } else if (xhr.readyState === 4) {
      alert("Erro ao recuperar a última fornada.");
    }
  };
  xhr.send();
}

function openModal(ultimaFornada) {
  let header = document.createElement("h2");
  header.textContent = "Fornada";
  let span1 = document.createElement("span");
  span1.textContent = ultimaFornada.pao.tipo;
  let span2 = document.createElement("span");
  span2.setAttribute("id", "countdownTimer");
  span2.setAttribute("data-tempoFim", `${ultimaFornada.tempoFim}`);
  let closeButton = document.createElement("button");
  closeButton.setAttribute("onclick", "closeModal()");
  closeButton.textContent = "Fechar";

  let modal = document.createElement("div");
  modal.setAttribute("id", "modal");
  modal.appendChild(header);
  modal.appendChild(span1);
  modal.appendChild(span2);
  modal.appendChild(closeButton);
  document.querySelector("body").appendChild(modal);

  setInterval(addCountdownTimer, 1000);
}

function closeModal() {
  document.getElementById("modal").remove();
}

function addCountdownTimer() {
  let span = document.getElementById("countdownTimer");
  let tempoFinal = Number(span.getAttribute("data-tempoFim"));
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

  let minString = m >= 1 ? `${m}` : `0${m}`;
  let secString = s >= 10 ? `${s}` : `0${s}`;
  span.textContent = `${minString}:${secString}`;

  if (tempoRestante < 0) {
    span.textContent = "Pronta!";
  }
}
