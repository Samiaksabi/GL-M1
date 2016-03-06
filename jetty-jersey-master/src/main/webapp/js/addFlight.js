
$("#template_anchor").submit(function(e){ // On sélectionne le formulaire par son identifiant

    e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
    var donnees = $(this).serialize(); // On créer une variable contenant le formulaire sérialisé
    addServerData("http://localhost:8080/ws/flight",donnees);

});