<%-- 
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
--%>

<%@page import="java.util.HashMap"%>
<%@page import="presentation.curso.ModelCurso"%>
<%@page import="java.util.Map"%>

<% Map<String, String> errores = (Map<String, String>)request.getAttribute("errores"); %>
<% ModelCurso model = (ModelCurso)request.getAttribute("model"); %>
<% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar curso</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <form class ="formulario" enctype="multipart/form-data" action="/CursosLibres/presentation/curso/agregar" method="POST">
            <h2>Registrar curso</h2>
            <div class="sesion">
                <div class="nombre">
                    <span>Nombre del curso: </span>
                    <input class="<%=comprobarErrores("nombre", errores)%>" type="text"
                        name="nombre" value="<%=form.get("nombre")[0]%>">
                </div>
                <div class="tematica">
                    <span>Temática: </span>
                    <input class="<%=comprobarErrores("tematica", errores)%>" type="text"
                           name="tematica" value="<%=form.get("tematica")[0]%>">
                </div>
                <div class="estatus">
                    <span>Estatus: </span>
                    <select name="estatus">
                        <option value="en oferta">En oferta</option>
                        <option value="sin oferta">Sin oferta</option>
                    </select>
                </div>
                <div class="precio">
                    <span>Precio: </span>
                    <input class="<%=comprobarErrores("precio", errores)%>" type="text"
                          name="precio" value="<%=form.get("precio")[0]%>">
                </div>  
                <div>
                    <span>Imagen del curso: </span>
                    <input type="file" name="imagen">
                </div>
                <div>
                    <input type="submit" value="Registrar curso", class="boton">
                </div>
            </div>
        </form>
            <% if (errores != null){ %>
                <div>
                    <h3 class="errores"><%=title("nombre", errores)%></h3>
                    <h3 class="errores"><%=title("tematica", errores)%></h3>
                    <h3 class="errores"><%=title("estatus", errores)%></h3>
                    <h3 class="errores"><%=title("precio", errores)%></h3>
                    <h3 class="errores"><%=title("imagen", errores)%></h3>
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

    private Map<String,String[]> getForm(ModelCurso model){
       Map<String,String[]> values = new HashMap<>();
       values.put("nombre", new String[]{model.getCurso().getNombre()});
       values.put("tematica", new String[]{model.getCurso().getTematica()});
       values.put("estatus", new String[]{model.getCurso().getEstatus()});
       values.put("precio", new String[]{String.valueOf(model.getCurso().getPrecio())});
       return values;
    }
%>
