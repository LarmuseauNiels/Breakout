package be.brickrevolution.servlets;

import be.brickrevolution.data.data.Repositories;
import be.brickrevolution.model.Player;
import be.brickrevolution.pages.MenuPages;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "RegisterPage", urlPatterns = {"/RegisterPage"})
public class RegisterPage extends HttpServlet {

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
        if (request.getParameter("action") != null) {
            this.doRegister(request, response);
        } else {
            this.generateForm(request, response, false);
        }
    }
    
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String email = request.getParameter("email");
        String repeatEmail = request.getParameter("repeatEmail");
        
        Player player = Repositories.getPlayerRepository().getPlayerByUsername(username);
        Player player2 = Repositories.getPlayerRepository().getPlayerByEmail(email);
        
        
        if(username.equals("")|| password.equals("")||repeatPassword.equals("")||email.equals("")||repeatEmail.equals("")){
       this.generateForm(request,response,true);
       }
        if ( player == null && password.equals(repeatPassword)&& player2 == null && email.equals(repeatEmail)) {
            String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();  
            Player playerToAdd = new Player(username, hashedPassword, email, randomUUIDString);
            Repositories.getPlayerRepository().addPlayer(playerToAdd);
            response.sendRedirect("InlogPage");
        }else{
            this.generateForm(request, response, true);
        }
        
        
    }
    
    private void generateForm(HttpServletRequest request, HttpServletResponse response, boolean isLoginError) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            MenuPages mp = new MenuPages();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Brick Revolution</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/reset.css\">\n" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/screen.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println(mp.ToonRegister());
            if (isLoginError) {
                out.println("<p style=\"color: red\">Registered failed there was something wrong with your imput.</p>");
            }
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
