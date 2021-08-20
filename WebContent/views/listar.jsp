<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Productos</title>
</head>
<body>
	<h1>Listar producto</h1>
	<table border="1">
		<tr>
			<td>id</td>
			<td>nombre</td>
			<td>cantidad</td>
			<td>precio</td>
			<td>fecha creacion</td>
			<td>fecha aztualizacion</td>
			<td>Eliminar</td>
		</tr>

		<c:forEach var="producto" items="${productos}">
			<tr>
				<td><a href="ProductoController?opcion=editar&id=${producto.id}">${producto.id}</a></td>
				<td><c:out value="${producto.nombre}"></c:out></td>
				<td><c:out value="${producto.cantidad}"></c:out></td>
				<td><c:out value="${producto.precio}"></c:out></td>
				<td><c:out value="${producto.fechaCrear}"></c:out></td>
				<td><c:out value="${producto.fechaActualizar}"></c:out></td>
				<td><a href="ProductoController?opcion=eliminar&id=${producto.id}">Eliminar</a></td>				
								
			</tr>
		</c:forEach>

	</table>
</body>
</html>
<%-- FORMA NATIVA DE JAVA DE MOSTAR DATAS DE UN ARRAY ->->->->
<%@page import="java.util.ArrayList"%>
<%@page import= "com.empresa.modelo.Producto" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Productos</title>
</head>
<body>
<h1>Listar producto</h1>
<table border="1">
 <tr>
   <td>id</td>
   <td>nombre</td>
   <td>cantidad</td>
   <td>precio</td>
   <td>fecha creacion</td>
   <td>fecha aztualizacion</td>
 </tr>
 <% ArrayList<Producto> lista = new ArrayList<Producto>();
 lista = (ArrayList<Producto>) request.getAttribute("productos");
 
 for(int i = 1; i < lista.size();i++){
	 Producto p = new Producto();
	 p = lista.get(i);
	 %>
  <tr>
   <td><% out.print(p.getId()); %></td>
   <td><% out.print(p.getNombre()); %></td>
   <td><% out.print(p.getCantidad()); %></td>
   <td><% out.print(p.getPrecio()); %></td>
   <td><% out.print(p.getFechaCrear()); %></td>
   <td><% out.print(p.getFechaActualizar()); %></td>
 </tr>
<%} %>
 
</table>
</body>
</html>
--%>
