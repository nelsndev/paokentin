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
      location.reload();
    } else if (xhr.readyState === 4) {
      alert("Erro ao cadastrar o item.");
      location.reload();
    }
  };
  const objectJSON = formToObjectJSON(form);
  xhr.send(objectJSON);
});

function formToObjectJSON(form) {
  const formData = new FormData(form);
  let object = {};
  for (let [key, value] of formData) {
    object[key] = value;
  }
  return JSON.stringify(object);
}
