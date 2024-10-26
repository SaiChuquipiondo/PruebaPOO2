package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.beans.Autor;
import com.model.AutoresModel;

/**
 * Servlet implementation class AutorControllers
 */
public class AutorControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AutoresModel modelo = new AutoresModel();
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
		if(request.getParameter("op")==null) {
			listar(request,response);
			return;
		}
		String operacion = request.getParameter("op");
		switch (operacion) {
		case "listar":
			listar(request, response);
			
			break;
		case "nuevo":
			request.getRequestDispatcher("/autores/NuevoAutor.jsp").forward(request, response);
			break;
			
		case "insertar":
			insertar(request,response);
			break;
		case "obtener":
			obtener(request, response);
			break;
		}
		
		
			
		}
	}
    public AutorControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaAutores", modelo.ListarAutores());
			request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
		}catch (ServletException | IOException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE,null,ex);
		}
		
	}
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Autor miAutor = modelo.obtenerAutor(Integer.parseInt(id));
			if(miAutor!=null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/autores/editarAutores.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			Autor miAutor = new Autor();
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setPais(request.getParameter("nacionalidad"));
			if (modelo.insertarAutor(miAutor)>0) {
				request.getSession().setAttribute("exito", "Autor registrado exitoso");
			}else {
				request.getSession().setAttribute("fracaso", "Autor no registrado");
			}
			response.sendRedirect(request.getContextPath()+"/AutorControllers?op=listar");
			
			
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
	


}
