<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="logic.Grupo"%>
<%@page import="java.util.List"%>
<% List<Grupo> grupos = (List<Grupo>)request.getAttribute("grupos"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/profesor.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grupos a cargo</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <h1>¡Aquí los grupos disponibles!</h1>
        <% if (grupos.isEmpty()){%>
            <h1>Actualmente, usted no posee ningún grupo asignado.</h1>
        <% } %>
        <% if (!grupos.isEmpty()){ %>
        <table border>
            <thead>
                <tr><th>Curso</th><td>Horario</td></tr>
            </thead>
            <tbody>
                <% for(Grupo grupo : grupos){ %>
                    <tr>
                        <td><% grupo.getCurso().getNombre(); %></td>
                        <td><% grupo.getHorario(); %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
