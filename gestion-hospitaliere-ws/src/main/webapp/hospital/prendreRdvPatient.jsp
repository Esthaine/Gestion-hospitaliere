<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.entity.Rendezvous" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<jsp:include page="components/topbar.jsp" />
<%
    List<User> docteurs = (List<User>) request.getAttribute("docteurs");
    Person person = (Person) request.getAttribute("person");
    Rendezvous rendezVous = (Rendezvous) request.getAttribute("rendezVous");
    PersonDao personDao = null;
    try {
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
    }catch (ClassNotFoundException e){
        throw new RuntimeException(e);
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <a href="<%= request.getContextPath() %>/hopital/rendez-vous" class="btn btn-purple">Retour</a>
        <h1>Prendre RDV</h1>
        <form action="<%= request.getContextPath()%>/hopital/rendez-vous" method="post">
            <div>
                <h3>Information du patient</h3>
                <p>Nom Complet: <%= person!= null && person.getGivenName() != null ? person.getGivenName(): "" %>
                    <%= person!= null && person.getFirstName() != null ? person.getFirstName(): "" %>
                    <%= person!= null && person.getLastName() != null ? person.getLastName(): "" %>
                </p>
                <p>Age: <%= person!= null && person.getDateOfBirth() != null ? person.getDateOfBirth(): "" %></p>
                <input type="hidden" name="personId" value="<%= person!= null && person.getId() != null ? person.getId(): "" %>">
            </div>
            <div>
                <div class="form-group">
                    <label>Date de rendez-vous</label>
                    <input type="date" name="date_rendezvous" value="<%= rendezVous != null && rendezVous.getDateRendezVous() != null ? rendezVous.getDateRendezVous() : "" %>">
                    <input type="hidden" name="rendezVousId" value="<%= rendezVous != null && rendezVous.getId() != null ? rendezVous.getId() : "" %>">
                </div>
                <div class="form-group">
                    <label>Selectionner un Medecin :</label>
                    <select name="doctorId">
                        <%
                            if (!docteurs.isEmpty()){
                                for (User docteur: docteurs){
                                    Person docteurPerson = personDao.findByUserId(docteur.getId());
                        %>
                            <option value="<%=docteur.getId()%>" >
                                <%= docteurPerson!= null && docteurPerson.getGivenName() != null ? docteurPerson.getGivenName() : "" %>
                                <%= docteurPerson!= null && docteurPerson.getFirstName() != null ? docteurPerson.getFirstName() : "" %>
                                <%= docteurPerson!= null && docteurPerson.getLastName() != null ? docteurPerson.getLastName() : "" %>
                            </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-blue">Soummettre la demande </button>
        </form>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>

