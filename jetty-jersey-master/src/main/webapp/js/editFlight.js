
var ofpIsSet = false;
var wmIsSet = false;

function string(e){
    return '"' + e + '"';
}

function submit_form(){
    $("#edit_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	var ofp_url = null;
	var weather_maps_url = null;

	if($('#ofpInputFile')[0].files[0] != undefined){
	    uploadOfp();
	    ofpIsSet = true;
	}
	if(ofpIsSet){
	    ofp_url = "/data/" + $.cookie("edit_id") + "_ofp.txt";
	    ofp_url = string(ofp_url);
	}

	if($('#weatherMapInputFile')[0].files[0] != undefined){
	    uploadWeatherMap();
	    wmIsSet = true;
	}
	if(wmIsSet){
	    weather_maps_url = "/data/" + $.cookie("edit_id") + "_weather_map.jpg";
	    weather_maps_url = string(weather_maps_url);
	}


	var commercial_number = $("#commercial_number").val();
	var atc_number = $("#atc_number").val();
	var plane_id = $("#plane_id").val();
	var departure_airport = $("#departure_airport").val();
	var arrival_airport = $("#arrival_airport").val();
	var departure_date = $("#departure_date").val();
	var arrival_date = $("#arrival_date").val();
	var json_str =
	    '{' +
	    '"identifier":'         + string($.cookie("edit_id")) +
	    ',"commercial_number":' + string(commercial_number) +
	    ',"ATC_code":'          + string(atc_number) +
	    ',"plane":'             + string(plane_id) +
	    ',"crew_members":'      + crewMembersToString() +
	    ',"departure_airport":' + string(departure_airport) +
	    ',"arrival_airport":'   + string(arrival_airport) +
	    ',"departure_time":'    + Date.parse(departure_date) +
	    ',"arrival_time":'      + Date.parse(arrival_date) +
	    ',"ofp_url":'           + ofp_url +
	    ',"weather_maps_url":'  + weather_maps_url +
	    ',"notam":[]}';
	console.log(json_str);
	editServerData("/ws/flight/" + $.cookie("edit_id") + "/edit",json_str);
	$(location).attr('href',"CCOflightlist.html");
    });
}

function uploadOfp(){
    var formData = new FormData();
    formData.append('file', $('#ofpInputFile')[0].files[0]);
    var url = '/ws/flight/' + $.cookie('edit_id') + '/uploadofp';
    $.ajax({
	url : url,
	type : 'POST',
	data : formData,
	processData: false,  // tell jQuery not to process the data
	contentType: false,  // tell jQuery not to set contentType
	success : function(data) {
	}
    });
}

function uploadWeatherMap(){
    var formData = new FormData();
    formData.append('file', $('#weatherMapInputFile')[0].files[0]);
    var url = '/ws/flight/' + $.cookie('edit_id') + '/uploadweathermap';
    $.ajax({
	url : url,
	type : 'POST',
	data : formData,
	processData: false,  // tell jQuery not to process the data
	contentType: false,  // tell jQuery not to set contentType
	success : function(data) {
	}
    });
}

function logout_form(){
    $("#logout_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	logout();
    });
}

function input(value,id){
    return '<input class="form-control" type="text" value=' + string(value) + 'id=' + id + '>';
}

function fillForm(flight){
    var html = input(flight.commercial_number,"commercial_number");
    $("#commercial_number_td").append(html);
    var html = input(flight.ATC_code,"atc_number");
    $("#atc_number_td").append(html);
    var html = input(flight.plane,"plane_id");
    $("#plane_id_td").append(html);
    var html = input(flight.departure_airport,"departure_airport");
    $("#departure_airport_td").append(html);
    var html = input(flight.arrival_airport,"arrival_airport");
    $("#arrival_airport_td").append(html);
    var html = input(new Date(flight.departure_time).toUTCString(),"departure_date");
    $("#departure_date_td").append(html);
    var html = input(new Date(flight.arrival_time).toUTCString(),"arrival_date");
    $("#arrival_date_td").append(html);
    for(var i = 0; i<flight.crew_members.length;i++)
	getServerData("/ws/crew/"+flight.crew_members[i],addCrew);

    if(flight.ofp_url != null){
	var html = '<a href=' + flight.ofp_url + '>OFP</a>';
	$("#ofp_td").append(html);
	ofpIsSet = true;
    }
    var html = '<input id="ofpInputFile" type="file">';
    $("#ofp_td").append(html);

    if(flight.weather_maps_url != null){
	var html = '<a href=' + flight.weather_maps_url + '>Weather Map</a>';
	$("#weather_map_td").append(html);
	wmIsSet = true;
    }
    var html = '<input id="weatherMapInputFile" type="file">';
    $("#weather_map_td").append(html);
}



var currentCrew = [];
var count = 0;
var crewListTemplate;

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


$( document ).ready(function() {
    crewListTemplate = _.template($('#crewListTemplate').html());
    logout_form();
    //form();
    getServerData("/ws/flight/" + $.cookie("edit_id"),fillForm);
});
