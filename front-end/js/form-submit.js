function formToObjectJSON(form) {
  const formData = new FormData(form);
  let object = {};
  for (let [key, value] of formData) {
    object[key] = value;
  }
  return JSON.stringify(object);
}

document.querySelector("form").addEventListener("submit", function (event) {
  event.preventDefault();
  const form = document.querySelector("form");
  const xhr = new XMLHttpRequest();
  const url = form.getAttribute("action");
  xhr.open("post", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      alert("Item cadastrado com sucesso!");
      location.href = form.getAttribute("data-redirect");
    } else if (xhr.readyState === 4) {
      alert("Erro ao cadastrar o item.");
      location.href = form.getAttribute("data-redirect");
    }
  };
  const objectJSON = formToObjectJSON(form);
  xhr.send(objectJSON);
});
