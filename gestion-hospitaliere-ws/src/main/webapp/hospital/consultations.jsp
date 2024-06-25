<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.Rendezvous" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<jsp:include page="components/topbar.jsp" />
<%
    List<Rendezvous> rendezvousList = (List<Rendezvous>) request.getAttribute("rendezvousList");
    PersonDao personDao;
    Person person = null;
    int index = 0;
    try{
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
    }catch (ClassNotFoundException e){
       throw new RuntimeException(e);
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <div>
            <h2>Consultations</h2>
<%--            <%= rendezvousList %>--%>
        </div>
        <div>
            <div>
                <table class="table-mapper">
                    <thead>
                    <th>No</th>
                    <th>Nom Complet</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                    <%
                        for (Rendezvous r : rendezvousList) {
                            index += 1;
                            if (r.getPerson() != null && r.getPerson().getId() != null ){
                                person = personDao.findById(r.getPerson().getId());
                            }

                    %>
<%--                    <%= personDao.findById(r.getPerson().getId()).getGivenName() %><br>--%>
<%--                    <%= personDao.findById(r.getPerson().getId()).getFirstName() %><br>--%>
<%--                    <%= personDao.findById(r.getPerson().getId()).getLastName() %><br>--%>
                        <tr>
                            <td>
                                <%=index%>
                            </td>
                            <td>
                                <p>
                                    <%= person != null && person.getGivenName() != null ? person.getGivenName(): "" %>
                                    <%= person != null && person.getFirstName() != null ? person.getFirstName(): "" %>
                                    <%= person != null && person.getLastName() != null ? person.getLastName(): "" %>
                                </p>
                            </td>
                            <td>
                                <a href="<%=request.getContextPath()%>/hopital/patient/consultation/operation?patientId=<%= person != null && person.getId() != null?person.getId() :""%>&rendezVousId=<%= r.getId() != null? r.getId(): ""%>" class="btn btn-green">Consulter</a>
                                <a href="<%=request.getContextPath()%>/hopital/patient/fiche?patientId=<%=person != null && person.getId() != null?person.getId() :""%>" class="btn btn-blue">Voir fiche</a>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
