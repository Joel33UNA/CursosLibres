<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import = "logic.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
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
                <li><a href="/CursosLibres/presentation/signin/show">Registrarse</a></li>
                <li><a href="/CursosLibres/presentation/visualizarCursos/show">Visualizar cursos</a></li>
            <% } %>
            <% if (usuario!=null){%>
                <li><a href="/CursosLibres">Inicio</a></li>
                <% if (usuario.getRol().equals("estudiante")){%>
                    <li><a href="/CursosLibres/presentation/estudiante/show">Matricular</a></li>
                    <li><a href="/">Registro estudiante</a></li>
                    <li><a href="/">Historial</a></li>
                    <li><a href="/">Constancia</a></li>
                    <li><a href="/CursosLibres/presentation/login/logout">Cerrar sesión</a></li>
                <% } %>
                <% if (usuario.getRol().equals("profesor")){%>
                    <li><a href="/CursosLibres/presentation/profesor/show"">Grupos a cargo</a></li>
                    <li><a href="/">Registrar notas</a></li>
                    <li><a href="/CursosLibres/presentation/login/logout">Cerrar sesión</a></li>
                <% } %>
                <% if (usuario.getRol().equals("administrador")){%>  
                    <li><a href="/CursosLibres/presentation/administrador/show">Listado y registro de cursos</a></li>
                    <li><a href="/CursosLibres/presentation/profesor/visualizarprofes">Listado y registro de profesores</a></li>
                    <li><a href="/">Abrir grupo</a></li>
                    <li><a href="/CursosLibres/presentation/login/logout">Cerrar sesión</a></li>
                <% } %>
            <% } %>      
        </ul>
    </div>
</header>
