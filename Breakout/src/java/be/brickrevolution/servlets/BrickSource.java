package be.brickrevolution.servlets;

import be.brickrevolution.data.data.AmplifierIconRepo;
import be.brickrevolution.model.Color;
import be.brickrevolution.model.Icons;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StandardBrick", urlPatterns = {"/StandardBrickSource"})
public class BrickSource extends HttpServlet {

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
        response.setContentType("image/svg+xml;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String widthString = request.getParameter("width");
            int width = Integer.parseInt(widthString);
            String heightString = request.getParameter("height");
            int height = Integer.parseInt(heightString);
            String digitString = request.getParameter("color");
            int digit = Integer.parseInt(digitString);
            Color c = new Color(digit);

            String PowerUpDownProperty = request.getParameter("property");

            out.println("<svg width='" + width + "' height='" + height + "' xmlns='http://www.w3.org/2000/svg'>");
            out.println("<g>");
            out.println("<title>background</title>");
            out.println("<rect fill=\"#fff\" id=\"canvas_background\" height=\"402\" width=\"582\" y=\"-1\" x=\"-1\"/>");
            out.println("</g>");
            out.println("<g>");
            out.println("<title>Layer 1</title>");
            out.println("<rect stroke=\"#000\" id=\"svg_2\" height=\"" + height + "\" width=\"" + width + "\" y=\"0\" x=\"0\" stroke-opacity=\"null\" stroke-width=\"0\" fill='#" + c.getLighthex() + "'/>");
            out.println("<polygon stroke=\"#000\" id=\"svg_6\" stroke-width=\"0\" fill='#" + c.getDarkhex() + "' points=\"0,0 " + width + "," + height + " 0," + height + "\" />");
            out.println("<rect stroke='#" + c.getMiddlehex() + "' id=\"svg_5\" height=\"" + (height * 0.62) + "\" width=\"" + (width * 0.6224) + "\" y=\"" + (height * 0.19725) + "\" x=\"" + (width * 0.195) + "\" stroke-width=\"0\" fill='#" + c.getMiddlehex() + "'/>");
            double scale;
            if ((height / 2) > (width / 3)) {
                scale = (1 - ((250 - (width / 3)) / 250.0));
            } else {
                scale = (1 - ((250 - (height / 2)) / 250.0));
            }

            out.println("<g transform=\"translate(" + ((width / 2) - (scale * 180)) + "," + ((height / 2) - (scale * 125)) + "),scale(" + scale + ")\">");
            Icons i = new Icons();
            if (PowerUpDownProperty == null || PowerUpDownProperty == "0") {
            } else {
                out.println(AmplifierIconRepo.getInstance().getICON(Integer.parseInt(PowerUpDownProperty)));
            }

            out.println(" </g>");
            out.println(" </g>");
            out.println("</svg>");

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
