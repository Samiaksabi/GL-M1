
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
	data: data
    });
}

function editServerData(url,data){
    $.ajax({
	type: "POST",
	url: url,
	data: data,
	contentType: "application/json"
    });
}

function deleteServerData(url){
    $.ajax({
	type: "DELETE",
	url: url
    });
}
