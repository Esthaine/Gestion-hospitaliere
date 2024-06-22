<%@ page import="com.gestion.hospitaliere.model.UserDto" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<jsp:include page="components/topbar.jsp" />
<%
    UserDto user = null;
    PersonDao personDao = null;
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");

    if (session.getAttribute("authenticated") != null){
        user = (UserDto) session.getAttribute("authenticated");
    }
    try{
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
    }catch (Exception e) {
        e.printStackTrace();
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <div>
            <h2>Profile utilisateur</h2>
        </div>
        <div>
            <%
                Person person = null;
                if (user != null){
                    if (personDao != null && personDao.findByUserId(user.getUserId()) != null ) {
                        person = personDao.findByUserId(user.getUserId());
                    }
            %>
                <h3>Infomation:</h3>
                <p>
                    Nom complet:  <%= person != null && person.getGivenName() != null ? person.getGivenName() : "" %>
                    <%= person != null && person.getFirstName() != null ? person.getFirstName() : "" %>
                    <%= person != null && person.getLastName() != null ? person.getLastName() : "" %>
                </p>
                <p>Adresse: </p>
                <p>Date de naissance:  <%= person != null && person.getDateOfBirth() != null ? person.getDateOfBirth() : "" %></p>
            <%}%>
        </div>
        <div class="update-profile">
            <h2>Modifier profile</h2>

            <%
                if (error != null){
            %>
                <span class="error"><%=error%></span>
            <%
                }
            %>
            <%
                if (success != null){
            %>
                <span class="success"><%=success%></span>
            <%
                }
            %>
            <form action="<%= request.getContextPath()%>/hopital/profile" method="post">
                <div class="form-group">
                    <label>Ancien mot de passe *:</label>
                    <input type="password" name="oldPassword"/>
                </div>

                <div class="form-group">
                    <label>Nouveau mot de passe *:</label>
                    <input type="password" name="newPassword"/>
                </div>

                <div class="form-group">
                    <label>Comfirmer mot de passe *:</label>
                    <input type="password" name="confirmPassword"/>
                </div>
                <button type="submit">Update Profile</button>
            </form>
        </div>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>