<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.Rendezvous" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.entity.RendezVousStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<Rendezvous> rendezvousList = (List<Rendezvous>) request.getAttribute("rendezvousList");
    PersonDao personDao = null;
    try {
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
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
                    Date rendez-vous:
                    <p><%=rdv.getDateRendezVous()%></p>
                    Medecin:
                    <p>
                        <%= person != null && person.getFirstName() != null ? person.getFirstName(): "" %>
                        <%= person != null && person.getGivenName() != null ? person.getGivenName(): "" %>
                        <%= person != null && person.getLastName() != null ? person.getLastName(): "" %>
                    </p>
                    Status: <span class="<%=rdv.getStatus().toString().toLowerCase()%>"><%=rdv.getStatus()%></span>
                    <%if (rdv.getStatus().equals(RendezVousStatus.NOUVEAU)) { %>
                        <div class="action">
                            <a href="<%= request.getContextPath()%>/appointement?rendezVousId=<%=rdv.getId()%>&action=edit">Modifier</a>
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
