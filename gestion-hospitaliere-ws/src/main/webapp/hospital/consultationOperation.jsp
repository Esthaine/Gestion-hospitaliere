<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page import="com.gestion.hospitaliere.dao.PremierSoinDao" %>
<%@ page import="com.gestion.hospitaliere.dao.AntecedentMedicalDao" %>
<%@ page import="com.gestion.hospitaliere.dao.ResultatsExamensDao" %>
<%@ page import="com.gestion.hospitaliere.dao.MedicamentDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PremierSoinDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.AntecdentMedicalDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.ResultatExamenDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.MedicamentDaoImpl" %>
<jsp:include page="components/topbar.jsp" />

<div class="main">
  <%
    List<Medicament> medicaments = (List<Medicament>) request.getAttribute("medicaments");
    Fiche fiche = (Fiche) request.getAttribute("fiche");
    List<PremierSoin> premierSoin = new ArrayList<>();
    List<AntecedentMedical> antecedentMedicalList = new ArrayList<>();
    List<ResultatsExamens> resultatsExamens = new ArrayList<>();
    PremierSoinDao premierSoinDao = null;
    AntecedentMedicalDao antecedentMedicalDao = null;
    ResultatsExamensDao resultatsExamensDao = null;
    MedicamentDao medicamentDao = null;

    try{
      premierSoinDao = new PremierSoinDaoImpl((Class<PremierSoin>) Class.forName("com.gestion.hospitaliere.entity.PremierSoin"));
      antecedentMedicalDao = new AntecdentMedicalDaoImpl((Class<AntecedentMedical>) Class.forName("com.gestion.hospitaliere.entity.AntecedentMedical"));
      resultatsExamensDao = new ResultatExamenDaoImpl((Class<ResultatsExamens>) Class.forName("com.gestion.hospitaliere.entity.ResultatsExamens"));
      medicamentDao = new MedicamentDaoImpl((Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament"));
    } catch (Exception e){
      e.printStackTrace();
    }
    if(fiche != null){
      premierSoin = premierSoinDao.findByFiche(fiche.getId());
      antecedentMedicalList = antecedentMedicalDao.antecedentMedicalFindByFiche(fiche.getId());
      resultatsExamens = resultatsExamensDao.findFicheById(fiche.getId());
//      medicamentList = medicamentDao.listMedicamentPerFiche(fiche.getId());
    }
  %>
  <jsp:include page="components/sidebar.jsp" />
  <div class="content">
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
                  <input type="text" name="appreciation_questions">
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
              <input type="checkbox" name="medicaments"><label for="medicmanents"><%= medicament.getNom()%></label>
            </div>
            <%}%>
          </div>
        </div>
<%--        <a class="action-button action-add" id="add-medicament">--%>
<%--          <i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--        </a>--%>
      </div>
      <button type="submit" class="btn-submit">Soumettre le details</button>
    </form>
  </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
