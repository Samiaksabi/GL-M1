function login(user){
    console.log(user);
    if(user.status == "CCO")
	$(location).attr('href',"CCOflightlist.html");
    else if(user.status == "CREW")
	$(location).attr('href',"crewflightlist.html");
    $.cookie("status", user.status);
    $.cookie("username", user.userName);
    $.cookie("pwd", user.password);
}

function logout(){
    $.removeCookie("status");
    $.removeCookie("username");
    $.removeCookie("pwd");
    location.href = "\login.html";
}

function checkLoggedAsCCO(){
    if ($.cookie("status") != "CCO"){
	location.href = "login.html";
	return false;
    }
    else
	return true;
}

function checkLogged(){
    if($.cookie("username") == undefined){
	location.href = "login.html";
	return false;
    }
    else
	return true;
}

function checkLoggedAsCrew(){
    if ($.cookie("status") != "CREW"){
	location.href = "login.html";
	return false;
    }
    else
	return true;
}
