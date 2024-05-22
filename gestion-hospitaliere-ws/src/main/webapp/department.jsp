<%@ page import="com.gestion.hospitaliere.entity.Departement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Departement</title>
</head>
<body>
  <jsp:include page="components/header.jsp"/>
  <%
      Departement departement = (Departement) request.getAttribute("departementDetails");
  %>
  <div class="main">
      <h3><%= departement.getNomDepartement() %></h3>
      <p><%= departement.getDescription()%></p>
    <%--  Doctors list    --%>
  </div>
  <jsp:include page="components/footer.jsp"/>
</body>
</html>
