<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/> <%-- incluimos la hoja de cascada header.css --%>
        <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/> <%-- incluimos la hoja de cascada index.css --%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cursoslibres.com</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %> <%-- incluimos el archivo cabecera header.jsp --%>
        <h3>¡Cursos libres para todos!</h3>
        
        <%@ include file="/presentation/footer.jsp" %> <%-- incluimos el archivo cabecera footer.jsp --%>
    </body>
</html>
