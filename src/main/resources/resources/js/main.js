
var my_ajax_req ={
    updInterval: 100,
    url: '/updateRoomData?nameOfRoom=' + document.getElementById('nameOfRoom').value,
    init: function(){
        var self = my_ajax_req;
        setInterval($.proxy(my_ajax_req.requestData, self), self.updInterval);
    },

    requestData: function(){
        var self = my_ajax_req;

        $.ajax({
            url: self.url,
            type: 'GET',
            dataType: 'json',
            success: function(data){ self.update(data) },
            error: function(data){ self.error(data) },
        });
    },

    update: function(Data){
        var self = my_ajax_req;
        var body = document.getElementById('body');

        if (Data.lightOn === true) {
            body.style.backgroundColor = "white";
            document.getElementById("bulb").src="/resources/icons/yellow.png"
        } else {
            body.style.backgroundColor = "black";
            document.getElementById("bulb").src="/resources/icons/white.png"
        }
    },

    error: function(Data){
        var self = my_ajax_req;
        console.log('Failed to get data');
    },
}

function load() {
    my_ajax_req.init();
}

function changeLight() {
    $.ajax({
        method: "GET",
        url: '/changeLight?nameOfRoom=' + document.getElementById('nameOfRoom').value,
        dataType: "json",
        success: function() {},
        error: function(er) {
            console.log(er);
        }
    });
}



