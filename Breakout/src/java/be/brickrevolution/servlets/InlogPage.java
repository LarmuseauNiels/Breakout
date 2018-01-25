package be.brickrevolution.servlets;

import be.brickrevolution.data.data.Repositories;
import be.brickrevolution.model.Player;
import be.brickrevolution.pages.MenuPages;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "InlogPage", urlPatterns = {"/InlogPage"})
public class InlogPage extends HttpServlet {

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
            throws ServletException, IOException 
    {

        if (request.getParameter("action") != null) {
            this.doLogin(request, response);
        } else {
            this.generateForm(request, response, false);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            playerP = Repositories.getPlayerRepository().getPlayerByUsername(username);
            hashedPassword = playerP.getPassword();
        } catch (NullPointerException e) {
            this.generateForm(request, response, true);
        }
        
        if (playerP == null || !BCrypt.checkpw(password, hashedPassword)) {
            
            this.generateForm(request, response, true);
            
        } else {
            request.getSession().setAttribute(SESS_USER, playerP.getUsername());
            Cookie cookie = new Cookie("uuid", playerP.getUuid());
            response.addCookie(cookie);
            response.sendRedirect("MainMenu");
        }
    }

    private void generateForm(HttpServletRequest request, HttpServletResponse response, boolean isLoginError) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            MenuPages mp = new MenuPages();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Brick Revolution</title>");    
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/reset.css\">\n" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/screen.css\">");

            out.println("</head>");
            out.println("<body>");
            out.println(mp.ToonInlog(isLoginError));
            out.println("</body>");
            out.println("</html>");
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

}
