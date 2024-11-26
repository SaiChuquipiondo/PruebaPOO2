package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.beans.Autor;
import com.beans.Editorial;
import com.beans.Genero;
import com.beans.Libro;
import com.model.AutoresModel;
import com.model.LibroModel;

/**
 * Servlet implementation class LibroControllers
 */
public class LibroControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	LibroModel modeloL = new LibroModel();

	public LibroControllers() {
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

		case "nuevo":
			nuevo(request, response);
			break;

		case "insertar":
			insertar(request, response);
			break;

		case "obtener":
			obtener(request, response);
			break;

		case "editar":
			editar(request, response);
			break;
		
		  case "eliminar": 
		  eliminar(request, response); 
		  break;
		 
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listalibro", modeloL.ListarLibro());
			request.getRequestDispatcher("/Libro/listaLibro.jsp").forward(request, response);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServletException {
		try {
			if(!validar(request, response)) {
			Libro libro = new Libro();
			Autor autor = new Autor();
			Editorial edi = new Editorial();
			Genero gene = new Genero();
			libro.setNombre(request.getParameter("nombre"));
			libro.setExistencia(Integer.parseInt(request.getParameter("existencia")));
			libro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			libro.setDescripcion(request.getParameter("descripcion"));
			autor.setId(Integer.parseInt(request.getParameter("autor")));
			edi.setIdEditorial(Integer.parseInt(request.getParameter("editorial")));
			gene.setIdgenero(Integer.parseInt(request.getParameter("genero")));
			libro.setAutor(autor);
			libro.setEditorial(edi);
			libro.setGenero(gene);
			if (modeloL.insertarLibro(libro) > 0) {
				request.getSession().setAttribute("Exitoso", "Libro agregado");
			} else {
				request.getSession().setAttribute("Fallido", "Libro no agregado");
			}
			response.sendRedirect(request.getContextPath() + "/LibroControllers?op=listar");
			}
		} catch (SQLException | IOException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
		}
		

	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaAutor", modeloL.ListarAutor());
			request.setAttribute("listaEditorial", modeloL.ListarEditorial());
			request.setAttribute("listaGenero", modeloL.ListarGenero());
			request.getRequestDispatcher("/Libro/nuevoLibro.jsp").forward(request, response);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			request.setAttribute("listaAutor", modeloL.ListarAutor());
			request.setAttribute("listaEditorial", modeloL.ListarEditorial());
			request.setAttribute("listaGenero", modeloL.ListarGenero());
			Libro libro = modeloL.obtenerLibro(Integer.parseInt(id));
			if (libro != null) {
				request.setAttribute("Libro", libro);
				request.getRequestDispatcher("/Libro/editarLibro.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(!validar(request, response)) {
			Libro libro = new Libro();
			Autor autor = new Autor();
			Editorial edi = new Editorial();
			Genero gene = new Genero();
			libro.setIdLibro(Integer.parseInt(request.getParameter("id")));
			libro.setNombre(request.getParameter("nombre"));
			libro.setExistencia(Integer.parseInt(request.getParameter("existencia")));
			libro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			libro.setDescripcion(request.getParameter("descripcion"));
			autor.setId(Integer.parseInt(request.getParameter("autor")));
			libro.setAutor(autor);
			edi.setIdEditorial(Integer.parseInt(request.getParameter("editorial")));
			libro.setEditorial(edi);
			gene.setIdgenero(Integer.parseInt(request.getParameter("genero")));
			libro.setGenero(gene);
			if (modeloL.editarLibro(libro) > 0) {
				request.setAttribute("Editado exitooso", "Libro editado");
			} else {
				request.setAttribute("No exitoso", "Libro no editado");
			}
			response.sendRedirect(request.getContextPath() + "/LibroControllers?op=listar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(modeloL.eliminarLibro(id)>0) {
				request.setAttribute("Eliminado existoso","Libro eliminado");
			}else {
				request.setAttribute("Eliminado fracaso", "Libro no eliminado");
			}
			response.sendRedirect(request.getContextPath()+"/LibroControllers?op=listar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean validar = false;
		List<String> listError = new ArrayList<>();
		try {
			if(request.getParameter("nombre").equals("")) {
				validar = true;
				listError.add("Ingresar nombre del libro");
			}
			if(request.getParameter("existencia").equals("")) {
				validar=true;
				listError.add("Ingresar existencia:");
			}else if(Integer.parseInt(request.getParameter("existencia"))<0) {
				validar=true;
				listError.add("Ingresar mas de 0");
			}
			if(request.getParameter("precio").equals("")) {
				validar = true;
				listError.add("Ingresar precio");
			}else if(Double.parseDouble(request.getParameter("precio"))<0) {
				validar = true;
				listError.add("Ingresar mas de 0");
			}
			
			if(request.getParameter("descripcion").equals("")) {
				validar=true;
				listError.add("Ingresar descripcion");
			}
			request.setAttribute("respuesta", validar);
			request.setAttribute("listaError", listError);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validar;
	}
}
