/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.brickrevolution.servlets;

import be.brickrevolution.data.data.Repositories;
import be.brickrevolution.model.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gurin
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/index.html","/index","/classic", "/constructionpage", "/highscorepage", "/mainmenu", "/registerpage",
    "/settingspage", "/login", "/multiplayer", "/logout","/creditspage","/difficulty"})
public class ControllerServlet extends HttpServlet {

    public static final String SESS_USER = "USER";
    private Player playerP;
    private String hashedPassword;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/index.html":
            case "/index":
                this.goToIndex(request, response);
                break;
            case "/classic":
                this.goToClassic(request, response);
                break;
            case "/constructionpage":
                this.goToConstructionPage(request, response);
                break;

            case "/highscorepage":
                this.goToHighScore(request, response);
                break;

            case "/mainmenu":
                this.goToMainMenu(request, response);
                break;

            case "/registerpage":
                if (request.getParameter("action") != null) {
                    this.doRegister(request, response);
                } else {
                       request.setAttribute("registerError", false);
                     request.getRequestDispatcher("WEB-INF/registerpage.jsp").forward(request, response);
                }
                
                break;
            case "/settingspage":
                this.goToSettingsPage(request, response);
                break;
            case "/login":
                if (request.getParameter("action") != null) {
                    this.doLogin(request, response);
                } else {
                    request.setAttribute("isLoggedIn", false);
                    request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
                }
                break;

            case "/multiplayer":
                this.goToMultiplayer(request, response);
                break;

            case "/logout":
                this.logOut(request, response);
                break;
            case "/difficulty":
                this.goToDifficulty(request, response);
                break;
            case "/creditspage":
                this.goToCreditsPage(request, response);
                break;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            playerP = Repositories.getPlayerRepository().getPlayerByUsername(username);
            hashedPassword = playerP.getPassword();
        } catch (NullPointerException e) {

            request.setAttribute("isLoggedIn", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        if (playerP == null || !BCrypt.checkpw(password, hashedPassword)) {

            request.setAttribute("isLoggedIn", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } else {
            request.getSession().setAttribute(SESS_USER, playerP.getUsername());
            request.getRequestDispatcher("WEB-INF/mainmenu.jsp").forward(request, response);
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String email = request.getParameter("email");
        String repeatEmail = request.getParameter("repeatEmail");

        Player player = Repositories.getPlayerRepository().getPlayerByUsername(username);
        Player player2 = Repositories.getPlayerRepository().getPlayerByEmail(email);

        if (username.equals("") || password.equals("") || repeatPassword.equals("") || email.equals("") || repeatEmail.equals("")) {
            request.setAttribute("registerError", true);
            request.getRequestDispatcher("WEB-INF/registerpage.jsp").forward(request, response);
        }
        else{
        if (player == null && password.equals(repeatPassword) && player2 == null && email.equals(repeatEmail)) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();  
            Player playerToAdd = new Player(username, hashedPassword, email, randomUUIDString);
            Repositories.getPlayerRepository().addPlayer(playerToAdd);
            response.sendRedirect("login");
        } else {
            request.setAttribute("registerError", true);
            request.getRequestDispatcher("WEB-INF/registerpage.jsp").forward(request, response);
        }
        }
    }

    private void goToClassic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/classic.jsp").forward(request, response);
    }
    
    private void goToIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
    
    private void goToConstructionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/constructionpage.jsp").forward(request, response);

    }


    private void goToMainMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/mainmenu.jsp").forward(request, response);
    }

    private void goToHighScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Player> SPplayers = Repositories.getPlayerRepository().getSinglePlayerHighscores();
        List<Player> MPplayers = Repositories.getPlayerRepository().getMultiPlayerHighscores();
        request.setAttribute("SPHIGHSCORES", SPplayers);
        request.setAttribute("MPHIGHSCORES",MPplayers);
        request.getRequestDispatcher("WEB-INF/highscorepage.jsp").forward(request, response);
    }

    private void goToSettingsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/settingspage.jsp").forward(request, response);
    }
/*
    private void goToRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/registerpage.jsp").forward(request, response);
    }*/

    private void goToMultiplayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/multiplayer.jsp").forward(request, response);
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void goToCreditsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/creditspage.jsp").forward(request, response);

    }

    private void goToDifficulty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/difficulty.jsp").forward(request, response);    }

}
