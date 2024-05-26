<%@ page import="com.gestion.hospitaliere.entity.Rendezvous" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.entity.Fiche" %>
<%@ page import="com.gestion.hospitaliere.entity.RendezVousStatus" %>
<jsp:include page="components/topbar.jsp" />
<%
    Rendezvous rendezvous = (Rendezvous) request.getAttribute("rendezvous");
    Person patient = (Person) request.getAttribute("patient");
    Fiche fiche = (Fiche) request.getAttribute("fiche");
%>
<div class="main">
  <jsp:include page="components/sidebar.jsp" />
  <div class="content">
    <h1>Premier Soin</h1>
      <div>
          <h3>Information du patient</h3>
          <p>Nom Complet:
              <%= patient != null && patient.getGivenName() != null ? patient.getGivenName() : "" %>
              <%= patient != null && patient.getFirstName() != null ? patient.getFirstName() : "" %>
              <%= patient != null && patient.getLastName() != null ? patient.getLastName() : "" %>
          </p>
          <p>
              Age: <%=  patient!= null&& patient.getDateOfBirth() != null ? patient.getDateOfBirth():""%>
          </p>
          <% if (rendezvous!= null && rendezvous.getStatus().equals(RendezVousStatus.EN_COURS)) {%>
            <span>En cours</span>
          <%}%>

          <% if (rendezvous!= null && rendezvous.getStatus().equals(RendezVousStatus.TRAITER)) {%>
            <span>Terminer</span>
          <%}%>

          <% if (rendezvous!= null && rendezvous.getStatus().equals(RendezVousStatus.ANNULER)) {%>
            <span>Annuler</span>
          <%}%>
          <% if (rendezvous!= null && rendezvous.getStatus().equals(RendezVousStatus.REPROGRAMMER)) {%>
            <span>Reprogrammer</span>
          <%}%>
          <% if (rendezvous!= null && rendezvous.getStatus().equals(RendezVousStatus.NOUVEAU)) {%>
            <span>En attente</span>
          <%}%>
      </div>
      <% if (rendezvous != null && (rendezvous.getStatus().equals(RendezVousStatus.EN_COURS) || rendezvous.getStatus().equals(RendezVousStatus.TRAITER ) || rendezvous.getStatus().equals(RendezVousStatus.ANNULER))) {%>
      <%}else{%>
        <form action="<%= request.getContextPath()%>/hopital/patient/premierSoin" method="post">
            <% if (fiche != null) {%>
            <div>
                <h3>Prelevement</h3>
                <table>
                    <tr>
                        <td><label>Temperature *:</label></td>
                        <td><input type="number" name="temperature"></td>
                    </tr>
                    <tr>
                        <td><label>Kilo *:</label></td>
                        <td><input type="number" name="kilo"></td>
                    </tr>
                    <tr>
                        <td><label>Tension *:</label></td>
                        <td><input type="number" name="tension"></td>
                    </tr>
                    <tr>
                        <td><label>Taille *:</label></td>
                        <td><input type="number" name="taille"></td>
                    </tr>
                    <tr>
                        <td><label>Respiration *:</label></td>
                        <td><input type="number" name="respiration"></td>
                    </tr>
                    <input type="hidden" name="rendezVousId" value="<%=rendezvous.getId()%>"/>
                    <input type="hidden" name="patientId" value="<%=patient.getId()%>"/>
                </table>
            </div>
            <button class="btn btn-blue" type="submit">Prelevez et envoyer pour consultation</button>
            <%} else {%>
            <div>
                <h3>Le patient n'a pas une fiche medicale enregistrer dans le systeme de Maman Yemo Hopital</h3>
                <p>Veuillez creer la fiche pour continue avec la consultation.</p>
            </div>
            <input type="hidden" name="rendezVousId" value="<%=rendezvous!= null && rendezvous.getId()!= null ? rendezvous.getId(): ""%>">
            <input type="hidden" name="patientId" value="<%=rendezvous!= null && rendezvous.getPerson()!= null && rendezvous.getPerson().getId()!= null?rendezvous.getPerson().getId():""%>">
            <input type="hidden" name="action" value="createFile">
            <button type="submit" class="btn btn-green">Creer une fiche</button>
            <%}%>
        </form>
      <%}%>
  </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>