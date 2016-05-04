

var currentCrew = [];
var count = 0;
var crewListTemplate;

/*function crewTempGen(dataArray){
    for(var i = 0; i<dataArray.length;i++)
	hydrateFlightList(dataArray[i],flightListTemplate);
}*/

function hydrateCrewList(data,template){
    var html = template({
	"name":data.firstName + " " + data.lastName,
	"profession":data.crewStatus,
	"crew_table_id":"\""+ "crew_table_id_" + count +"\"",
	"crew_table_id_removal":"crew_table_id_" + count,
	"id":count-1
    });
    $("#template_anchor").append(html);
}

function addCrew(crew){
    if(crew != undefined){
	currentCrew.push(crew);
	console.log(currentCrew[0]);
	count += 1;
	hydrateCrewList(crew, crewListTemplate);
    }
}

function onCrewSubmit(){
    var res = $("#new_crew").val().split(" ");
    var fn = res[0];
    var ln = res[1];
    console.log(fn + " " + ln);
    var url = "/ws/crew/" + fn + "/" + ln;
    console.log(url);
    getServerData(url,addCrew);
}

function crewMembersToString(){
    var crew = [];
    for(var i = 0; i<currentCrew.length;i++){
	if(currentCrew[i]!=undefined){
	    crew.push(currentCrew[i]);
	}
    }

    var res = "[";
    for(var i = 0; i<crew.length;i++){
	res += string(crew[i].userName);
	    if(i!=crew.length-1)
		res += ",";
    }
    res += "]";
    return res;
}

function string(e){
    return '"' + e + '"';
}

function submit_form(){
    $("#add_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
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
	    ',"crew_members":'      + crewMembersToString() +
	    ',"departure_airport":' + string(departure_airport) +
	    ',"arrival_airport":'   + string(arrival_airport) +
	    ',"departure_time":'    + Date.parse(departure_date) +
	    ',"arrival_time":'      + Date.parse(arrival_date) +
	    ',"ofp_url":null' +
	    ',"weather_maps_url":null' +
	    ',"notam":[]}';
	console.log(json_str);
	addServerData("/ws/flight",json_str);
	$(location).attr('href',"CCOflightlist.html");
    });
}

function logout_form(){
    $("#logout_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	logout();
    });
}

$( document ).ready(function() {
    crewListTemplate = _.template($('#crewListTemplate').html());
    logout_form();
    //form();
});
