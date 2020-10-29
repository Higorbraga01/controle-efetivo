package br.mil.fab.controle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.mil.fab.controle.entities.Usuario;
 
public class LoginFilter implements Filter {
 
 public void destroy() {
           // TODO Auto-generated method stub
 
 }
 
 public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {
   Usuario usuario = null;
   HttpSession session = ((HttpServletRequest) request).getSession(false);
    
   if (session != null){
         usuario = (Usuario) session.getAttribute("usuarioLogado");
   }      
 
     if (usuario == null) {
              String contextPath = ((HttpServletRequest) request)
                                 .getContextPath();
              ((HttpServletResponse) response).sendRedirect(contextPath
                                 + "/faces/login.xhtml");
     } else {
              chain.doFilter(request, response);
     }
 
 }
 
 public void init(FilterConfig arg0) throws ServletException {
           // TODO Auto-generated method stub
 
 }
 
}
