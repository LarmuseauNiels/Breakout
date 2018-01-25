<%-- 
    Document   : registerpage
    Created on : 18-dec-2017, 10:05:16
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
<main class="form">
    <div id="Register">
        <h2>Sign Up</h2>
        <form action="registerpage" id="registerform" method="post">
            <input type="text" name="username" id="username" placeholder="username">
            <input type="password" name="password" id="password" placeholder="password">
            <input type="password" name="repeatPassword" id="repeatpassword" placeholder="confirm password">
                   <input type="email" name="email" id="email" placeholder="email">
            <input type="email" name="repeatEmail" id="repeatemail" placeholder="confirm email">
            <input type="submit" name="action" value="Sign Up!"/>
        </form>

    </div>
            <%
                if(request.getAttribute("registerError") != null){
                Boolean registerError = (Boolean) request.getAttribute("registerError") ;
                if (registerError) {
                    out.println("<p class ='error' >Registered failed there was something wrong with your imput.</p>");
                }
                    }
                %>

</main>
<%@ include file="footer.jspf"%>  
