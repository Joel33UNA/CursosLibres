<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="java.util.HashMap"%>
<%@page import="presentation.grupo.ModelGrupo"%>
<%@page import="java.util.Map"%>

<% Map<String, String> errores = (Map<String, String>)request.getAttribute("errores"); %>
<% ModelGrupo model = (ModelGrupo)request.getAttribute("model"); %>
<% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar grupo</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class ="formulario" enctype="multipart/form-data" action="/CursosLibres/presentation/grupo/agregar" method="POST"> 
            <h2>Registrar grupo</h2>
            <div class="sesion">
                <div class="horario">
                    <span>Horario: </span>
                    <input class="<%=comprobarErrores("horario", errores)%>" type="text"
                        name="horario" value="<%=form.get("horario")[0]%>">
                </div>
                <div class="profesor">
                    <span>ID del profesor: </span>
                    <input class="<%=comprobarErrores("profesor", errores)%>" type="text"
                        name="profesor" value="<%=form.get("profesor")[0]%>">
                </div>
                <div>
                    <input type="submit" value="Registrar grupo", class="boton">
                </div>
            </div>
        </form>     
        <% if (errores != null){ %>
            <div>
                <h3 class="errores"><%=title("horario", errores)%></h3>
                <h3 class="errores"><%=title("profesor", errores)%></h3>
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

    private Map<String,String[]> getForm(ModelGrupo model){
       Map<String,String[]> values = new HashMap<>();
       values.put("horario", new String[]{model.getGrupo().getHorario()});
       values.put("profesor", new String[]{model.getGrupo().getProfesor().getId()});
       return values;
    }
%>