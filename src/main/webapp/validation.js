// noinspection UnnecessaryReturnStatementJS

let hitDataForm = document.querySelector("#hitDataForm");
let xInput = hitDataForm.querySelector("#xInput");
let yInput = hitDataForm.querySelector("#yInput");

let floatRegex = /^-?\d+(\.\d+)?$/

hitDataForm.addEventListener("submit", event => {
    let xAsStr = xInput.value.trim()
    if(!floatRegex.test(xAsStr)) {
        event.preventDefault();
        alert("x: " + xAsStr + " не является числом");
        return
    }

    let x = parseFloat(xAsStr);

    if (-3 < x && x < 3) {
        event.preventDefault();
        alert("x: " + xAsStr + " не лежит в диапазоне (-5;3)")
        return
    }

    let yAsStr = yInput.value.trim()

    if(!floatRegex.test(yAsStr)) {
        event.preventDefault();
        alert("y: " + yAsStr + " не является числом");
        return
    }

    let y = parseFloat(xAsStr);

    if (-3 < y && y < 3) {
        event.preventDefault();
        alert("y: " + yAsStr + " не лежит в диапазоне (-5;3)")
        return
    }
})
