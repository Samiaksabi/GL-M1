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
	"url": "/ws/flight/" + data.identifier,
	"edit_id":data.identifier
    });
    $("#template_anchor").append(html);
}

function logout_form(){
    $("#logout_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	logout();
    });
}
//only works with .xls files
function upload(){
    var formData = new FormData();
    formData.append('file', $('#inputFile')[0].files[0]);
    $.ajax({
	url : '/ws/flight/uploadxls',
	type : 'POST',
	data : formData,
	processData: false,  // tell jQuery not to process the data
	contentType: false,  // tell jQuery not to set contentType
	success : function(data) {
	    location.reload();
//            console.log(data);
//            alert(data);
	}
    });
}

$( document ).ready(function() {
    logout_form();
    flightListTemplate = _.template($('#flightListTemplate').html());
    getServerData("/ws/flight",flightTempGen);
});
