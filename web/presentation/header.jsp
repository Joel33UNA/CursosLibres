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
            <% if (usuario==null){%>
                <li><a href="/CursosLibres">Inicio</a></li>
                <li><a href="/CursosLibres/presentation/login/show">Iniciar sesión</a></li>
                <li><a href="/">Registrarse</a></li>
                <li><a href="/">Visualizar cursos</a></li>
            <% } %>
            <% if (usuario!=null){%>
                <li><a href="/CursosLibres">Inicio</a></li>
                <% if (usuario.getRol() == "estudiante"){%>
                    <li><a href="/">Matricular</a></li>
                    <li><a href="/">Registro estudiante</a></li>
                    <li><a href="/">Historial</a></li>
                    <li><a href="/">Constancia</a></li>
                    <li><a href="/">Cerrar sesión</a></li>
                <% } %>
                <% if (usuario.getRol() == "profesor"){%>
                    <li><a href="/">Grupos a cargo</a></li>
                    <li><a href="/">Registrar notas</a></li>
                    <li><a href="/">Cerrar sesión</a></li>
                <% } %>
                <% if (usuario.getRol() == "administrador"){%>  
                    <li><a href="/">Listado y registro de cursos</a></li>
                    <li><a href="/">Listado y registro de profesores</a></li>
                    <li><a href="/">Abrir grupo</a></li>
                    <li><a href="/CursosLibres/presentation/login/logout">Cerrar sesión</a></li>
                <% } %>
            <% } %>      
        </ul>
    </div>
</header>
