package com.empresa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.ProductoDAO;
import com.empresa.modelo.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet("/ProductoController")
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
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
		String opcion = request.getParameter("opcion");

		if (opcion.equalsIgnoreCase("crear")) {
			RequestDispatcher rq = request.getRequestDispatcher("/views/crear.jsp");
			rq.forward(request, response);

			System.out.println("Eligio crear");
		} else if (opcion.equalsIgnoreCase("listar")) {
			RequestDispatcher rq = request.getRequestDispatcher("/views/listar.jsp");
			ArrayList<Producto> productos = new ArrayList<Producto>();
			ProductoDAO pDAO = new ProductoDAO();
			productos = pDAO.obtenerProductos();
			for (Producto producto : productos) {
				System.out.println(producto.toString());
				request.setAttribute("productos", productos);
				rq.forward(request, response);
				System.out.println("Eligio listar");
			}
		} else if (opcion.equalsIgnoreCase("editar")) {
			Producto producto = new Producto();
			ProductoDAO pDAO = new ProductoDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			producto = pDAO.obtenerProducto(id);
			System.out.println(producto.toString());
			request.setAttribute("producto", producto);
			RequestDispatcher rq = request.getRequestDispatcher("/views/editar.jsp");
			rq.forward(request, response);

		}  else if (opcion.equalsIgnoreCase("eliminar")) {
			ProductoDAO pDAO = new ProductoDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			pDAO.eliminar(id);
			RequestDispatcher rq = request.getRequestDispatcher("/index.jsp");
			rq.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");

		if (opcion.equalsIgnoreCase("guardar")) {
			Date fechaActual = new Date();
			ProductoDAO pDAO = new ProductoDAO();
			Producto produc = new Producto();
			produc.setNombre(request.getParameter("nombre"));
			produc.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
			produc.setPrecio(Double.parseDouble(request.getParameter("precio")));
			produc.setFechaCrear(new java.sql.Date(fechaActual.getTime()));

			if (pDAO.guardar(produc)) {
				System.out.println("Registro exitoso");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} else if (opcion.equalsIgnoreCase("editar")) {
			Date fechaActual = new Date();
			ProductoDAO pDAO = new ProductoDAO();
			Producto produc = new Producto();
			produc.setId(Integer.parseInt(request.getParameter("id")));
			produc.setNombre(request.getParameter("nombre"));
			produc.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
			produc.setPrecio(Double.parseDouble(request.getParameter("precio")));
			produc.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			if (pDAO.editar(produc)) {
				System.out.println("Actualizacion exitoso");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}else{
				System.out.println("no se actualizo el producto");
			}
		}
	}

}
