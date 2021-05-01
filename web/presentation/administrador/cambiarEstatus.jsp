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
        <title>Cambiar estatus</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <h1>Aquí todos los cursos registrados</h1>
        <h2>
            Si desea cambiar el estatus de un curso, haga clic en el propio estatus.
        </h2>
        <form class="formulario">
            <table border>
                <thead>
                    <tr >
                        <th>Temática</th> <th>Nombre</th> <th>Estatus</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Curso curso : model.getCursos()){ %>
                    <tr>
                        <td><%= curso.getTematica()%></td>
                        <td><%= curso.getNombre()%></td>
                        <td><a href="/CursosLibres/presentation/curso/enviarCambioEstatus?id=<%= curso.getId()%>"><%= curso.getEstatus()%></a></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>

