function setupCanvas(canvas) {
    // Get the device pixel ratio, falling back to 1.
    let dpr = window.devicePixelRatio || 1;
    // Get the size of the canvas in CSS pixels.
    let rect = canvas.getBoundingClientRect();
    // Give the canvas pixel dimensions of their CSS
    // size * the device pixel ratio.
    canvas.width = rect.width * dpr;
    canvas.height = rect.height * dpr;
    let ctx = canvas.getContext('2d');
    // Scale all drawing operations by the dpr, so you
    // don't have to worry about the difference.
    ctx.scale(dpr, dpr);
    return ctx;
}

let canvas = document.getElementById('area');
let ctx = setupCanvas(canvas)
const shiftX = 200
const shiftY = 200
const scaleX = 1
const scaleY = -1
ctx.transform(scaleX, 0, 0, scaleY, shiftX, shiftY);
ctx.strokeStyle = '#444444';

let axisSize = 175
let arrowSize = 7
function drawAxes() {
    ctx.fillStyle = '#444'
    ctx.beginPath()
    ctx.font = "14px sans-serif";
    ctx.textAlign = 'center'
    ctx.textBaseline = 'middle'

    //Ось Y
    ctx.lineWidth = 1;
    ctx.moveTo(0, -axisSize);
    ctx.lineTo(0, axisSize);

    ctx.moveTo(-arrowSize, axisSize - arrowSize);
    ctx.lineTo(0, axisSize);
    ctx.lineTo(arrowSize, axisSize - arrowSize);

    ctx.scale(1, -1)
    ctx.fillText("Y", 0, -190);
    ctx.scale(1, -1)

    //Ось X
    ctx.moveTo(-axisSize, 0);
    ctx.lineTo(axisSize, 0);

    ctx.moveTo(axisSize - arrowSize, -arrowSize);
    ctx.lineTo(axisSize, 0);
    ctx.lineTo(axisSize - arrowSize, arrowSize);

    ctx.fillText("X", 190, 1);
    ctx.stroke();
}


function drawArea(radius) {
    ctx.fillStyle = '#09b0e8';

    //rectangle
    ctx.fillRect(0, 0, radius / 2, radius)

    //triangle
    ctx.beginPath()
    ctx.moveTo(0, 0)
    ctx.lineTo(0, -radius / 2)
    ctx.lineTo(radius / 2, 0)
    ctx.fill();

    // circle
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.arc(0, 0, radius, Math.PI, Math.PI * 3 / 2)
    ctx.fill();
}

let serifheight = 5
function drawSerifs(r) {
    ctx.lineWidth = 1;
    ctx.font = "14px sans-serif";
    ctx.fillStyle = "#444"
    ctx.beginPath();

    ctx.textAlign = 'center';
    ctx.textBaseline = 'alphabetic'
    drawSerifX(-r, "-R")
    drawSerifX(-r/2, "-R/2")
    drawSerifX(r/2, "R/2")
    drawSerifX(r, "R")

    ctx.textAlign = 'start';
    ctx.textBaseline = 'middle';
    drawSerifY(-r, "-R")
    drawSerifY(-r/2, "-R/2")
    drawSerifY(r/2, "R/2")
    drawSerifY(r, "R")
    ctx.stroke();
}

function drawSerifX(x, name) {
    ctx.moveTo(x, -serifheight);
    ctx.lineTo(x, serifheight);
    ctx.scale(1, -1)
    ctx.fillText(name, x, serifheight + 15)
    ctx.scale(1, -1)
}
function drawSerifY(y, name) {
    ctx.moveTo(-serifheight, y);
    ctx.lineTo(serifheight, y);

    ctx.scale(1, -1);
    ctx.fillText(name, serifheight + 10, -y)
    ctx.scale(1, -1);
}

let radius = 150;
drawArea(radius)
drawAxes()
drawSerifs(radius)

function drawHit(x, y, doesItHit) {
    ctx.beginPath();
    ctx.arc(x, y, 5, 0, 2 * Math.PI, false);

    ctx.fillStyle = doesItHit ? "#018F27" : "#8F0004"
    ctx.fill();
    ctx.lineWidth = 0.5

    ctx.strokeStyle = '#000'
    ctx.stroke()
}


let radiusSelector = hitDataForm.querySelector("#rInput");
canvas.addEventListener('click', function(event) {
    let canvasX = scaleX * (event.offsetX - shiftX);
    let canvasY = scaleY * (event.offsetY - shiftY);
    let r = Number.parseFloat(radiusSelector.options[radiusSelector.selectedIndex].value)

    let ratio = r / radius
    let x = canvasX * ratio
    let y = canvasY * ratio
    sendAjax(x, y, canvasX, canvasY, r)
})

function sendAjax(x, y, canvasX, canvasY, r) {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open('POST', '', true);
    xmlHttpRequest.setRequestHeader('Accept', 'text/plain')

    xmlHttpRequest.addEventListener('readystatechange', function () {
        if (xmlHttpRequest.readyState !== 4) {
            return
        }

        if (xmlHttpRequest.status === 200) {
            let doesItHit = xmlHttpRequest.responseText.trim() === 'true'
            drawHit(canvasX, canvasY, doesItHit)
            addHitToTable(x, y, r, doesItHit)
        }
    })
    xmlHttpRequest.send(new URLSearchParams({x, y, r}))
}

function addHitToTable(x, y, r, doesItHit) {
    const tbody = document.getElementById("hitDataTable")

    let tdX = document.createElement('td');
    tdX.textContent = x
    let tdY = document.createElement('td');
    tdY.textContent = y
    let tdR = document.createElement('td');
    tdR.textContent = r
    let tdDoesItHit = document.createElement('td');
    tdDoesItHit.textContent = doesItHit ? "true" : "false"

    let tr = document.createElement('tr');
    tr.appendChild(tdX)
    tr.appendChild(tdY)
    tr.appendChild(tdR)
    tr.appendChild(tdDoesItHit)

    tbody.appendChild(tr)
}