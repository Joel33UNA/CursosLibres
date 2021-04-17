<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import = "logic.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("user"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <div class="logo">
        <span>cursoslibres.com</span>
    </div>
    <div class="submenu">
        <ul>
            <li><a href="../CursosLibres">Inicio</a></li>
            <li><a href="/">Visualizar cursos</a></li>
            <li><a href="login/login.jsp">Iniciar sesión</a></li>
            <li><a href="/">Registrarse</a></li>
        </ul>
    </div>
</header>
