<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.utils.AppConst" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<jsp:include page="components/topbar.jsp" />
<%
    UserDao userRepository;
    PersonDao personRepository;
    Person person = null;
    try {
        userRepository = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        personRepository = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    List<User> patients = userRepository.listOfUserPerRole(AppConst.PATIENT);
    String action = request.getParameter("action");
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <div class="content-header">
            <a href="<%=request.getContextPath()%>/hopital/patient/creerEtModifier?action=add" class="btn btn-blue">ajouter nouveau patient</a>
            <button class="btn btn-green">Exporter liste des patients</button>
        </div>

        <div>
            <table class="table-mapper">
                <thead>
                <th>No</th>
                <th>Prenom</th>
                <th>Nom</th>
                <th>Poste-Nom</th>
                <th>Action</th>
                </thead>
                <tbody>
                    <%
                            if (patients!=null && !patients.isEmpty()) {
                                for (User patient : patients) {
                                    if (personRepository != null){
                                        try{
                                            if (personRepository.findByUserId(patient.getId()) != null){
                                                person = personRepository.findByUserId(patient.getId());
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                            %>
                                <tr>
                                    <td><%=patient.getId()%></td>
                                    <td>
                                        <%= person != null && person.getGivenName() != null ? person.getGivenName(): "" %>
                                    </td>
                                    <td>
                                        <%= person != null && person.getFirstName() != null ? person.getFirstName(): "" %>
                                    </td>
                                    <td>
                                        <%= person != null && person.getLastName() != null ? person.getLastName(): "" %>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/hopital/patient/creerEtModifier?userId=<%=patient.getId()%>&action=edit" class="btn btn-green">Editer</a>
                                        <a href="<%=request.getContextPath()%>/hopital/patient/rendezVous?userId=<%=patient.getId()%>" class="btn btn-blue">Prendre un Rendez-vous</a>
                                    </td>
                                </tr>
                            <%
                                }
                        }
                    %>
                </tbody>
            </table>

        </div>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>