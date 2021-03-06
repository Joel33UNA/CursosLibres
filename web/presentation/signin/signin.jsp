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
<% Map<String, String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
<% Map<String, String> pass = (Map<String, String>)request.getAttribute("password"); %>
<% Map<String, String> gru = (Map<String, String>)request.getAttribute("gru"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class ="formulario" 
            <% if(gru == null){ %>
                action="/CursosLibres/presentation/signin/signin"
            <% } %>
            <% if(gru != null){ %>
                <% if(gru.equals("null") == true){ %>
                    action="/CursosLibres/presentation/signin/signin"
                <% } %>
                <% if(gru.equals("null") == false){ %>
                    action="/CursosLibres/presentation/signin/signin?gru=<%=gru.get("gru")%>"
                <% } %>
            <% } %>
              method="post">
            <h2>Registrarse</h2>
            <div class="sesion">
                <div class="id">
                    <span>Identificación: </span>
                    <input class="<%=comprobarErrores("id", errores)%>" type="text"
                           name="id" value="<%=form.get("id")[0]%>" title="<%=title("id", errores)%>">  
                </div>
                <div class="nombre">
                    <span>Nombre completo: </span>
                    <input class="<%=comprobarErrores("nombre", errores)%>" type="text"
                           name="nombre" value="<%=form.get("nombre")[0]%>" title="<%=title("nombre", errores)%>">
                </div>
                <div class="correo">
                    <span>Correo electrónico: </span>
                    <input class="<%=comprobarErrores("correo", errores)%>" type="text"
                           name="correo" value="<%=form.get("correo")[0]%>" title="<%=title("correo", errores)%>">
                </div>
                <div class="telefono">
                    <span>Teléfono: </span>
                    <input class="<%=comprobarErrores("telefono", errores)%>" type="text"
                           name="telefono" value="<%=form.get("telefono")[0]%>" title="<%=title("telefono", errores)%>">
                </div>
                <div>
                    <input type="submit" value="Registrarse", class="boton"
                           onclick="document.getElementById('o').style.display='block';
                           document.getElementById('p').style.display='block';">
                </div>
            </div> 
        </form>
        <% if (pass != null && errores == null){ %>
            <div><h3 class="password">Su nueva contraseña es: <%=pass.get("password")%></h3>
        <% } %>
        <% if (errores != null){ %>
            <div>
                <h3 class="errores"><%=title("id", errores)%></h3>
                <h3 class="errores"><%=title("nombre", errores)%></h3>
                <h3 class="errores"><%=title("correo", errores)%></h3>
                <h3 class="errores"><%=title("telefono", errores)%></h3>
            </div>
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

    private Map<String,String[]> getForm(ModelSignin model){
       Map<String,String[]> values = new HashMap<>();
       values.put("id", new String[]{model.getUsuario().getId()});
       values.put("nombre", new String[]{model.getUsuario().getNombre()});
       values.put("correo", new String[]{model.getUsuario().getCorreo()});
       values.put("telefono", new String[]{String.valueOf(model.getUsuario().getTelefono())});
       values.put("password", new String[]{model.getUsuario().getClave()});
       return values;
    }
%>