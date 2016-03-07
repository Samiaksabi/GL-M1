function string(e){
    return '"' + e + '"';
}

function form(){
    $("#login_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	var username = $("#username").val();
	var password = $("#password").val();
	var url = "/ws/login/" + username + "/" + password;
	getServerData(url,function(){$(location).attr('href',"home.html");});
    });
}

$( document ).ready(function() {
    form();
});
