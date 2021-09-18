function addCountdownTimer() {
  const listSpan = document.getElementsByClassName("countdownTimer");
  for (let index = 0; index < listSpan.length; index++) {
    const tempoFinal = Number(listSpan[index].getAttribute("data-tempoFim"));
    const tempoAtual = new Date().getTime();
    const tempoRestante = tempoFinal - tempoAtual;

    const dias = Math.floor(tempoRestante / (1000 * 60 * 60 * 24));
    const horas = Math.floor(tempoRestante / (1000 * 60 * 60));
    const minutos = Math.floor(tempoRestante / (1000 * 60));
    const segundos = Math.floor(tempoRestante / 1000);

    const d = dias;
    const h = horas - dias * 24;
    const m = minutos - horas * 60;
    const s = segundos - minutos * 60;

    listSpan[index].textContent = `${format(m)}:${format(s)}`;

    if (tempoRestante < 0) {
      listSpan[index].textContent = "Pronta!";
      listSpan[index].style.color = "#556b2f";
    }
  }
}

function format(time) {
  return (time >= 10) ? `${time}` : `0${time}`;
}

setInterval(addCountdownTimer, 1000);
