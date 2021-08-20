package com.empresa.dao;

import com.empresa.conexion.Conexion;
import com.empresa.modelo.*;
import com.mysql.cj.api.xdevapi.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.parser.RecoveredUnit;

public class ProductoDAO {
    private Connection conexion;
	private PreparedStatement preparar;
	private ResultSet resulSet;

	// guardar producto
	public boolean guardar(Producto producto)  {
		boolean estadoOperacion = false;
		
		String consulta = "INSERT INTO productos (nombre,cantidad,precio,fecha_crear)"
				+ "VALUES(?,?,?,?) ";
		try {
			conexion = obtenerConexion();
			conexion.setAutoCommit(false);
			preparar = conexion.prepareStatement(consulta);
			preparar.setString(1,producto.getNombre());
			preparar.setInt(2,producto.getCantidad());
			preparar.setDouble(3,producto.getPrecio());
			preparar.setDate(4,producto.getFechaCrear());
			estadoOperacion = preparar.executeUpdate()>0;
			conexion.commit();
			preparar.close();
			conexion.close();


		} catch (SQLException e) {
			try {
				obtenerConexion().rollback();
			} catch (Exception e2) {
				System.out.println(e2);
			}
				
			System.out.println("Error :" + e);
		}
		
		return estadoOperacion;
		
	}

	// editar producto
	public boolean editar(Producto producto) {
		boolean estadoOperacion = false;
		String consulta = "UPDATE productos SET nombre = ?,cantidad = ?,precio= ?,fecha_actualizar= ?"
				+ "WHERE id = ?";
		try {
			conexion = obtenerConexion();
			conexion.setAutoCommit(false);
			preparar = conexion.prepareStatement(consulta);
			preparar.setString(1,producto.getNombre());
			preparar.setInt(2,producto.getCantidad());
			preparar.setDouble(3,producto.getPrecio());
			preparar.setDate(4,producto.getFechaActualizar());
			preparar.setInt(5,producto.getId());
			estadoOperacion = preparar.executeUpdate()>0;
			conexion.commit();
			preparar.close();
			conexion.close();


		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error :" + e2);
			}

			System.out.println("Error :" + e);
		}
		
		return estadoOperacion;
	}

	// eliminar producto
	public boolean eliminar(int idProducto) {
		boolean estadoOperacion = false;
		String consulta = "DELETE FROM crud.productos WHERE id = ?";
		try {
			conexion = obtenerConexion();
			conexion.setAutoCommit(false);
			preparar = conexion.prepareStatement(consulta);
			preparar.setInt(1,idProducto);
			estadoOperacion = preparar.executeUpdate()>0;
			conexion.commit();
			preparar.close();
			conexion.close();


		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error :" + e2);
			}

			System.out.println("Error :" + e);
		}
		
		return estadoOperacion;
	}

	// obtener Producto
	public Producto obtenerProducto(int idProducto) {
		Producto producto = new Producto();
		String consulta = "SELECT * FROM crud.productos WHERE id = ?";
		try {
			conexion = obtenerConexion();
			conexion.setAutoCommit(false);
			preparar = conexion.prepareStatement(consulta);
			preparar.setInt(1,idProducto);
			resulSet = preparar.executeQuery();
            if (resulSet.next()) {
            	
            	producto.setId(resulSet.getInt("id"));	
            	producto.setNombre(resulSet.getString("nombre"));
            	producto.setCantidad(resulSet.getInt("cantidad"));
            	producto.setPrecio(resulSet.getDouble("precio"));
            	producto.setFechaCrear(resulSet.getDate("fecha_crear"));
            	producto.setFechaActualizar(resulSet.getDate("fecha_actualizar"));

			}
			conexion.commit();
			preparar.close();
			conexion.close();
			resulSet.close();

		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error :" + e2);
			}

			System.out.println("Error :" + e);
		}
		
		return producto;
	}

	// obtener Productos
	public ArrayList<Producto> obtenerProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String consulta = "SELECT * FROM crud.productos";
		try {
			conexion = obtenerConexion();
			conexion.setAutoCommit(false);
			preparar = conexion.prepareStatement(consulta);
			resulSet = preparar.executeQuery();
            while (resulSet.next()) {
            	Producto e = new Producto();
            	e.setId(resulSet.getInt("id"));	
            	e.setNombre(resulSet.getString("nombre"));
            	e.setCantidad(resulSet.getInt("cantidad"));
            	e.setPrecio(resulSet.getDouble("precio"));
            	e.setFechaCrear(resulSet.getDate("fecha_crear"));
            	e.setFechaActualizar(resulSet.getDate("fecha_actualizar"));
				productos.add(e);	
			}
			conexion.commit();
			preparar.close();
			conexion.close();
			resulSet.close();

		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error :" + e2);
			}

			System.out.println("Error :" + e);
		}
		
		return productos;

	}

	// obtener conexion
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}
