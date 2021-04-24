<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="logic.Curso"%>
<%@page import="presentation.curso.ModelCurso"%>
<% ModelCurso model = (ModelCurso)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/curso.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cursos</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario" action="/CursosLibres/presentation/visualizarCursos/buscar" method="post">
            <h1>¡Aquí los cursos disponibles!</h1>
            <h2>Dele clic al nombre del curso para ver los grupos disponibles.</h2>
            <table border>
                <thead>
                    <tr >
                        <th>ID</th> <th>Temática</th> <th>Nombre</th> <th>Estatus</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Curso c: model.getCursos()){ %>
                    <tr>
                        <td class="id"> <%= c.getId()%> </td>
                        <td> <%= c.getTematica() %> </td>
                        <% if(c.getEstatus().equals("en oferta")){ %>
                            <td><a href="/presentation/grupos/show"> <%= c.getNombre() %> </a></td> 
                        <% } %>
                        <% if(!(c.getEstatus().equals("en oferta"))){ %>
                           <td> <%= c.getNombre() %> </td> 
                        <% } %>
                        <td> <%= c.getEstatus()%> </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <div>
                <br> 
                    Escriba aquí el nombre o la temática del curso que quiere buscar: 
                    <input  type="text" name="buscar" value=" "></input>
                    <input type="submit" value="Buscar" class="boton"></input>
                </br>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
