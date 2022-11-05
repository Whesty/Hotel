function WriteList(list){
    //Создание выпадаюшего списка
    var select = document.createElement("select");
    select.setAttribute("id", "select");
    select.setAttribute("name", "select");
    select.setAttribute("onchange", "WriteTable()");
    document.body.appendChild(select);
    //Заполнение выпадаюшего списка
    for (var i = 0; i < list.length; i++) {
        var option = document.createElement("option");
        option.setAttribute("value", list[i]);
        option.innerHTML = list[i];
        select.appendChild(option);
    }
    return select;
}