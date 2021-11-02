// noinspection UnnecessaryReturnStatementJS

let hitDataForm = document.querySelector("#hitDataForm");
let yInput = hitDataForm.querySelector("#yInput");

let floatRegex = /^-?\d+(\.\d+)?$/

hitDataForm.addEventListener("submit", event => {
    let yAsStr = yInput.value.trim()

    if(!floatRegex.test(yAsStr)) {
        event.preventDefault();
        alert("y: " + yAsStr + " не является числом");
        return
    }

    let y = parseFloat(yAsStr);

    if (!(-5 < y && y < 3)) {
        event.preventDefault();
        alert("y: " + yAsStr + " не лежит в диапазоне (-5;3)")
        return
    }
})
