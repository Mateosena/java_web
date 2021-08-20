<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear producto</title>
</head>
<body>
<h1>Crear producto</h1>
<br>
<form action="ProductoController" method="post">
<input type="hidden" name="opcion" value="guardar">
<label>Nombre  :</label> <input  type="text" name="nombre" ><br><br>
<label>Cantidad:</label> <input  type="text" name="cantidad" ><br><br>
<label>Precio  :</label> <input  type="text" name="precio" ><br><br>
<input type="submit" value="Enviar">
</form>
</body>
</html>