<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.utils.AppConst" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.DepartementDao" %>
<%@ page import="com.gestion.hospitaliere.entity.Departement" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.DepartementDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PersonDao personDao = null;
    Person person = null;
    DepartementDao departementDao =  null;
    Departement departement = null;
    try{
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        departementDao = new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Gestion Hospitaliere</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="main">

    <div class="main-header">
        <h2>Docteurs</h2>
        <p>Liste des docteurs et leurs department respectif</p>
    </div>

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
                try {
                    person = personDao.findByUserId(user.getId());
                    departement = departementDao.findById(user.getDepartement().getId());
                }catch (Exception e){
                    e.printStackTrace();
                }
        %>
        <div class="doctors-item">
            <div class="profile">
            </div>
            <div class="details">
                <div class="left">
                    <h3>
                        Dr. <%= person != null && person.getGivenName() != null ? person.getGivenName() : "" %>
                            <%= person != null && person.getFirstName() != null ? person.getFirstName() : "" %>
                            <%= person != null && person.getLastName() != null ? person.getLastName() : "" %>
                    </h3>
                    <p> <%= departement!= null && departement.getNomDepartement()!= null ? "Specialiste en: " +departement.getNomDepartement() : "Generaliste" %></p>
                </div>
                <div class="right">
                    <a href="<%=request.getContextPath()%>/appointement?docteurId=<%=user.getId()%>">Prendre un rendez-vous</a>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
