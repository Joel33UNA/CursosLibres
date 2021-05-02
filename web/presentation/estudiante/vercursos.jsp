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
        <title>Matricular cursos</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario" enctype="multipart/form-data" action="/CursosLibres/presentation/curso/buscar" method="post">
            <div>
                <h1>¡Aquí los cursos disponibles para la matrícula!</h1>
                <h2> Si desea matricular un curso, haga clic en la imagen.</h2>
            </div>
            <div class="grid-container">
                <% for(Curso c : model.getCursos()){ %>
                    <%if(c.getEstatus().equals("en oferta")){%>
                        <div class="image-container">
                            <p> <%= c.getNombre()%> </p>
                            <p>Precio: <%= c.getPrecio()%> </p>
                            <a href="/CursosLibres/presentation/estudiante/showgru?id=<%=c.getId()%>">
                                <img src='CursosLibres/presentation/curso/image?nombre=<%=c.getNombre()%>'> 
                            </a>
                        </div>
                    <%}%>
                <% } %>
            </div>
            <div>
                <br> 
                    Escriba aquí el nombre del curso que desea buscar: 
                    <input  type="text" name="buscar" value=" "></input>
                    <input type="submit" value="Buscar" class="boton"></input>
                </br>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
