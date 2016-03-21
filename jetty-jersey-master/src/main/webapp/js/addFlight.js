

function string(e){
    return '"' + e + '"';
}

function form(){
    $("#add_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	var commercial_number = $("#commercial_number").val();
	var atc_number = $("#atc_number").val();
	var plane_id = $("#plane_id").val();
	var departure_airport = $("#departure_airport").val();
	var arrival_airport = $("#arrival_airport").val();
	var departure_date = $("#departure_date").val();
	//var departure_time = $("#departure_time").val();
	var arrival_date = $("#arrival_date").val();
	//var arrival_time = $("#arrival_time").val();
	/*var json = {"commercial_number":string(commercial_number),
		   "ATC_code":string(atc_number),
		   "plane":string(plane_id),
		   "crew_members":[],
		   "departure_airport":string(departure_airport),
		   "arrival_airport":string(arrival_airport),
		   }*/
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
	//var donnees = $("#add_form").serialize(); // On créer une variable contenant le formulaire sérialisé
	console.log(json_str);
	var json = JSON.parse(json_str);
	addServerData("/ws/flight",json);
	$(location).attr('href',"flightlist.html");
    });
}

$( document ).ready(function() {
    form();
});
