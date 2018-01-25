package be.brickrevolution.pages;

public class GameEnvironment {

    public String ToonGame() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div id='scores'>");
        sb.append("<p>Player1</p>");
        sb.append("<p><em id='player1score'>0</em></p>");
        sb.append("</div>");
        sb.append("<div id='gameSpace'>");
        sb.append("<canvas id='gameCanvas'  ></canvas>");
        sb.append("</div>");
        sb.append("<div class=\"ufo2\">");
        sb.append("<img class=\"flyingSaucer\" src=\"https://s.cdpn.io/4579/FlyingSaucer.png\"/>");
        sb.append("</div>");
        sb.append("<div id='overlay'>");
        sb.append("    <div id='loader'><img src=\"assets/images/Spinner.svg\" alt=\"loadingicon\"></div>");
        sb.append("</div>");
        sb.append("<script src=\"assets/js/jquery-3.1.0.min.js\">></script>");
        return sb.toString();
    }

    public String ToonMPGame() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div id='scores'>");
        sb.append("<p>Player1: ");
        sb.append("<em id='player1score'>0</em></p>");
        sb.append("<p>Player2: ");
        sb.append("<em id='player2score'>0</em></p>");
        sb.append("<div id='timer'>");
        sb.append("<p id='time'>time left:</p>");
        sb.append("</div>");
        sb.append("</div>");

        sb.append("<div id='gameSpace'>");
        sb.append("<canvas id='gameCanvas'  ></canvas>");
        sb.append("</div>");

        sb.append("<div id='overlay'>");
        sb.append("    <div id='loader'><img src=\"assets/images/Spinner.svg\" alt=\"loadingicon\"></div>");
        sb.append("</div>");
        sb.append("<script src=\"assets/js/jquery-3.1.0.min.js\">></script>");
        return sb.toString();
    }

}
