<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.DepartementDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.DepartementDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<Rendezvous> rendezvousList = (List<Rendezvous>) request.getAttribute("rendezvousList");
    PersonDao personDao = null;
    DepartementDao departementDao = null;
    Departement departement = null;
    UserDao userDao = null;
    User user = null;
    try {
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        departementDao = new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
        userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));

    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
%>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Historique Rendez-vous medicale</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
    <div class="main">
        <div class="main-header">
            <h2>Historique rendez-vous medicale</h2>
        </div>
        <div class="rdv-list">
            <%
                Person person = null;
                if (rendezvousList != null && rendezvousList.size() > 0) {
                    for (Rendezvous rdv : rendezvousList) {
                        if (personDao != null){
                            try{
                                if (personDao.findByUserId(rdv.getDocteurSet().getId()) != null){
                                    person = personDao.findByUserId(rdv.getDocteurSet().getId());
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
            %>
            <div class="rdv-item">
                <div class="date">
                    <p>Date</p>
                    <span><%=rdv.getDateRendezVous()%></span>
                </div>
                <div class="doctor-details">
                    <p> Dr. <%= person != null && person.getFirstName() != null ? person.getFirstName(): "" %>
                        <%= person != null && person.getGivenName() != null ? person.getGivenName(): "" %>
                        <%= person != null && person.getLastName() != null ? person.getLastName(): "" %>
                    </p>
                    <%
                        if (person!= null && person.getUser() != null) {
                            user = userDao.findById(person.getUser().getId());
                        }
                        if (user != null && user.getDepartement() != null) {
                            departement = departementDao.findById(user.getDepartement().getId());
                        }
                    %>
                    <span>
                        <%= departement!= null && departement.getNomDepartement() != null ? departement.getNomDepartement(): ""%>
                    </span>
                </div>

                <div class="status">
                    <p>Status:</p>
                    <span class="<%=rdv.getStatus().toString().toLowerCase()%>"><%=rdv.getStatus()%></span>
                </div>

                <%if (rdv.getStatus().equals(RendezVousStatus.NOUVEAU)) { %>
                    <div class="action">
                        <a href="<%= request.getContextPath()%>/historique/rendezVous?action=cancel&rendezVousId=<%=rdv.getId()%>">Annuler</a>
                    </div>
                <%}%>
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
