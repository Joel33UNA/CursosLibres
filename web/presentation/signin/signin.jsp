<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="presentation.signin.ModelSignin"%>

<% Map<String, String> errores = (Map<String, String>)request.getAttribute("errores"); %>
<% ModelSignin model = (ModelSignin)request.getAttribute("model"); %>
<% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class ="formulario" action="presentation/signin/signin" method="post">
            <h2>Registrarse</h2>
            <div class="sesion">
                <div class="id">
                    <i class="fas fa-id-card"></i>
                    <input class="<%=comprobarErrores("id", errores)%>" type="text"
                           name="id", placeholder="Digite su identificación"
                           value="" tittle="<%=title("id", errores)%>"></input>  
                </div>
                <div>
                    <input type="submit" value="Registrarse", class="boton"></input>
                </div>
            </div>
        </form>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>

<%!
    private String comprobarErrores(String texto, Map<String, String> errores){
        if(errores != null && errores.get(texto)!= null){ return "invalido"; }
        return "";
    }

    private String title(String texto, Map<String,String> errores){
      if ((errores != null) && (errores.get(texto) != null) )
        return errores.get(texto);
      else
        return "";
    }

    private Map<String,String[]> getForm(ModelSignin model){
       Map<String,String[]> values = new HashMap<>();
       //values.put("id", new String[]{model.getUsuario().getId()});
       //values.put("password", new String[]{model.getUsuario().getClave()});
       return values;
    }
%>