<%@ page import="java.security.Permission" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page import="com.gestion.hospitaliere.dao.*" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.*" %>
<jsp:include page="components/topbar.jsp" />
<%

    Fiche fiche = (Fiche) request.getAttribute("fiche");
    List<PremierSoin> premierSoin = new ArrayList<>();
    List<AntecedentMedical> antecedentMedicalList = new ArrayList<>();
    List<ResultatsExamens> resultatsExamens = new ArrayList<>();
    List<Medicament> medicamentList = new ArrayList<>();
    PremierSoinDao premierSoinDao = null;
    AntecedentMedicalDao antecedentMedicalDao = null;
    ResultatsExamensDao resultatsExamensDao = null;
    MedicamentDao medicamentDao = null;
    ExamenDao examenDao = null;
    QuestionDao questionDao = null;

    try{
        premierSoinDao = new PremierSoinDaoImpl((Class<PremierSoin>) Class.forName("com.gestion.hospitaliere.entity.PremierSoin"));
        antecedentMedicalDao = new AntecdentMedicalDaoImpl((Class<AntecedentMedical>) Class.forName("com.gestion.hospitaliere.entity.AntecedentMedical"));
        resultatsExamensDao = new ResultatExamenDaoImpl((Class<ResultatsExamens>) Class.forName("com.gestion.hospitaliere.entity.ResultatsExamens"));
        medicamentDao = new MedicamentDaoImpl((Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament"));
        examenDao = new ExamenDaoImpl((Class<Examen>) Class.forName("com.gestion.hospitaliere.entity.Examen"));
        questionDao = new QuestionDaoImpl((Class<Question>) Class.forName("com.gestion.hospitaliere.entity.Question"));

    } catch (Exception e){
        e.printStackTrace();
    }
    if(fiche != null){
        premierSoin = premierSoinDao.findByFiche(fiche.getId());
        antecedentMedicalList = antecedentMedicalDao.antecedentMedicalFindByFiche(fiche.getId());
        resultatsExamens = resultatsExamensDao.findFicheById(fiche.getId());
        medicamentList = medicamentDao.listMedicamentPerFiche(fiche.getId());
    }

%>
<div class="main">
    <jsp:include page="components/sidebar.jsp"/>
    <div class="content">
        <div class="fiche">
            <div class="fiche_head">
                <h1>FICHE MEDICALE</h1>
                <p>Cette fiche est une aide a la decision.</p>
            </div>

            <div class="fiche_content">
                <div id="fiche_patient_info">
                    <table class="table-fiche">
                        <tr>
                            <td>
                                <h4>No Fiche</h4>
                            </td>
                            <td>
                                <p><%=fiche!= null && fiche.getFicheNumber()!= null? fiche.getFicheNumber(): ""%></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2>NOM</h2>
                            </td>
                            <td>
                                <%
                                    if (fiche!= null){
                                        if (fiche.getPatient()!= null){
                                %>
                                <h3>
                                    <%= fiche.getPatient().getFirstName() != null ? fiche.getPatient().getFirstName(): ""%>
                                    <%= fiche.getPatient().getLastName() != null ? fiche.getPatient().getLastName(): ""%>
                                </h3>
                                <%
                                        }
                                    }
                                %>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2>
                                    Prenom
                                </h2>
                            </td>
                            <td>
                                <h2>
                                    <%= fiche!= null && fiche.getPatient()!= null && fiche.getPatient().getGivenName() != null? fiche.getPatient().getGivenName(): ""%>
                                </h2>
                            </td>
                        </tr>
                        <tr>
                            <td>Date et lieu de naissance</td>
                            <td>
                                <p>
                                    <%= fiche != null && fiche.getPatient() != null && fiche.getPatient().getDateOfBirth() != null? fiche.getPatient().getDateOfBirth(): ""%>
                                </p>
                            </td>
                        </tr>
                    </table>
                </div>

                <%
                    if (!antecedentMedicalList.isEmpty()) {
                %>
                <div class="fiche_patient_traitement">
                    <table class="table-fiche">
                        <tr>
                            <td>
                                <h2>Antecedant Medicale</h2>
                            </td>
                        </tr>
                        <%
                            for (AntecedentMedical antecedentMedical: antecedentMedicalList) {
                        %>
                        <tr>
                            <td>
                                <%= antecedentMedical.getType()%>
                            </td>
                            <td>
                                <p><%=antecedentMedical.getDateDebut()%></p>
                                <p><%=antecedentMedical.getDescription()%></p>
                            </td>
                        </tr>

                        <%}%>
                    </table>
                </div>
                <%}%>
                <%
                    if (!premierSoin.isEmpty()){
                %>
                <div class="prelevement">
                    <h2>Premiers soins.</h2>
                    <table class="table-fiche">
                        <%
                            for (PremierSoin premier : premierSoin){
                        %>
                        <tr>
                            <td>Date: <%=premier.getCreatedAt()%> </td>
                            <td>
                                <p>Taille: <%= premier.getTaille() != null? premier.getTaille() : ""%></p>
                                <p>Temperature: <%= premier.getTemperature() != null? premier.getTemperature() : ""%></p>
                                <p>Poid: <%= premier.getKilo() != null? premier.getKilo() : ""%></p>
                                <p>Tension: <%= premier.getTension() != null? premier.getTension() : ""%></p>
                                <p>Respiration: <%= premier.getRespiation() != null? premier.getRespiation() : ""%></p>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>
                <%}%>
                <div class="examens_resulats">
                    <% if (!resultatsExamens.isEmpty()) {
                        for (ResultatsExamens resultatsExamen: resultatsExamens) {
                    %>
                    <div>
                        <h2>Examens et Resultats</h2>
                        <table class="table-fiche">

                            <tr>
                                <%
                                    if (questionDao.findByResultExamens(resultatsExamen.getId()) != null) {
                                        for (Question question: questionDao.findByResultExamens(resultatsExamen.getId())) { %>
                                    <td>
                                        <%= question.getTitre()%>
                                        <%= question.getAppreciation()%>
                                    </td>
                                <%
                                        }
                                    }
                                %>
                                <%
                                    if (examenDao.findByResultatExemens(resultatsExamen.getId()) != null){
                                    for (Examen examen : examenDao.findByResultatExemens(resultatsExamen.getId())) {%>
                                    <td>
                                        <%= examen.getResultat()!= null ? examen.getResultat(): ""%>
                                        <%= examen.getType() != null ? examen.getType() : ""%>
                                        <%= examen.getCommentaires() != null ? examen.getCommentaires(): "" %>
                                    </td>
                                <%
                                     }
                                    }
                                %>
                            </tr>
                        </table>
                    </div>
                    <%
                            }
                        }
                    %>

                    <% if (!medicamentList.isEmpty()) {
                        for (Medicament medicament: medicamentList){   %>
                    <div class="medicaments">
                        <h2>Medicaments</h2>
                        <div class="medicament-items">
                            <p>Date de prescriptions: <%= medicamentList.get(0).getDatePeremption()%></p>
                            <ul>
                                <li><%= medicament.getNom()%></li>
                            </ul>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/admin.js"></script>