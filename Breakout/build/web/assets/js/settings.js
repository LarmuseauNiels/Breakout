function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

$('body').keydown(function (e) {
  keynum = e.which;
  
  
  switch (keynum) {
    case 13: 
      name = 'return';
      break;
    case 32:
      name = 'spacebar';
      break;
    case 37:
      name = 'left arrow';
      break;
    case 38:
      name = 'up arrow';
      break;
    case 39:
      name = 'right arrow';
      break;
    case 40:
      name = 'down arrow';
      break;
    default: 
      name = String.fromCharCode(keynum);
  }
  
  if($('#left').is(':hover')) { 
  $('#left .keyname').text(name);
  $('#left .code').text(keynum);
  setCookie("leftkey", keynum, 1);
  
  };
  if($('#right').is(':hover')) { 
  $('#right .keyname').text(name);
  $('#right .code').text(keynum);
  setCookie("rightkey", keynum, 1);
  };
  if($('#left2').is(':hover')) { 
  $('#left2 .keyname').text(name);
  $('#left2 .code').text(keynum);
  setCookie("leftkey2", keynum, 1);
  
  };
  if($('#right2').is(':hover')) { 
  $('#right2 .keyname').text(name);
  $('#right2 .code').text(keynum);
  setCookie("rightkey2", keynum, 1);
  };
});


