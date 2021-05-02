<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="presentation.grupoestudiante.ModelGrupoEst"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar nota</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario" action="/CursosLibres/presentation/grupoestudiante/setearNota?grupo=<%=model.getGrupoEstudiante().getGrupo().getId()%>&id=<%=model.getGrupoEstudiante().getEstudiante().getId()%>" method="post">
            <h1>Digite la nota del estudiante.</h1>
            <div>
                <input type="text" name="nota" placeholder="Nota"> </input>
            </div>
            <div>
                <input type="submit" value="Registrar nota" class="boton"></input>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
