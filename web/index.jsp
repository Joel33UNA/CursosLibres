<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="java.util.HashMap"%>
<%@page import="presentation.signin.ModelSignin"%>
<%@page import="java.util.Map"%>

<% Map<String, String> password = (Map<String, String>)request.getAttribute("password"); %>
<% ModelSignin model = (ModelSignin)request.getAttribute("model"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/> <%-- incluimos la hoja de cascada header.css --%>
        <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/> <%-- incluimos la hoja de cascada index.css --%>
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cursoslibres.com</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %> <%-- incluimos el archivo cabecera header.jsp --%>
        <h1>¡Cursos libres para todos!</h1>
        <p class="<%=mostrarPass(password)%>">
            ¡Se ha registrado con éxito!
            Su nueva contraseña es: <%=getPass(model)%>
        </p>
        
        <%@ include file="/presentation/footer.jsp" %> <%-- incluimos el archivo cabecera footer.jsp --%>
    </body>
</html>

<%!
    private String mostrarPass(Map<String, String> registro){
        if(registro != null && registro.get("password")!= null){ return "password"; }
        return "oculto";
    }

    private String getPass(ModelSignin model){
        if (model != null){
            return model.getUsuario().getClave();
        }
       return "";
    }
%>