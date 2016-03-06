
function getServerData(url){
    $.ajax({
	dataType: "json",
	url: url
    }).done();
}
