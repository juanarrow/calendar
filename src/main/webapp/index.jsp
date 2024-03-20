<%-- index.jsp (proyecto Incrementa5) --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modules.Calendario"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
</head>
<body>
    <%
      Calendario cal = new Calendario();
      String fecha = request.getParameter("fecha");
      if(fecha!=null && fecha.length()>0)
        cal.setFecha(fecha);
      
      out.print(cal);
    %>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>