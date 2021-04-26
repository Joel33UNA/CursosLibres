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
        <title>Administrar cursos</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="form" action="/CursosLibres/presentation/curso/buscar" method="post">
            <div>
            <h1>¡Aquí los cursos disponibles!</h1>
            <h2>
                Si desea agregar un nuevo curso, haga clic <a href="/CursosLibres/presentation/curso/showcursoadd">aquí</a>.
            </h2>
        </div>
        <div>
            <table border>
                <thead>
                    <tr >
                        <th>ID</th><th>Nombre</th><th>Temática</th><th>Estatus</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Curso c : model.getCursos()){ %>
                    <tr>
                        <td> <%= c.getId() %> </td>
                        <td><%=c.getNombre()%></td>
                        <td><%=c.getTematica()%></td>
                        <td><%=c.getEstatus()%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <div>
            <br> 
                Escriba aquí el ID del curso que desea buscar: 
                <input  type="text" name="buscar" value=" "></input>
                <input type="submit" value="Buscar" class="boton"></input>
            </br>
        </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
