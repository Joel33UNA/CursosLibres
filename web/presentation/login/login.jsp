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
        <link href="../../css/header.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/login.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/231dc4ad61.js" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario">
            <h2>Iniciar sesión</h2>
            <div class="sesion">
                <div class="id">
                    <i class="far fa-smile"></i>
                    <input type="text", placeholder="Identificación"></input>
                </div>
                <div class="password">
                    <i class="fas fa-lock"></i>
                    <input type="password", placeholder="Contraseña"></input>
                </div>
                <div>
                    <input type="submit" value="Iniciar Sesión" class="boton"></input>
                </div>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
