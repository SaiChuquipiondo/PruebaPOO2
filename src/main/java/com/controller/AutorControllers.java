package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oracle.jdbc.driver.parser.util.Array;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
			request.getRequestDispatcher("/autores/NuevoAutor.jsp").forward(request, response);
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

	public AutorControllers() {
		super();
		// TODO Auto-generated constructor stub
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
			request.setAttribute("listaAutores", modelo.ListarAutores());
			request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Autor miAutor = new Autor();
			miAutor.setId(Integer.parseInt(request.getParameter("id")));
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setPais(request.getParameter("nacionalidad"));

			if (modelo.modificarAutor(miAutor) > 0) {
				request.getSession().setAttribute("exito", "Autor modificado exitoso");
				System.out.println("modificado exitoso");
			} else {
				request.getSession().setAttribute("fracaso", "Autor no modificado");
				System.out.println("no modificado");
			}
			response.sendRedirect(request.getContextPath() + "/AutorControllers?op=listar");
			System.out.println("modificar: " + modelo.modificarAutor(miAutor));

		} catch (Exception ex) {
			ex.getStackTrace();
		}

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idAutor = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarAutor(idAutor) > 0) {
				request.getSession().setAttribute("exito", "Autor eliminado exitoso");
				System.out.println("eliminado exitoso");
			} else {
				request.getSession().setAttribute("fracaso", "Autor no eliminado");
				System.out.println("no elimando");
			}
			response.sendRedirect(request.getContextPath() + "/AutorControllers?op=listar");

		} catch (Exception ex) {
			ex.getStackTrace();
		}

	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Autor miAutor = modelo.obtenerAutor(Integer.parseInt(id));

			if (miAutor != null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/autores/editarAutores.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (!validar(request, response)) {
			Autor miAutor = new Autor();
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setPais(request.getParameter("nacionalidad"));
				if (modelo.insertarAutor(miAutor) > 0) {
					request.getSession().setAttribute("exito", "Autor registrado exitoso");
				} else {
					request.getSession().setAttribute("fracaso", "Autor no registrado");
				}
				response.sendRedirect(request.getContextPath() + "/AutorControllers?op=listar");
			} else {
				request.getRequestDispatcher("/autores/NuevoAutor.jsp").forward(request, response);
			}

		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	private boolean validar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean res = false;
		List<String> listError = new ArrayList<>();

		try {
			if (request.getParameter("nombre").equals("")) {
				res = true;
				listError.add("Ingresar el nombre de autor: ");
			}
			if (request.getParameter("nacionalidad").equals("")) {
				res = true;
				listError.add("Ingresar la nacionalidad del autor");
			}

			request.setAttribute("respuesta", res);
			request.setAttribute("listaError", listError);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return res;
	}

}
