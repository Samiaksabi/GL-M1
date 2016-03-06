
var flightListTemplate;

function flightTempGen(dataArray){
    for(var i = 0; i<dataArray.length;i++)
	hydrateFlightList(dataArray[i],flightListTemplate);
}

function hydrateFlightList(data,template){
    var html = template({
	"commercial_number":JSON.stringify(data.commercial_number),
	"departure_airport":JSON.stringify(data.departure_airport),
	"arrival_airport":JSON.stringify(data.arrival_airport),
	"departure_time":JSON.stringify(data.departure_time),
	"arrival_time":JSON.stringify(data.arrival_time),
	"url": "/ws/flight/" + data.commercial_number
    });
    $("#template_anchor").append(html);
}

$( document ).ready(function() {
    flightListTemplate = _.template($('#flightListTemplate').html());
    getServerData("/ws/flight",flightTempGen);
});
