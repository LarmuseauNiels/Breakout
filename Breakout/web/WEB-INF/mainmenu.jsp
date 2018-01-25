<%-- 
    Document   : newjspmainmenu
    Created on : 18-dec-2017, 10:05:21
    Author     : massi
--%>

<%@page import="be.brickrevolution.servlets.ControllerServlet"%>
<%@ include file="header.jspf"%>
          <header>
        <em id='user'><img alt='user' src='assets/images/user.png'>Welcome   <% out.println(request.getSession().getAttribute(ControllerServlet.SESS_USER)); %>  !</em><em id='experiencePoints'><img alt='star' src='assets/images/keditbookmarks.png'>Experience Points:</em><a href='constructionpage'><img alt='shop' src='assets/images/basket.png'></a>
        <svg viewBox="0 0 1500 200">
                <symbol id="s-text">
               	<text text-anchor="middle" x="50%" y="80%">Breakout Rev.</text>
                </symbol>
                        
                <g class = "g-ants">
                		<use xlink:href="#s-text" class="text-copy"></use>
                		<use xlink:href="#s-text" class="text-copy"></use>
                                <use xlink:href="#s-text" class="text-copy"></use>
                		<use xlink:href="#s-text" class="text-copy"></use>  
                		<use xlink:href="#s-text" class="text-copy"></use>
                	</g>
                </svg>
        </header>
        <main>
        <ul id=menulist>
        <li><a href='difficulty' id="classic" class="menuButton">Classic</a></li>
        <li><a href='constructionpage' id="campaign" class="menuButton">Campaign</a></li>
        <li><a href='multiplayer' id="multiplayer" class="menuButton">Multiplayer</a></li>
        <li><a href='highscorepage' id="highscores"class="menuButton">Highscores</a></li
        <li><a href='constructionpage' id="clans" class="menuButton">Clans</a></li>
        <li><a href='settingspage' id="settings" class="menuButton">Settings</a></li>
        <!--<li><a href='creditspage' id="credits"class="menuButton">Credits</a></li>-->
        
        </ul>
        </main>

       <footer><a class="logout" href='logout' id='logout'><img alt='logout' src='assets/images/lgout.png'></a></footer>
<%@ include file="footer.jspf"%>