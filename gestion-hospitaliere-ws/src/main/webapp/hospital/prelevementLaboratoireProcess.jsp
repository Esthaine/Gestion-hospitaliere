<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page import="com.gestion.hospitaliere.dao.*" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.*" %>
<jsp:include page="components/topbar.jsp" />
<%
    FicheDao ficheDao = null;
    PersonDao personDao = null;
    RendezVousDao rendezVousDao = null;
    ResultatsExamensDao resultatsExamensDao = null;
    ExamenDao examenDao = null;
    QuestionDao questionDao = null;
    Person person = null;
    Fiche fiche = null;
    Rendezvous rendezvous = null;


    String rendezVousId = request.getParameter("rendezVousId");
    String patientId = request.getParameter("patientId");

    try{
        ficheDao = new FicheDaoImpl((Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche"));
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        rendezVousDao = new RendezVousDaoImpl((Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous"));
        resultatsExamensDao = new ResultatExamenDaoImpl((Class<ResultatsExamens>) Class.forName("com.gestion.hospitaliere.entity.ResultatsExamens"));
        questionDao = new QuestionDaoImpl((Class<Question>) Class.forName("com.gestion.hospitaliere.entity.Question"));
        examenDao = new ExamenDaoImpl((Class<Examen>) Class.forName("com.gestion.hospitaliere.entity.Examen"));
    }catch (Exception e){
    }

    if (ficheDao != null){
        fiche = ficheDao.findByPatient(Long.parseLong(patientId));
        person = personDao.findById(Long.parseLong(patientId));
        rendezvous = rendezVousDao.findById(Long.parseLong(rendezVousId));
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <form action="<%=request.getContextPath()%>/hopital/labo/prelevement/process" method="post">
            <div class="demand__examens">
                <h2>Examens Labo</h2>
                <div class="analyse">
                    <div class="questions">


                        <%
                            if (resultatsExamensDao.findFicheById(fiche.getId()) != null){
                                for (ResultatsExamens resultatsExamens: resultatsExamensDao.findFicheById(fiche.getId())) {
                        %>
                            <div class="question-item">
                                <div class="question">
                                    <%
                                        if ( questionDao.findByResultExamens(resultatsExamens.getId()) != null){
                                            for (Question question: questionDao.findByResultExamens(resultatsExamens.getId())) {
//                                                if (examenDao.findByResultatExemens(resultatsExamens.getId()) == null){
                                    %>
                                        <div class="questions-items">
                                            <h3>Demande:</h3>
                                            <label>Examen:</label>
                                            <input type="text" name="questions" value="<%= question.getTitre() != null ? question.getTitre(): ""%>" disabled>
                                            <label>Appreciation</label>
                                            <input type="text" name="appreciation" value="<%= question.getAppreciation() != null ? question.getAppreciation(): ""%>" disabled>
                                        </div>
                                        <div class="reponse-items">
                                            <h3>Reponse:</h3>
                                            <label>Resultat:</label>
                                            <input type="text" name="reponses">
                                            <label>Appreciation</label>
                                            <input type="text" name="appreciation_reponses">
                                        </div>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                            </div>
                        <%
                                }
                            }
                        %>
                    </div>
                    <input type="hidden" name="result" value="<%=resultatsExamensDao.findFicheById(fiche.getId()).get(0).getId()%>">
                    <input type="hidden" name="rendezVousId" value="<%= rendezvous.getId()%>">
                    <button type="submit" class="btn-submit">Sauvegarder les prelevements</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
