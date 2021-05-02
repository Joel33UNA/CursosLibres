<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="java.util.HashMap"%>
<%@page import="presentation.login.ModelLogin"%>
<%@page import="java.util.Map"%>

<% ModelLogin model = (ModelLogin)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/231dc4ad61.js" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Información del usuario</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario">
            <h2>Información del usuario actual</h2>
            <div class="sesion">
                <div class="id">
                    <i>Identificación: <%=model.getUsuario().getId()%></i>
                </div>
                <div class="id">
                    <i>Nombre: <%=model.getUsuario().getNombre()%></i>
                </div>
                <div class="id">
                    <i>Clave: <%=model.getUsuario().getClave()%></i>
                </div>
                <div class="id">
                    <i>Rol: <%=model.getUsuario().getRol()%></i>
                </div>
                <div class="id">
                    <i>Teléfono: <%=model.getUsuario().getTelefono()%></i>
                </div>
                <div class="id">
                    <i>Correo: <%=model.getUsuario().getCorreo()%></i>
                </div>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>