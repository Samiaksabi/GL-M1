function string(e){
    return '"' + e + '"';
}

function login(s){
    $(location).attr('href',"home.html");
}

function form(){
    $("#login_form").submit(function(e){ // On sélectionne le formulaire par son identifiant
	e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
	var username = $("#username").val();
	var password = $("#password").val();
	var url = "/ws/login/" + username + "/" + password;
	console.log(url);
	getServerData(url,login);
    });
}

$( document ).ready(function() {
    form();
});
