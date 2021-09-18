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
      listSpan[index].style.color = "#556b2f";
    }
  }
}

setInterval(addCountdownTimer, 1000);
