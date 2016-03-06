
function getServerData(url,success){
    $.ajax({
	dataType : "json",
	url: url
    }).done(success);
}

function addServerData(url,data){
    $.ajax({
	type: 'PUT',
	contentType: 'application/json',
	url: url,
	data: JSON.stringify(data)
    });
}

function editServerData(url,data){
    $.ajax({
	type: "POST",
	url: url,
	data: JSON.stringify(data),
	contentType: "application/json"
    });
}
