function CreateGuest(list){
// Добавить форму создания гостя c использованием JavaSpring
var form = document.createElement("form");
form.setAttribute("method", "post");
form.setAttribute("action", "/CreateGuest");
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "name");
input.setAttribute("placeholder", "Name");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "surname");
input.setAttribute("placeholder", "Surname");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "patronymic");
input.setAttribute("placeholder", "Patronymic");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "phone");
input.setAttribute("placeholder", "Phone");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "email");
input.setAttribute("placeholder", "Email");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "email");
input.setAttribute("placeholder", "Email");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "text");
input.setAttribute("name", "birthday");
input.setAttribute("placeholder", "Birthday");
form.appendChild(input);
var input = document.createElement("input");
input.setAttribute("type", "sibmit");
input.setAttribute("value", "Create");
form.appendChild(input);
list.appendChild(form);

}