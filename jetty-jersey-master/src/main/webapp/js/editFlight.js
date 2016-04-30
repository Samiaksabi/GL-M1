
function string(e){
    return '"' + e + '"';
}

function form(){
    $("#edit_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	var commercial_number = $("#commercial_number").val();
	var atc_number = $("#atc_number").val();
	var plane_id = $("#plane_id").val();
	var departure_airport = $("#departure_airport").val();
	var arrival_airport = $("#arrival_airport").val();
	var departure_date = $("#departure_date").val();
	var arrival_date = $("#arrival_date").val();
	var json_str =
	    '{"commercial_number":' + string(commercial_number) +
	    ',"ATC_code":'          + string(atc_number) +
	    ',"plane":'             + string(plane_id) +
	    ',"crew_members":[]' +
	    ',"departure_airport":' + string(departure_airport) +
	    ',"arrival_airport":'   + string(arrival_airport) +
	    ',"departure_time":'    + Date.parse(departure_date) +
	    ',"arrival_time":'      + Date.parse(arrival_date) +
	    ',"ofp_url":null' +
	    ',"weather_maps_url":null' +
	    ',"notam":[]}';
	console.log(json_str);
	editServerData("/ws/flight/" + commercial_number + "/edit",json_str);
	$(location).attr('href',"CCOflightlist.html");
    });
}

$( document ).ready(function() {
    form();
});
