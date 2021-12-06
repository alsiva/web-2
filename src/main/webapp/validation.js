let hitDataForm = document.querySelector("#hitDataForm");
let yInput = hitDataForm.querySelector("#yInput");

let floatRegex = /^-?\d+(\.\d+)?$/

let radiusSelector = hitDataForm.querySelector("#rInput");
function getRadius() {
    return Number.parseFloat(radiusSelector.options[radiusSelector.selectedIndex].value)
}

function getX() {
    return Number.parseFloat(hitDataForm.querySelector("#xInput:checked").value)
}

hitDataForm.addEventListener("submit", event => {
    event.preventDefault();

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

    let x = getX()
    let r = getRadius()

    let ratio = r / radius

    sendAjax(x, y, x / ratio, y / ratio, r)
})
