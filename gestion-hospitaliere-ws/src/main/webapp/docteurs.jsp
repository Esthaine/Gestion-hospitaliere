<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.utils.AppConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Gestion Hospitaliere</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="main">
    <h2>Docteurs</h2>
    <p>Liste des docteurs et leurs department respectif</p>
    <div class="doctors">
        <%
            UserDao userDao = null;
            try {
                userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<User> users = userDao.listOfUserPerRole(AppConst.MEDECIN);
            for (User user : users) {
        %>
            <div class="doctor">
                Dr
<%--                <% if (user.getPerson().getFirstName() != null) { %>--%>
<%--                    <h1><%=user.getPerson().getFirstName()%></h1>--%>
<%--                <%}%>--%>

                <a href="<%=request.getContextPath()%>/appointement?docteurId=<%=user.getId()%>">Prendre un rendez-vous</a>
            </div>
        <%}%>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
