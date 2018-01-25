<%-- 
    Document   : highscorepage
    Created on : 18-dec-2017, 10:07:31
    Author     : massi
--%>

<%@ include file="header.jspf"%>
<header>
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
<h1 id="highscoresText" >Highscores</h1>


<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Single Player</a></li>
    <li><a href="#tabs-2">MultiPlayer</a></li>
  </ul>
  <div id="tabs-1">
    <table>
            <tr>
                <th>Username</th><th>Score</th>
            </tr>
            
            <c:forEach var="spplayer" items="${SPHIGHSCORES}">
                <tr>
                    <td><c:out value="${spplayer.username}"></c:out></td>
                    <td><c:out value="${spplayer.singleplayerhighscore}"></c:out></td>
                    
                </tr>
            </c:forEach>
</table>
</div>
  <div id="tabs-2">
      <table>
            <tr>
                <th>Username</th><th>Score Player 1</th><th>Score Player 2</th>
            </tr>
            
            <c:forEach var="mpplayer" items="${MPHIGHSCORES}">
                <tr>
                    <td><c:out value="${mpplayer.username}"></c:out></td>
                    <td><c:out value="${mpplayer.multiplayerhighscoreplayer1}"></c:out></td>
                    <td><c:out value="${mpplayer.multiplayerhighscoreplayer2}"></c:out></td>
                </tr>
            </c:forEach>
</table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>$(function(){$("#tabs").tabs();});</script>
<%@ include file="footer.jspf"%>        
