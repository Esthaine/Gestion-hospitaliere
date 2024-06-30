<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gestion.hospitaliere.utils.AppConst" %>
<%@ page import="com.gestion.hospitaliere.dao.*" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.ZoneId" %>
<jsp:include page="components/topbar.jsp" />

<div class="main">
  <%
    List<Medicament> medicaments = (List<Medicament>) request.getAttribute("medicaments");
    String error = request.getParameter("error");
    Long rendezVousId = Long.valueOf(request.getParameter("rendezVousId"));
    Fiche fiche = (Fiche) request.getAttribute("fiche");
    List<PremierSoin> premierSoin = new ArrayList<>();
    List<AntecedentMedical> antecedentMedicalList = new ArrayList<>();
    List<ResultatsExamens> resultatsExamens = new ArrayList<>();
    PremierSoinDao premierSoinDao = null;
    AntecedentMedicalDao antecedentMedicalDao = null;
    ResultatsExamensDao resultatsExamensDao = null;
    MedicamentDao medicamentDao = null;
    ExamenDao examenDao = null;
    QuestionDao questionDao = null;
    PersonDao personDao = null;
    Person person = null;
    RendezVousDao rendezVousDao = null;

    try{
      premierSoinDao = new PremierSoinDaoImpl((Class<PremierSoin>) Class.forName("com.gestion.hospitaliere.entity.PremierSoin"));
      antecedentMedicalDao = new AntecdentMedicalDaoImpl((Class<AntecedentMedical>) Class.forName("com.gestion.hospitaliere.entity.AntecedentMedical"));
      resultatsExamensDao = new ResultatExamenDaoImpl((Class<ResultatsExamens>) Class.forName("com.gestion.hospitaliere.entity.ResultatsExamens"));
      medicamentDao = new MedicamentDaoImpl((Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament"));
      personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
      questionDao = new QuestionDaoImpl((Class<Question>) Class.forName("com.gestion.hospitaliere.entity.Question"));
      examenDao = new ExamenDaoImpl((Class<Examen>) Class.forName("com.gestion.hospitaliere.entity.Examen"));
      rendezVousDao = new RendezVousDaoImpl((Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous"));
    } catch (Exception e){
      e.printStackTrace();
    }
    if(fiche != null){
      premierSoin = premierSoinDao.findByFiche(fiche.getId());
      antecedentMedicalList = antecedentMedicalDao.antecedentMedicalFindByFiche(fiche.getId());
      resultatsExamens = resultatsExamensDao.findFicheById(fiche.getId());
      //medicamentList = medicamentDao.listMedicamentPerFiche(fiche.getId());
      person = personDao.findById(fiche.getPatient().getId());
    }
  %>
  <jsp:include page="components/sidebar.jsp" />
  <div class="content">
    <div class="info-patient">
      <% if ( error!= null) {%>
        <span class="error">
          <%= error%>
        </span>
      <%}%>

      <table>
        <tr>
          <td>Nom Complet:</td>
          <td>
            <h4>
              <%= person!= null && person.getGivenName()!= null ? person.getGivenName(): ""%>
              <%= person!= null && person.getFirstName()!= null ? person.getFirstName() : ""%>
              <%= person!= null && person.getLastName()!= null ? person.getLastName(): ""%>
            </h4>
          </td>
        </tr>
        <tr>
          <td>Date de naissance: </td>
          <td>
            <h4>
              <%= person!= null && person.getDateOfBirth()!= null? person.getDateOfBirth(): ""%>
            </h4>
          </td>
        </tr>
        <tr>
          <%
            //comparting
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate patientDob = null;
            int currentAge = 0;
            if (person!= null && person.getDateOfBirth() != null){
              patientDob = person.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
              int currentYear  = localDate.getYear();
              int patientYear =  patientDob.getYear();
              currentAge =  currentYear - patientYear;
            }

          %>
          <td>Age: </td>
          <td><h4><%=currentAge > 0 ? currentAge: ""%></h4></td>
        </tr>
      </table>
    </div>
    <form class="form-fiche" action="<%=request.getContextPath()%>/hopital/patient/consultation/operation" method="post">
      <div class="antecedants">
        <h2>Liste antecedants medicales</h2>
        <% if (!antecedentMedicalList.isEmpty()) {%>
        <div class="antecedants-items-list">
          <% for (AntecedentMedical antecedentMedical: antecedentMedicalList){%>
          <div class="antecedants-item">
            <h4><%= antecedentMedical.getType()%></h4>
            <p><%= antecedentMedical.getDescription()%></p>
          </div>
          <%}%>
        </div>
        <%}%>

        <% if (antecedentMedicalList.isEmpty()) {%>
        <div class="antecedants-items-form">
          <div class="antecedants-item-form">
            <div class="form-table-complet-h">
              <div class="form-table">
                <label>Date debut:</label>
                <input type="date" name="date_debuts">
              </div>

              <div class="form-table">
                <label>Date fin:</label>
                <input type="date" name="date_fins">
              </div>
            </div>
            <div class="form-table-complet">
              <label>Type:</label>
              <input type="text" name="type">
            </div>
            <div class="form-table-complet">
              <label>Description:</label>
              <textarea name="descriptions"></textarea>
            </div>
          </div>
          <a class="action-button action-add" id="add-antecedant">
            <i class="fa fa-plus" aria-hidden="true"></i>
          </a>
        </div>
        <%}%>
      </div>
      <div class="demand__examens">
        <h2>Examens Labo</h2>
        <div class="analyse">
          <div class="questions">
            <div class="question-item">
              <div class="question">
                <div class="questions-items">
                  <h3>Demande:</h3>
                  <label>Examen:</label>
                  <input type="text" name="questions">
                  <label>Appreciation</label>
                  <input type="text" name="appreciation">
                </div>
                <div class="reponse-items">
                  <h3>Reponse:</h3>
                  <label>Resultat:</label>
                  <input type="text" name="reponses" disabled>
                  <label>Appreciation</label>
                  <input type="text" name="appreciation_reponses" disabled>
                </div>
              </div>
            </div>
            <a class="action-button action-add" id="add-examen">
              <i class="fa fa-plus" aria-hidden="true"></i>
            </a>
          </div>

        </div>
      </div>
      <div class="subscripiton_medical">
        <h2>Subscription Medical</h2>
        <div class="medicaments">
          <div class="medicament-item-container">
            <%
              for (Medicament medicament : medicaments) {
            %>
            <div class="medicament-item">
              <input type="checkbox" name="medicaments" value="<%=medicament.getId()%>"><label for="medicmanents"><%= medicament.getNom()%></label>
            </div>
            <%}%>
          </div>
        </div>
      </div>
      <div class="envoyer-vers">

        <h2>Autres services</h2>
        <div class="pharmacie">
          <input type="radio" name="envoyer_vers" value="<%=AppConst.PHARMACIE%>">
          <label>Envoyer a la pharmacie</label>
        </div>
        <div class="laboratoire">
          <input type="radio" name="envoyer_vers" value="<%=AppConst.LABORATOIRE%>">
          <label>Envoyer au laboratoire</label>
        </div>
      </div>
      <input type="hidden" name="patientId" value="<%= fiche.getPatient() != null && fiche.getPatient().getId() != null ? fiche.getPatient().getId() : "" %>">
      <input type="hidden" name="rendezVousId" value="<%= rendezVousId%>">
      <button type="submit" class="btn-submit">Soumettre le details</button>
    </form>
  </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
