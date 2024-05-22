<%@ page import="com.gestion.hospitaliere.model.UserDto" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Gestion Hospitaliere</title>
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <div class="main">
        <% if (session.getAttribute("authenticated") == null) {%>
            <div class="banner">
                <div class="wrapper">
                    <div class="info">
                        <h2>Bienvenue a Maman Yemo Sendwe Hospital</h2>
                        <p>Caring is ou mission</p>
                    </div>
                    <a class="btn btn-start" href="<%= request.getContextPath()%>/authentication?action=patients">
                        Commencez ici
                    </a>
                </div>
            </div>
        <%}%>
        <div class="liens-service">
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <a href="<%=request.getContextPath()%>/docteurs">Docteurs</a>
            </div>
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <a href="<%=request.getContextPath()%>/patient/profile">Profile</a>
            </div>
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <a href="<%= request.getContextPath() %>/appointement">Rendez-vous Medical</a>
            </div>
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <a href="<%= request.getContextPath()%>/historique/rendezVous">Historiques Rendez-vous</a>
            </div>
        </div>
    </div>
    <jsp:include page="components/footer.jsp"/>
</body>
</html>
