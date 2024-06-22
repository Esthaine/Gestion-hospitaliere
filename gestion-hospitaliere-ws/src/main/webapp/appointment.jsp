<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.Departement" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.DepartementDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.DepartementDao" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Departement> departements = null;
    String docteurId = null;
    PersonDao personDao = null;
    Person person = null;
    Departement department = null;
    UserDao userDao = null;
    if (request.getParameter("docteurId") != null) {
        docteurId = request.getParameter("docteurId");
    }
    try{
        DepartementDao departementDao = new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        departements = departementDao.findAll();

        if (docteurId != null){
            person = personDao.findByUserId(Long.parseLong(docteurId));
            User user = userDao.findById(Long.parseLong(docteurId));
            department = departementDao.findById(user.getDepartement().getId());
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Rendez-Vous</title>
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <div class="main">
        <div class="main-header">
            <h2>Rendez-vous medical</h2>
        </div>
        <div class="form-patient">
            <form action="<%= request.getContextPath()%>/appointement" method="post">
                <div class="form-group">
                    <label>Date de rendez-vous * :</label>
                    <input type="date" name="date-rdv">
                </div>
                <div class="form-group">
                    <%
                        if (docteurId == null){
                    %>
                    <label>Selectionner le service :</label>
                    <select name="departements">
                        <% if (departements != null) {
                            for (Departement departement: departements) { %>
                                <option value="<%=departement.getId()%>"><%=departement.getNomDepartement()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <%}%>
                    <%
                        if (docteurId != null){
                    %>
                        <p> Dr. <%= person != null && person.getGivenName() != null ? person.getGivenName() : ""%>
                            <%= person != null && person.getFirstName() != null ? person.getFirstName() : ""%>
                            <%= person != null && person.getLastName() != null ? person.getLastName() : ""%>
                        </p>
                        <span><%= department != null && department.getNomDepartement() != null ? department.getNomDepartement() : ""%></span>
                        <input type="hidden" value="<%= docteurId %>" name="docteurId">
                        <input type="hidden" value="<%= department != null ? department.getNomDepartement(): ""%>" name="department">
                    <%
                        }
                    %>
                </div>
                <button type="submit">Soumettre</button>
            </form>
        </div>
    </div>
    <jsp:include page="components/footer.jsp"/>
</body>
</html>

