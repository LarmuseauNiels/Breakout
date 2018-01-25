package be.brickrevolution.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PaletSource", urlPatterns = {"/PaletSource"})
public class PaletSource extends HttpServlet {

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
            String heightString = request.getParameter("height");
            int width = Integer.parseInt(widthString);
            int height = Integer.parseInt(heightString);
            String color = request.getParameter("color");
            out.println("<svg width='"+(width+2)+"' height='"+(height+2)+"' xmlns='http://www.w3.org/2000/svg'>" +
                    "	<defs>\n" +
"		<filter id=\"blur\" x=\"-200%\" y=\"-200%\" width=\"400%\" height=\"400%\">\n" +
"			<feGaussianBlur in=\"SourceGraphic\" stdDeviation=\"70\" />\n" +
"    </filter>\n" +
"	</defs>"+
"                       <g>" +
"                        <title>background</title>\n" +
"                           <rect fill='none' id='canvas_background' height='"+(height+4)+"' width='"+(width+4)+"' y='-1' x='-1'/>\n" +
"                           <g display='none' id='canvasGrid'>\n" +
"                           <rect fill='url(#gridpattern)' stroke-width='0' y='1' x='1.25' height='"+height+"' width='"+width+"' id='svg_2'/>\n" +
"                         </g>\n" +
"                     </g>\n" +
"                <g>\n" +
"                   <title>Layer 1</title>\n" +
"                   <rect rx='10' id='svg_1' height='"+height+"' width='"+width+"' y='0.9' x='0.75' stroke-width='1.5' stroke='#000' fill='"+color+"' filter='blur'/>\n" +
"               </g>\n" +
"               </svg>");
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
