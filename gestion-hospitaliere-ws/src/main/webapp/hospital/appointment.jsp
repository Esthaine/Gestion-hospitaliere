<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.Rendezvous" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.RendezVousDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.RendezVousDaoImpl" %>
<%@ page import="java.util.concurrent.ExecutionException" %>
<%@ page import="com.gestion.hospitaliere.entity.RendezVousStatus" %>
<jsp:include page="components/topbar.jsp" />
<%
    List<Rendezvous> rendezvousList = (List<Rendezvous>) request.getAttribute("rendezvousList");
    String action = (String) request.getParameter("action");
    Long rendezvousId = (Long) request.getAttribute("rendezvousId");
    Long personId = (Long) request.getAttribute("personId");
    PersonDao personDao;
    RendezVousDao rendezVousDao;
    Person person = null;
    Rendezvous rendezVs = null;


    try {
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <% if (action == null) {%>
        <div class="header-content">

<%--            <button class="btn btn-green">Exporter liste des rendez-vouis medicales</button>--%>
            <form>
                <input type="search" placeholder="recherche par nom du patient">
                <button>Recherche</button>
            </form>
        </div>
        <table class="table-mapper">
            <thead>
            <th>No</th>
            <th>Prenom</th>
            <th>Nom</th>
            <th>Date</th>
            <th>Voir</th>
            <th>Action</th>
            </thead>
            <tbody>
            <%}%>
                <%
                    if (action == null) {
                        if (rendezvousList != null && rendezvousList.size() > 0) {
                            for (Rendezvous rendezvous : rendezvousList) {
                                if (personDao != null){
                                    try{
                                        if (personDao.findByUserId(rendezvous.getPerson().getUser().getId()) != null){
                                            person = personDao.findByUserId(rendezvous.getPerson().getUser().getId());
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                    %>
                            <tr>
                                <td>
                                    <%= rendezvous.getId()%>
                                </td>
                                <td>
                                    <%= person != null && person.getGivenName() != null ? person.getGivenName(): "" %>
                                </td>
                                <td>
                                    <%= person != null && person.getFirstName() != null ? person.getFirstName(): "" %>
                                </td>
                                <td>
                                    <%= rendezvous.getDateRendezVous()%>
                                </td>
                                <td>
                                    <%
                                        Person patient = null;
                                        try{
                                            if (personDao.findByUserId(rendezvous.getDocteurSet().getId()) != null){
                                                patient = personDao.findByUserId(rendezvous.getDocteurSet().getId());
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        if (patient != null) {
                                    %>
                                    <p>
                                        Dr.
                                        <%= patient.getGivenName() != null ? patient.getGivenName() : "" %>
                                        <%= patient.getFirstName() != null ? patient.getFirstName() : ""%>
                                        <%= patient.getLastName() != null ? patient.getLastName() : "" %>
                                    </p>
                                    <%}%>
                                </td>
                                <td>
                                    <a href="<%= request.getContextPath()%>/hopital/patient/premierSoin?rendezVousId=<%=rendezvous.getId()%>&patientId=<%=rendezvous.getPerson().getId()%>" class="btn btn-purple">Voir</a>
                                    <% if (!rendezvous.getStatus().equals(RendezVousStatus.ANNULER)) {%>
                                        <a href="<%= request.getContextPath()%>/hopital/patient/rendezVous?rendezVousId=<%=rendezvous.getId()%>&patientId=<%=rendezvous.getPerson().getId()%>" class="btn btn-green">Reprogrammer</a>
                                        <a href="<%= request.getContextPath()%>/hopital/rendez-vous?action=cancel&rendezVousId=<%=rendezvous.getId()%>&patientId=<%=rendezvous.getPerson().getId()%>" class="btn btn-red">Annuler</a>
                                    <%}%>
                                </td>
                            </tr>
                <%
                            }
                        }
                    }
                %>
            <% if (action == null) {%>
                </tbody>
            <%}%>
        </table>
    </div>
</div>