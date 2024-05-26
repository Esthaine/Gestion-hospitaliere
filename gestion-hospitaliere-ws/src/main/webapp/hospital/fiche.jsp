<%@ page import="com.gestion.hospitaliere.entity.Fiche" %>
<%@ page import="java.security.Permission" %>
<%@ page import="com.gestion.hospitaliere.entity.PremierSoin" %>
<%@ page import="com.gestion.hospitaliere.dao.PremierSoinDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PremierSoinDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gestion.hospitaliere.dao.AntecedentMedicalDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.AntecdentMedicalDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.AntecedentMedical" %>
<jsp:include page="components/topbar.jsp" />
<%

    Fiche fiche = (Fiche) request.getAttribute("fiche");
    List<PremierSoin> premierSoin = new ArrayList<>();
    List<AntecedentMedical> antecedentMedicalList = new ArrayList<>();
    PremierSoinDao premierSoinDao = null;
    AntecedentMedicalDao antecedentMedicalDao = null;

    try{
        premierSoinDao = new PremierSoinDaoImpl((Class<PremierSoin>) Class.forName("com.gestion.hospitaliere.entity.PremierSoin"));
        antecedentMedicalDao = new AntecdentMedicalDaoImpl((Class<AntecedentMedical>) Class.forName("com.gestion.hospitaliere.entity.AntecedentMedical"));
    } catch (Exception e){
        e.printStackTrace();
    }
    if(fiche != null){
        premierSoin = premierSoinDao.findByFiche(fiche.getId());
        antecedentMedicalList = antecedentMedicalDao.antecedentMedicalFindByFiche(fiche.getId());
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
                                    <%= fiche.getPatient().getFirstName() != null? fiche.getPatient().getFirstName(): ""%>
                                    <%= fiche.getPatient().getLastName() != null? fiche.getPatient().getLastName(): ""%>
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
                    if (antecedentMedicalList.isEmpty()) {
                %>
                <div class="fiche_patient_traitement">
                    <table class="table-fiche">
                        <tr>
                            <td>
                                <h2>Antecedant Medicale</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>Allergies</td>
                            <td></td>
                        </tr>

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
                    <div>
                        <h2>Examens et Resultats</h2>
                        <table class="table-fiche">
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                            <tr>
                                <td>Demande:</td>
                                <td>Resultat</td>
                            </tr>
                        </table>
                    </div>
                    <div class="medicaments">
                        <h2>Medicaments</h2>
                        <div class="medicament-items">
                            <p>Date</p>
                            <ul>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                            </ul>
                        </div>

                        <div class="medicament-items">
                            <p>Date</p>
                            <ul>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                            </ul>
                        </div>

                        <div class="medicament-items">
                            <p>Date</p>
                            <ul>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                                <li>Quinine</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/admin.js"></script>