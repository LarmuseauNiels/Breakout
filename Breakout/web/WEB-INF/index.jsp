<%-- 
    Document   : inlogpage
    Created on : 18-dec-2017, 10:04:51
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
        <main class="form" >
            <div id="Login">
                <%
                if(request.getAttribute("isLoggedIn") != null){
                Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn") ;
                if (isLoggedIn) {
                    out.println("<p class ='error' >Access denied. Please specify a valid username and password.</p>");
                }
                    }
                %>
                
                <form action="login" id="inlogform" method="post">
                    <input type="text" name="username" id="username" placeholder="username">
                    <input type="password" name="password" id="password" placeholder="password">
                    <a href="#" class="forgotPassword">Forgot password?</a>
                    <input type="submit" name="action" value="Sign In" />
                </form>
                <div id="inlogsocialmedia">
                    <a href="#"><img alt="facebook" id="facebook" src="assets/images/sign-in-facebook.png"></a>
                    <a href="#"><img alt="google" id="google" src="assets/images/sign-in-with-google.png"></a>
                </div>
                <div>
                    <p>Need an account? <a href="registerpage">Sign up</a></p>
                </div>
            </div>
        </main>
<%@ include file="footer.jspf"%>