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
	"view_id":data.identifier
    });
    $("#template_anchor").append(html);
}

function logout_form(){
    $("#logout_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	logout();
    });
}

$( document ).ready(function() {
    logout_form();
    flightListTemplate = _.template($('#flightListTemplate').html());
    getServerData("/ws/" + $.cookie("username") +"/flight",flightTempGen);
});
