package be.brickrevolution.pages;

import be.brickrevolution.servlets.InlogPage;
import javax.servlet.http.HttpServletRequest;

public class MenuPages {

    public String ToonMainMenu(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("<header>");
        sb.append("<em id='user'><img alt='user' src='assets/images/user.png'>Welcome " + request.getSession().getAttribute(InlogPage.SESS_USER) + "!</em><em id='experiencePoints'><img alt='star' src='assets/images/keditbookmarks.png'>Experience Points:</em><a href='PageConstruction'><img alt='shop' src='assets/images/basket.png'></a>");
        sb.append("\"<svg viewBox=\"0 0 1500 200\">\n"
                + "\"	<symbol id=\"s-text\">\n"
                + "\"		<text text-anchor=\"middle\" x=\"50%\" y=\"80%\">Breakout Rev.</text>\n"
                + "\"	</symbol>\n"
                + "\n"
                + "\"	<g class = \"g-ants\">\n"
                + "\"		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "	</g>\n"
                + "</svg>");
        sb.append("</header>");
        sb.append("<main>");
        sb.append("<ul id=menulist>");
        sb.append("<li><a href='ClassicGameMode' id=\"classic\" class=menuButton>Classic</a></li>");
        sb.append("<li><a href='PageConstruction' id=\"campaign\" class=menuButton>Campaign</a></li>");
        sb.append("<li><a href='MultiplayerGameMode' id=\"multiplayer\" class=menuButton>Multiplayer</a></li>");
        sb.append("<li><a href='PageConstruction' id=\"clans\" class=menuButton>Clans</a></li>");
        sb.append("<li><a href='Settings' id=\"settings\"class=menuButton>Settings</a></li>");
        sb.append("</ul>");
        sb.append("</main>");

        sb.append("<footer><a class=\"logout\" href='/Breakout/InlogPage' id='logout'><img alt='logout' src='assets/images/lgout.png'></a></footer>");

        return sb.toString();
    }

    public String ToonInlog(boolean isLoginError) {
        StringBuilder sb = new StringBuilder();
        sb.append("<header>");
        sb.append("<svg viewBox=\"0 0 1500 200\">\n");
        sb.append("	<symbol id=\"s-text\">\n");
        sb.append("		<text text-anchor=\"middle\" x=\"50%\" y=\"80%\">Breakout Rev.</text>\n");
        sb.append("	</symbol>\n");
        sb.append("\n");
        sb.append("	<g class = \"g-ants\">\n");
        sb.append("		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n");
        sb.append("		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n");
        sb.append("		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n");
        sb.append("		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n");
        sb.append("		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n");
        sb.append("	</g>\n");
        sb.append("</svg>");
        sb.append("</header>");
        sb.append("<main class=\"form\" >\n");
        sb.append("        <div id=\"Login\">\n");
        if (isLoginError) {
            sb.append("<p class =\"error\" >Access denied. Please specify a valid username and password.</p>");
        }
        sb.append("            <form action=\"InlogPage\" id=\"inlogform\" method=\"get\">\n");
        sb.append("                <input type=\"text\" name=\"username\" id=\"username\" placeholder=\"username\">\n");
        sb.append("                <input type=\"password\" name=\"password\" id=\"password\" placeholder=\"password\">\n");
        sb.append("                <a href=\"#\" class=\"forgotPassword\">Forgot password?</a>\n");
        sb.append("                <input type=\"submit\" name=\"action\" value=\"Sign In\" />");
        sb.append("            </form>\n");
        sb.append("            <div id=\"inlogsocialmedia\">\n");
        sb.append("                <a href=\"#\"><img alt=\"facebook\" id=\"facebook\" src=\"assets/images/sign-in-facebook.png\"></a>\n");
        sb.append("                <a href=\"#\"><img alt=\"google\" id=\"google\" src=\"assets/images/sign-in-with-google.png\"></a>\n");
        sb.append("            </div>\n");
        sb.append("            <div>\n");
        sb.append("                <p>Need an account? <a href=\"RegisterPage\">Sign up</a></p>\n");
        sb.append("            </div>\n");
        sb.append("        </div>\n");
        sb.append("    </main>");

        return sb.toString();
    }

    public String ToonRegister() {
        StringBuilder sb = new StringBuilder();
        sb.append("<header>"
                + "<svg viewBox=\"0 0 1500 200\">\n"
                + "	<symbol id=\"s-text\">\n"
                + "		<text text-anchor=\"middle\" x=\"50%\" y=\"80%\">Breakout Rev.</text>\n"
                + "	</symbol>\n"
                + "\n"
                + "	<g class = \"g-ants\">\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "	</g>\n"
                + "</svg>"
                + "</header>"
                + "<main class=\"form\">\n"
                + "        <div id=\"Register\">\n"
                + "            <h2>Sign Up</h2>\n"
                + "            <form action=\"RegisterPage\" id=\"registerform\" method=\"post\">\n"
                + "                <input type=\"text\" name=\"username\" id=\"username\" placeholder=\"username\">\n"
                + "                <input type=\"password\" name=\"password\" id=\"password\" placeholder=\"password\">\n"
                + "                <input type=\"password\" name=\"repeatPassword\" id=\"repeatpassword\" placeholder=\"confirm password\">\n"
                + "                <input type=\"email\" name=\"email\" id=\"email\" placeholder=\"email\">\n"
                + "                <input type=\"email\" name=\"repeatEmail\" id=\"repeatemail\" placeholder=\"confirm email\">\n"
                + "                 <input type=\"submit\" name=\"action\" value=\"Sign Up!\"/>"
                + "            </form>\n"
                + "            \n"
                + "        </div>\n"
                + "    </main>");
        return sb.toString();
    }

    public String ToonSettings() {
        StringBuilder sb = new StringBuilder();
        sb.append("<header>"
                + "<svg viewBox=\"0 0 1500 200\">\n"
                + "	<symbol id=\"s-text\">\n"
                + "		<text text-anchor=\"middle\" x=\"50%\" y=\"80%\">Breakout Rev.</text>\n"
                + "	</symbol>\n"
                + "\n"
                + "	<g class = \"g-ants\">\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "		<use xlink:href=\"#s-text\" class=\"text-copy\"></use>\n"
                + "	</g>\n"
                + "</svg>"
                + "</header>"
                + "<h1>Settings</h1>"
                + "        <div id=\"Settings\">\n"
                + "            \n"
                + "<h1>left key</h1>"
                + "<div id=\"left\" class='key'>"
                + "    <span class='keyname'>Press left key</span>"
                + "    <span class='code'></span>"
                + "  </div>"
                + "<h1>right key</h1>"
                + "  <div id=\"right\" class='key'>"
                + "    <span class='keyname'>Press right key</span>"
                + "    <span class='code'></span>"
                + "</div>"
                + "        </div>\n"
        );
        return sb.toString();
    }
}
