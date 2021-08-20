<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Producto</title>
</head>
<body>
<h1>Editar un producto</h1>
<br>

<form action="ProductoController" method="post">
<c:set var="producto" value="${producto}"></c:set>
<input type="hidden" name="opcion" value="editar">
<label>id  :</label> <input   type="text" name="id" value="${producto.id}" ><br><br>
<label>Nombre  :</label> <input   type="text" name="nombre" value="${producto.nombre}" ><br><br>
<label>Cantidad:</label> <input  type="text" name="cantidad"value="${producto.cantidad}" ><br><br>
<label>Precio  :</label> <input  type="text" name="precio" value="${producto.precio}"><br><br>
<label>Fecha creacion  :</label> <input disabled="disabled"  type="text" name="fechaC" value="${producto.fechaCrear}" ><br><br>
<label>Fecha actualizacion  :</label> <input  disabled="disabled" type="text" name="fechaA" value="${producto.fechaActualizar}" ><br><br>
<input type="submit" value="Enviar">
</form>

</body>
</html>