package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.beans.Genero;
import com.model.GeneroModel;

/**
 * Servlet implementation class GeneroControllers
 */
public class GeneroControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GeneroModel modeloG=new  GeneroModel();
    public GeneroControllers() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op") == null) {
			listar(request, response);
			return;

		}
		String operacion = request.getParameter("op");
		switch (operacion) {
		case "listar":
			listar(request, response);
			break;
		case "insertar":
			insertar(request, response);
			break;
		case "nuevo":
			request.getRequestDispatcher("/genero/nuevoGenero.jsp").forward(request, response);
			break;
		}
    }


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
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaGenero", modeloG.ListarGenero());
			request.getRequestDispatcher("/genero/ListarGenero.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Genero gene = new Genero();
			gene.setNombre(request.getParameter("nombre"));
			gene.setDescripcion(request.getParameter("descripcion"));
				if (modeloG.insertarGenero(gene) > 0) {
					request.setAttribute("Exitoso", "Genero registrado");
				} else {
					request.setAttribute("Fracaso", "Genero no registrado");
				}
				response.sendRedirect(request.getContextPath() + "/GeneroControllers?op=listar");
				
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
