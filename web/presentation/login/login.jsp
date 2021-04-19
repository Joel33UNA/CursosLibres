<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="presentation.login.ModelLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% ModelLogin modelLogin= (ModelLogin) request.getAttribute("modelLogin"); %>
<% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
<% Map<String,String[]> form = (errores==null)?this.getForm(modelLogin):request.getParameterMap();%>
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
        <form class="formulario", name="form" action="/CursosLibres/presentation/login/login" method="post" >
            <h2>Iniciar sesión</h2>
            <div class="sesion">
                <div class="id">
                    <i class="far fa-smile"></i>
                    <input type="text" placeholder="Identificación" class="<%=erroneo("cedulaFld",errores)%>" name="cedulaFld" value="<%=form.get("cedulaFld")[0]%>" title="<%=title("cedulaFld",errores)%>"></input>
                </div>
                <div class="password">
                    <i class="fas fa-lock"></i>
                    <input type="password", placeholder="Contraseña" class="<%=erroneo("cedulaFld",errores)%>" name="cedulaFld" value="<%=form.get("cedulaFld")[0]%>" title="<%=title("cedulaFld",errores)%>"></input>
                </div>
                <div>
                    <input type="submit" value="Iniciar Sesión" class="boton"></input>
                </div>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>

<%!
    private String erroneo(String campo, Map<String,String> errores){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return "is-invalid";
      else
        return "";
    }

    private String title(String campo, Map<String,String> errores){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return "";
    }

    private Map<String,String[]> getForm(ModelLogin modelLogin){
       Map<String,String[]> values = new HashMap<>();
       values.put("cedulaFld", new String[]{modelLogin.getUsuario().getId()});
       values.put("claveFld", new String[]{modelLogin.getUsuario().getId()});
       return values;
    }
%>