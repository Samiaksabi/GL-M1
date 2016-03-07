
var flightListTemplate;

function flightTempGen(dataArray){
    for(var i = 0; i<dataArray.length;i++)
	hydrateFlightList(dataArray[i],flightListTemplate);
}

function hydrateFlightList(data,template){
    var html = template({
	"commercial_number":data.commercial_number,
	"departure_airport":data.departure_airport,
	"arrival_airport":data.arrival_airport,
	"departure_time":new Date(data.departure_time).toUTCString(),
	"arrival_time":new Date(data.arrival_time).toUTCString(),
	"url": "/ws/flight/" + data.commercial_number
    });
    $("#template_anchor").append(html);
}

$( document ).ready(function() {
    flightListTemplate = _.template($('#flightListTemplate').html());
    getServerData("/ws/flight",flightTempGen);
});
