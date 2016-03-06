
function getServerData(url,success){
    $.ajax({
	dataType : "json",
	url: url
    }).done(success);
}

function addServerData(url,dataObject){
    $.ajax({
	type: 'PUT',
	contentType: 'application/json',
	url: url,
	data: JSON.stringify(dataObject)
    });
}
