/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb1.enitites.User;
import ejb2.entities.RemoteUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MkSavin
 */
public class UsersAddServlet extends HttpServlet {

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
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("usersAdd.xhtml");
        requestDispatcher.forward(request, response);

    }

    @EJB
    beans.ExperimentSessionBeanLocal sb;
    
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
        if (!"local".equals(request.getParameter("type"))) {
            RemoteUser ru = new RemoteUser();
            ru.setLogin(request.getParameter("login"));
            ru.setEmail(request.getParameter("email"));
            ru.setName(request.getParameter("name"));
            
            sb.addRemoteUser(ru);
        } else {
            User u = new User();
            u.setRemoteId(Integer.parseInt(request.getParameter("remoteId")));
            u.setBalanceLocal(Integer.parseInt(request.getParameter("balance")));
            sb.addLocalUser(u);
        }
        
        response.sendRedirect(request.getContextPath() + "/users");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
