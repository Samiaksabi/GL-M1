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

function clearCookies(){
    $.removeCookie("status");
    $.removeCookie("username");
    $.removeCookie("pwd");
    $.removeCookie("edit_id");
    $.removeCookie("view_id");
}

function logout(){
    clearCookies();
    location.href = "login.html";
}

function checkLoggedAsCCO(){
    if ($.cookie("status") != "CCO"){
	logout();
	return false;
    }
    else
	return true;
}

function checkLogged(){
    if($.cookie("username") == undefined){
	logout();
	return false;
    }
    else
	return true;
}

function checkLoggedAsCrew(){
    if ($.cookie("status") != "CREW"){
	logout();
	return false;
    }
    else
	return true;
}
