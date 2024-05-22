<%@ page import="com.gestion.hospitaliere.model.UserDto" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDto user = (UserDto) session.getAttribute("authenticated");
    Person person = null;
    PersonDao personDao = null;
    try{
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        person = personDao.findByUserId(user.getUserId());
    }catch (Exception e) {
        e.printStackTrace();
    }

%>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Gestion Hospitaliere</title>
</head>
<%
    String action = (String) request.getParameter("action");
%>
<body>
<jsp:include page="components/header.jsp"/>
    <div class="main">
        <div class="main-header">
            <a href="<%=request.getContextPath()%>/patient/profile">Retour</a>
            <h2>Profile de l'utilisateur</h2>
        </div>
        <div>
            <% if (action == null) {%>
                <div>
                    Nom complet:
                    <p>
                        <%= person != null && person.getFirstName() != null ? person.getFirstName(): "" %>
                        <%= person != null && person.getGivenName() != null ? person.getGivenName(): "" %>
                        <%= person != null && person.getLastName() != null ? person.getLastName(): "" %>
                    </p>
                    <a href="<%=request.getContextPath()%>/patient/profile?action=edit&userProfileId=<%=user.getUserId()%>">Modifier Mot de passe</a>
                </div>
            <%}%>
            <%
                if (action != null) {
                  if (action.equals("edit")) {%>
                <div>
                    <form action="<%=request.getContextPath()%>/patient/profile" method="post">
                        <div class="form-group">
                            <label>Ancien Mot de passe</label>
                            <input type="text" name="old_password">
                        </div>
                        <div class="form-group">
                            <label>Nouveau Mot de passe</label>
                            <input type="text" name="new_password">
                        </div>
                        <div class="form-group">
                            <label>Confirmer Mot de passe</label>
                            <input type="text" name="confirm_new_password">
                        </div>
                        <input type="hidden" value="<%=user.getUserId()%>" name="user_id">
                        <button type="submit">Modifier Profile</button>
                    </form>
                </div>
            <%
                }
             }
            %>
        </div>
    </div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
