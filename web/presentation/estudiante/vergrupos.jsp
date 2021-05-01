<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="logic.Grupo"%>
<%@page import="presentation.grupo.ModelGrupo"%>
<% ModelGrupo model = (ModelGrupo)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/curso.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matricular grupo</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <h1>Aquí los grupos disponibles del curso seleccionado</h1>
        <h2>Si desea agregar matricular un grupo, haga click en él</h2>
        <form class="formulario" action="/CursosLibres/presentation/grupo/matricula" method="post">
            <table border>
                <thead>
                    <tr >
                        <th>ID</th> <th>Horario</th> <th>Profesor</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Grupo grupo : model.getGrupos()){ %>
                    <tr>
                        <td><a href="/CursosLibres/presentation/grupoestudiante/matricula?gru=<%=grupo.getId()%>"><%= grupo.getId() %></a></td>
                        <td><%= grupo.getHorario() %></td>
                        <td>
                            <% if (grupo.getProfesor() != null) { %>    
                                <%= grupo.getProfesor().getNombre() %> 
                            <% } %>
                            <% if (grupo.getProfesor() == null){ %>
                                Este grupo no posee profesor
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
