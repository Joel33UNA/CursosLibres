<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>


<%@page import="presentation.grupo.ModelGrupo"%>
<% ModelGrupo model = (ModelGrupo)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grupos</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario" action="/CursosLibres/presentation/grupo/matricula" method="post">
            <h1>Aquí los grupos correspondientes al curso <%= model.getGrupos().get(0).getCurso().getNombre()%> </h1>
            <h2>Dele clic al horario del grupo para matricular si así lo desea.</h2>
            <table border>
                <thead>
                    <tr >
                        <th>ID</th> <th>Horario</th> <th>Profesor</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int i = 0; i < model.getGrupos().size(); i++){ %>
                    <tr>
                        <td> <%= model.getGrupos().get(i).getId() %> </td>
                        <td><a href="/presentation/grupo/matricula"> <%= model.getGrupos().get(i).getHorario() %> </a></td>
                        <td> <%= model.getGrupos().get(i).getProfesor().getNombre() %> </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>