<%-- index.jsp (proyecto Incrementa5) --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modules/Calendario"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <%
      Calendario cal = new Calendario();
      out.print(cal);
    %>
</body>
</html>