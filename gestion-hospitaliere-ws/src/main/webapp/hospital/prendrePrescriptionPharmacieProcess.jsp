<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page import="com.gestion.hospitaliere.dao.*" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.*" %>
<jsp:include page="components/topbar.jsp" />
<%
    FicheDao ficheDao;
    PersonDao personDao;
    Person person = null;
    MedicamentDao medicamentDao;
    String rendezVousId = request.getParameter("rendezVousId");
    String patientId = request.getParameter("patientId");

    try{
        ficheDao = new FicheDaoImpl((Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche"));
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        medicamentDao = new MedicamentDaoImpl((Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament"));
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    Fiche fiche = ficheDao.findByPatient(Long.parseLong(patientId));
    if (fiche != null) {
        person = personDao.findById(Long.parseLong(patientId));
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <div>
            <h2>Remise des medicaments au patient</h2>
        </div>
        <div class="patient-info">
            <table>
                <tr>
                    <td>Nom Complet:</td>
                    <td>
                        <h4>
                            <%= person != null && person.getGivenName() != null? person.getGivenName(): ""%>
                            <%= person != null && person.getFirstName() != null? person.getFirstName(): ""%>
                            <%= person != null && person.getLastName() != null? person.getLastName(): ""%>
                        </h4>
                    </td>
                </tr>
                <tr>
                    <td>Date de naissance: </td>
                    <td><h4>26/09/1996</h4></td>
                </tr>
                <tr>
                    <td>Age: </td>
                    <td><h4>28</h4></td>
                </tr>
            </table>
        </div>
        <form action="<%= request.getContextPath()%>/hopital/pharmacie/prescription/process" method="post">
            <div class="medicaments">
                <div class="medicament-item-container">
                    <%
                        if (medicamentDao!= null && medicamentDao.listMedicamentPerFiche(fiche.getId()) != null){
                        for (Medicament medicament : medicamentDao.listMedicamentPerFiche(fiche.getId())) {
                    %>
                            <div class="medicament-item">
                                <input type="checkbox" name="medicament" value="<%=medicament.getId()%>"><label for="medicaments"><%= medicament.getNom()%></label>
                            </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <input type="hidden" value="<%= rendezVousId != null ? rendezVousId: ""%>" name="rendezVousId">
            <input type="hidden" value="<%= patientId != null ? patientId : "" %>" name="patientId">
            <input type="hidden" value="<%= fiche.getId() != null ? fiche.getId() : ""%>" name="ficheId">
            <button type="submit" class="btn-submit">Confirmer la procuration des medicaments</button>
        </form>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
