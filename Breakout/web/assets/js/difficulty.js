/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    $("#easy").click(function(event){
      setCookie("difficulty", "e" , 1);
      redirect();
    });
    $("#medium").click(function(event){
        setCookie("difficulty", "m" , 1);
      redirect();
    });
    $("#hard").click(function(event){
        setCookie("difficulty", "h" , 1);
      redirect();
    });
});

var redirect = function(){
    window.location = "/Breakout/classic";
};


function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}