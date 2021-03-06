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

<% Map<String, String> errores = (Map<String, String>)request.getAttribute("errores"); %>
<% ModelLogin model = (ModelLogin)request.getAttribute("model"); %>
<% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
<% Map<String, String> gru = (Map<String, String>)request.getAttribute("gru"); %>
<% Map<String, String> pass = (Map<String, String>)request.getAttribute("password"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/231dc4ad61.js" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class="formulario"
            <% if(gru == null){ %>
                action="/CursosLibres/presentation/login/login"
            <% } %>
            <% if(gru != null){ %>
                <% if(gru.equals("null") == false){ %>
                    action="/CursosLibres/presentation/login/login?gru=<%=gru.get("gru")%>"
                <% } %>
            <%}%>
              method="post">
            <h2>Iniciar sesión</h2>
            <div class="sesion">
                <div class="id">
                    <i class="far fa-smile"></i>
                    <input class="<%=comprobarErrores("id", errores)%>" type="text"
                           name="id" placeholder="Identificación" 
                           value="<%=form.get("id")[0]%>" tittle="<%=title("id", errores)%>"></input>
                </div>
                <div class="password">
                    <i class="fas fa-lock"></i>
                    <input class="<%=comprobarErrores("password", errores)%>" type="password", 
                           name="password" placeholder="Contraseña"
                           value="<%=form.get("password")[0]%>" title="<%=title("password", errores)%>"></input>
                </div>
                <div class="signin">
                    <p>¿No tenés cuenta aún?
                    <% if(gru == null){ %>
                         <a href="/CursosLibres/presentation/signin/show">registrate aquí</a>
                    <% } %>
                    <% if(gru != null){ %>
                    <a href="/CursosLibres/presentation/signin/show?gru=<%=gru.get("gru")%>">registrate aquí</a>
                    <% } %>
                    .</p>
                </div>
                <div>
                    <input type="submit" value="Iniciar Sesión" class="boton"></input>
                </div>
            </div>
        </form>
        <% if (pass != null && errores == null){ %>
            <div><h3 class="password">Su nueva contraseña es: <%=pass.get("password")%></h3>
        <% } %>
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

    private Map<String,String[]> getForm(ModelLogin model){
       Map<String,String[]> values = new HashMap<>();
       values.put("id", new String[]{model.getUsuario().getId()});
       values.put("password", new String[]{model.getUsuario().getClave()});
       return values;
    }
%>
