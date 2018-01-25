$(function(){
    on();
   socket = new WebSocket("ws://localhost:8080/Breakout/multiplayer");
   socket.onmessage = messagehandler;
   window.addEventListener('keydown',function(e){
        keyState[e.keyCode || e.which] = true;
    },true);    
    window.addEventListener('keyup',function(e){
        keyState[e.keyCode || e.which] = false;
   },true);
   window.addEventListener('keydown',function(e){
        if (e.keyCode === 13) {
            socket.send("start");
        }
    });
   socket.onopen = function(){
        socket.send("load");
        socket.send("uuid "+getCookie("uuid"));
    };
   checkKey();
});
var keyState = {};
var socket;
var images = {};
var debug = false;
var speed = false;
var t1 = 0;
var t0 = 0;
var webhookspeed = function(){
    t1 = performance.now();
    console.log("webhook delay" , (t1 - t0).toFixed(4));
    t0 = t1;
};

var messagehandler = function(evt){
    var data = JSON.parse(evt.data);
    if(debug)console.log(data);
        switch(data['action']){
            case("move"):
               if(speed)webhookspeed();
               moveimages(data.movement);
               updatetime(data.time);
               break;
            case("load"):
                loadimages(data);
                showpressenter();
               break;
            case("reload"):
                loadimages(data);
            case("goal"):
                showgoals(data);
                break;
            case("goal"):
                showgoals(data);
                break;
            case("remblock"):
                remblock(data);
                break;
            case("showtext"):
                showtext(data['text']);
                break;
            case("ufo"):
                ufo();
                break;
            default:
                console.error("101: Comunication error, Command not found");
                break;
        }
};


var checkKey = function() {
   var leftkeycode = getCookie("leftkey");
    if (leftkeycode==="") {leftkeycode = 37;}
    var rightkeycode = getCookie("rightkey");
    if (rightkeycode==="") {rightkeycode = 39;}
    var leftkeycode2 = getCookie("leftkey2");
    if (leftkeycode2==="") {leftkeycode2 = 81;}
    var rightkeycode2 = getCookie("rightkey2");
    if (rightkeycode2==="") {rightkeycode2 = 68;}
    if(keyState[leftkeycode]){socket.send("moveleftp1");}
    if(keyState[rightkeycode]){socket.send("moverightp1");}
    if(keyState[leftkeycode2]){socket.send("moveleftp2"); }
    if(keyState[rightkeycode2]){socket.send("moverightp2");}
    if(keyState[32]){socket.send("stop"); } 
    setTimeout(checkKey, 16);
};  
var loadimages = function(data){
    images.ball = (getimage(data['ball']['size'],data['ball']['size'],"white","Ball"));
    
    images.bpallet = (getimage(data['bpallet']['breete'],30,"blue","Palet"));
    images.tpallet = (getimage(data['tpallet']['breete'],30,"red","Palet"));
    images.blocks = [];    
    var len = data['blocks'].length;
    for (var i = 0; i < len; i++) {
        var brick = {
            img: getbrickimage(data['blocks'][i]['width'],data['blocks'][i]['height'],data['blocks'][i]['collor'],"StandardBrick",data['blocks'][i]['property']), 
            xpos: data['blocks'][i]['pos']['x'], 
            ypos: data['blocks'][i]['pos']['y']
        };
        images.blocks.push(brick);
    }
    
};



var reset = function(){
    images = {};
};


var getimage =  function(width,height,color,type){
    var img = new Image();
    img.src = '/Breakout/'+type+'Source?width='+width+'&height='+height+'&color='+color;
    return {width: width,height:height,img:img};
};
var getbrickimage =  function(width,height,color,type,property){
    var img = new Image();
    img.src = '/Breakout/'+type+'Source?width='+width+'&height='+height+'&color='+color+'&property='+property;
    return {width: width,height:height,img:img};
};
var moveimages = function(mdata){
    var c = document.getElementById("gameCanvas");
    c.width=924;
    c.height=668;
    var ctx = c.getContext("2d");
    moveimage(images.ball,mdata['ball']['x'],mdata['ball']['y'],ctx);
    moveimage(images.bpallet,mdata['bpallet']['x'],mdata['bpallet']['y'],ctx);
    moveimage(images.tpallet,mdata['tpallet']['x'],mdata['tpallet']['y'],ctx); 
    showbricks(ctx);
    
};
var showbricks = function(ctx){
    var l = images.blocks.length;
    for (var i = 0; i < l; i++) {
       var b = images.blocks[i];
       ctx.drawImage(b['img']['img'],b['xpos'],b['ypos'],b['img']['width'],b['img']['height']); 
    }
};
var moveimage = function(imageobj, x, y,ctx){
  ctx.drawImage(imageobj['img'],x,y,imageobj['width'],imageobj['height']);
};
var showpressenter = function(){
    var c = document.getElementById("gameCanvas");
                c.width=924;
                c.height=668;
                var ctx = c.getContext("2d");
                var img = new Image();
                img.src = "/Breakout/assets/images/pressenter.png";
                img.onload = function(){off();ctx.drawImage(img,280,300,395,46);};
                
};

var showtext = function(text){
    setTimeout(function(){ 
    var c = document.getElementById("gameCanvas");
    c.width=924;
    c.height=668;
    var ctx = c.getContext("2d");
    ctx.font = "50px Arial";
    ctx.fillStyle = "white";
    ctx.textAlign = "center";
    ctx.fillText(text, c.width/2, c.height/2); 
    }, 100);
};

var showgoals = function(data){
    $('#player1score').html(data['player1']);
    $('#player2score').html(data['player2']);
};

var updatetime = function(time){
    let min = ((time - (time%3600))/3600);
    let sec = ((time%3600) - ((time%3600)%60))/60;
    $('#time').html("time left: " + min +":" + sec);
};
var remblock = function(data){
    images.blocks.splice(data['blockid'], 1);
};

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
};

function on() {
    $("#overlay").show();
};

function off() {
    $("#overlay").hide();
};

var ufo = function(){ 
  var $ufo2 = $("div.ufo2"),
      flyBy;
  
  $('img').on('dragstart', function(event) { event.preventDefault(); });
      
  $ufo2.css({
    'opacity': 1,
    'width': '0px'
  }); 
    
  flyBy = new TimelineMax({repeat: 10, repeatDelay:0, yoyo: true});
  
  flyBy.add(TweenMax.to(".ufo2", 2.8, {top: 100, left: 200, width: '50px', delay: 0.8}));
  flyBy.add(TweenMax.to(".ufo2", 3.6, {top: 50, left: 600}));
  flyBy.add(TweenMax.to(".ufo2", 1.2, {top: - 50, left: 300, opacity: 0}));
  flyBy.add(TweenMax.to(".ufo2", 2, {top: 200, left: 300, opacity: 1, width: '200px'}));
  flyBy.add(TweenMax.to(".ufo2", 5, {left: 600, opacity: 1, width: '300px'}));
  flyBy.add(TweenMax.to(".ufo2", 5, {top: 0, left: 800, opacity: 1, width: '0px'}));
 };
