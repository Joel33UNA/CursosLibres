<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="logic.Profesor"%>
<%@page import="presentation.profesor.ModelProfe"%>
<% ModelProfe model = (ModelProfe)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/profesor.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de profesores</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario" action="/CursosLibres/presentation/profesor/buscar" method="post">
            <div>
                <h1>¡Aquí los profesores disponibles!</h1>
                <h2>
                    Si desea matricular los profesores, haga clic <a href="/CursosLibres/presentation/administrador/showprof">aquí</a>.
                </h2>
            </div>
            <div>
                <table border>
                    <thead>
                        <tr >
                            <th>ID</th> <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Profesor p : model.getProfesores()){ %>
                        <tr>
                            <td> <%= p.getId() %> </td>
                            <td><a href="/"> <%= p.getNombre() %> </a></td> 
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div>
                <br> 
                    Escriba aquí el nombre del profesor que desea buscar: 
                    <input  type="text" name="buscar" value=" "></input>
                    <input type="submit" value="Buscar" class="boton"></input>
                </br>
            </div>
        <%@ include file="/presentation/footer.jsp" %>
        </form>
    </body>
</html>
