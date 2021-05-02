<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="presentation.grupoestudiante.ModelGrupoEst"%>
<%@page import="logic.Curso"%>
<% ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/curso.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <h1>Aquí el historial de todos los cursos registrados por el estudiante</h1>
        <form class="formulario">
            <table border>
                <thead>
                    <tr >
                        <th>Curso</th>
                    </tr>
                </thead>
                <tbody>
                    <% if(model.getCursos().isEmpty()){ %>
                        <h2>No hay cursos para este estudiante.</h2>   
                    <% } %>
                    <% if(!model.getCursos().isEmpty()){ %>
                        <% for(Curso curso : model.getCursos()){ %>
                            <tr>
                                <td> <%= curso.getNombre()%></td>
                            </tr>
                        <% } %>
                    <% } %>
                </tbody>
            </table>
            <h2>Si desea generar una constancia de los cursos matriculados, haga clic
                <a href="/CursosLibres/presentation/grupoestudiante/print?idEst=<%= model.getGrupoEstudiante().getEstudiante().getId() %>"> aquí.</a>
            </h2>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
