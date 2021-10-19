
function loadRoomPage() {
    var light = document.getElementById('light').value;
    var body = document.getElementById('body');

    if (light == "true") {
        body.style.backgroundColor = "white";
        document.getElementById("bulb").src="/resources/icons/yellow.png";
    } else {
        body.style.backgroundColor = "black";
        document.getElementById("bulb").src="/resources/icons/white.png";
    }
}

function loadHomePage() {
    var message = document.getElementById('message').value;

    if (message != ''){
        alert(message);
    }
}

function getUserCountry(country) {
    let items = document.getElementsByClassName('userCountry');
    for (let item of items) {
        item.value = YMaps.location.country;
    }

    if (country != YMaps.location.country) {
        alert("Эта комната недоступна в вашем регионе");
    }
}

