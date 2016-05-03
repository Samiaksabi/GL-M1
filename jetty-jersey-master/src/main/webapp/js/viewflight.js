
function string(e){
    return '"' + e + '"';
}

function logout_form(){
    $("#logout_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	logout();
    });
}

function output(data){
    return data;
}

function fill(flight){
    var html = output(flight.commercial_number);
    $("#commercial_number_td").append(html);
    var html = output(flight.ATC_code);
    $("#atc_number_td").append(html);
    var html = output(flight.plane);
    $("#plane_id_td").append(html);
    var html = output(flight.departure_airport);
    $("#departure_airport_td").append(html);
    var html = output(flight.arrival_airport);
    $("#arrival_airport_td").append(html);
    var html = output(new Date(flight.departure_time).toUTCString());
    $("#departure_date_td").append(html);
    var html = output(new Date(flight.arrival_time).toUTCString());
    $("#arrival_date_td").append(html);
    for(var i = 0; i<flight.crew_members.length;i++)
	getServerData("/ws/crew/"+flight.crew_members[i],addCrew);
    if(flight.ofp_url != null){
	var html = '<a href=' + flight.ofp_url + '>OFP</a>';
	$("#ofp_td").append(html);
    }
    else{
	var html = 'None';
	$("#ofp_td").append(html);
    }
}

var crewListTemplate;

function hydrateCrewList(data,template){
    var html = template({
	"name":data.firstName + " " + data.lastName,
	"profession":data.crewStatus
    });
    $("#template_anchor").append(html);
}

function addCrew(crew){
    if(crew != undefined){
	hydrateCrewList(crew, crewListTemplate);
    }
}


$( document ).ready(function() {
    crewListTemplate = _.template($('#crewListTemplate').html());
    logout_form();
    getServerData("/ws/flight/" + $.cookie("view_id"),fill);
});
