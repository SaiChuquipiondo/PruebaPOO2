package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.beans.Editorial;
import com.model.EditorialModel;

/**
 * Servlet implementation class EditorialControllers
 */
public class EditorialControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EditorialModel modeloE = new EditorialModel();

	public EditorialControllers() {
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
			request.getRequestDispatcher("/editorial/nuevoEditorial.jsp").forward(request, response);
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

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaEditorial", modeloE.ListaEditorial());
			request.getRequestDispatcher("/editorial/listarEditorial.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Editorial edi = new Editorial();
			edi.setNombre(request.getParameter("nombre"));
			edi.setContacto(request.getParameter("contacto"));
			edi.setTelefono(request.getParameter("telefono"));
			if (edi.getTelefono().length() > 9) {
				
			} else {
				if (modeloE.insertarEditorial(edi) > 0) {
					request.setAttribute("Exitoso", "Editorial registrado");
				} else {
					request.setAttribute("Fracaso", "Editorial no registrado");
				}
				response.sendRedirect(request.getContextPath() + "/EditorialControllers?op=listar");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Editorial edi = modeloE.obtenerEditorial(Integer.parseInt(request.getParameter("id")));

			if (edi != null) {
				request.setAttribute("editorial", edi);
				request.getRequestDispatcher("/editorial/editarEditorial.jsp").forward(request, response);

			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Editorial edi = new Editorial();
			edi.setIdEditorial(Integer.parseInt(request.getParameter("id")));
			edi.setNombre(request.getParameter("nombre"));
			edi.setContacto(request.getParameter("contacto"));
			edi.setTelefono(request.getParameter("telefono"));
			if (modeloE.editarEditorial(edi) > 0) {
				request.getSession().setAttribute("Exitos", "Editoiral modificado");
			} else {
				request.getSession().setAttribute("Fracaso", "Editorial no modificado");
			}
			response.sendRedirect(request.getContextPath() + "/EditorialControllers?op=listar");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (modeloE.eliminarEditorial(Integer.parseInt(request.getParameter("id"))) > 0) {
				request.getSession().setAttribute("Exitoso", "Editorial Eliminado");
			} else {
				request.getSession().setAttribute("Fracaso", "Editorial NO Eliminado");
			}
			response.sendRedirect(request.getContextPath() + "/EditorialControllers?op=listar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
