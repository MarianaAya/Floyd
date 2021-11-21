
package servlet;

import com.google.gson.Gson;
import entidades.Floyd;
import entidades.Retorno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "ReceberDados", urlPatterns = {"/ReceberDados"})
public class ReceberDados extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String acao=request.getParameter("acao");
        Retorno r;
        Floyd f=new Floyd();
        Gson gson=new Gson();
        switch(acao){
            case "floyd":
                
                
                
                String valores=request.getParameter("valores");
                String qtde=request.getParameter("qtde");
                if(qtde.length()>0 && valores.length()>0){
                    int num=Integer.parseInt(qtde);
                    String[] partes=valores.split(",");
                    int cont=0;
                    List<List<Integer>> lista=new ArrayList<>(num);
                    for(int i=0;i<num;i++){
                        lista.add(new ArrayList<>(num));
                    }

                    for(int i=0;i<partes.length;i++){
                        lista.get(cont).add(Integer.parseInt(partes[i]));
                        cont++;
                        if(cont==num)
                            cont=0;
                    }
                    
                    r=f.executar(num, lista);
                    
                    response.getWriter().print(gson.toJson(r));
                }
                break;
            
            case "caminho":
                String origem=request.getParameter("origem");
                String destino=request.getParameter("destino");
           
                System.out.println(origem+" "+destino);
                if(origem.length()>0 && destino.length()>0){
                    r=f.procurarCaminho(Integer.parseInt(origem), Integer.parseInt(destino));
                    response.getWriter().print(gson.toJson(r));
                }
                 
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

}
