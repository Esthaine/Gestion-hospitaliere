<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.DepartementDao" %>
<%@ page import="com.gestion.hospitaliere.entity.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<jsp:include page="components/topbar.jsp" />
<div class="main">
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        String action = (String) request.getParameter("action");
        PersonDao personDao = (PersonDao) request.getAttribute("persons");
        DepartementDao departementDao = (DepartementDao) request.getAttribute("departments");
        UserDao userDao = (UserDao) request.getAttribute("userDao");
        List<Role> roles = (List<Role>) request.getAttribute("roles");
        List<Pays> pays = (List<Pays>) request.getAttribute("pays");
        List<Ville> villes = (List<Ville>) request.getAttribute("villes");
        List<Departement> departementList = (List<Departement>) request.getAttribute("departementList");
        List<Departement> departements = new ArrayList<>(new HashSet<>(departementList));
        Person editPerson = null;
        User editUser = null;
        if (request.getParameter("userId") != null){
            long userId = Long.parseLong(request.getParameter("userId"));
            editPerson = personDao.findByUserId(userId);
            editUser = userDao.findById(userId);
        }
    %>
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <% if (action == null) {%>
        <div class="header-content">
            <a href="<%= request.getContextPath()%>/hopital/docteurs?action=add" class="btn btn-blue">Ajouter un Membre</a>
            <button class="btn btn-green">Exporter liste des membres du corp medicale</button>
        </div>

        <table class="table-mapper">
            <thead>
                <th>No</th>
                <th>Prenom</th>
                <th>Nom</th>
                <th>Poste-Nom</th>
                <th>Fonction</th>
                <th>Department</th>
                <th>Action</th>
            </thead>
            <tbody>
            <% } %>
                <%
                    int index = 0;
                    if (action == null) {

                    if (users !=null && !users.isEmpty()){
                        for (User user : users) {
                            index++;
                            Person person = personDao.findByUserId(user.getId());

                %>
                        <tr>
                            <td><%= index %></td>
                            <td>
                                <%= person!= null && person.getGivenName()!= null ? person.getGivenName() : ""%>
                            </td>
                            <td>
                                <%= person!= null && person.getFirstName()!= null ? person.getFirstName() : ""%>
                            </td>
                            <td>
                                <%= person!= null && person.getLastName()!= null ? person.getLastName() : ""%>
                            </td>
                            <td><%=user.getRole().getName()%></td>
                            <td>
                                <%
                                    if (user.getDepartement()!= null) {
                                        Departement departement = departementDao.findById(user.getDepartement().getId());
                                        if (departement != null) {
                                        %>
                                        <%= departement.getNomDepartement()%>
                                    <%}
                                    }
                                %>
                            </td>
                            <td>
                                <a href="" class="btn btn-green">Activate</a>
                                <a href="<%=request.getContextPath()%>/hopital/docteurs?userId=<%=user.getId()%>&action=edit" class="btn btn-purple">Modify</a>
                            </td>
                        </tr>
                <%
                            }
                        }
                    }
                %>
            <% if (action == null) {%>
            </tbody>
            <%}%>


            <%
                if (action != null) {
                    if (action.equals("edit") || action.equals("add")){
            %>
            <a href="<%=request.getContextPath()%>/hopital/docteurs" class="btn btn-blue">Retour</a>
            <div class="form-member">
                <form action="<%= request.getContextPath()%>/hopital/patient/consultation/operation" method="post">
                    <div>
                        <h3>Information De la personne(Nouveau Membre)</h3>
                        <div class="form-group-item">
                            <div class="form-group">
                                <label>prenom:</label>
                                <input type="text" name="prenom" value="<%= action.equals("edit") && editPerson != null && editPerson.getGivenName() != null?editPerson.getGivenName():"" %>">
                            </div>
                            <div class="form-group">
                                <label>Nom:</label>
                                <input type="text" name="nom" value="<%= action.equals("edit") && editPerson != null && editPerson.getFirstName() != null?editPerson.getFirstName():"" %>">
                            </div>
                        </div>
                        <div class="form-group-item">
                            <div class="form-group">
                                <label>Poste nom:</label>
                                <input type="text" name="post-nom" value="<%= action.equals("edit") && editPerson != null && editPerson.getLastName() != null?editPerson.getLastName():"" %>">
                            </div>
                            <div class="form-group">
                                <label>Date de naissance:</label>
                                <input type="date" name="dob" value="<%= action.equals("edit") && editPerson != null && editPerson.getDateOfBirth() != null?editPerson.getDateOfBirth():"" %>">
                            </div>
                        </div>
                        <div class="form-group-item">
                            <div class="form-group">
                                <label>Avenue:</label>
                                <input type="text" name="street" value="<%= action.equals("edit") && editPerson != null && editPerson.getStreetName() != null?editPerson.getStreetName():"" %>">
                            </div>
                            <div class="form-group">
                                <label>Adresse No:</label>
                                <input type="text" name="street-number" value="<%= action.equals("edit") && editPerson != null && editPerson.getHouseNumber() != null?editPerson.getHouseNumber():"" %>">
                            </div>
                        </div>
                        <div class="form-group-item">
                            <div class="form-group">
                                <label>Pays:</label>
                                <% if (pays!= null && !pays.isEmpty()) {%>
                                <select name="pays">
                                    <%
                                        for (Pays pay : pays) {
                                    %>
                                    <option value="<%=pay.getId()%>"><%= pay.getNom()%></option>
                                    <%}%>
                                </select>
                                <%}%>
                            </div>
                            <div class="form-group">
                                <label>Ville:</label>
                                <%
                                    if (villes != null && !villes.isEmpty()) {
                                %>
                                <select name="villes">
                                    <%
                                        for (Ville ville: villes){
                                    %>
                                        <option value="<%=ville.getId()%>"><%= ville.getNom()%></option>
                                    <%}%>
                                </select>
                                <%}%>
                            </div>
                        </div>

                        <div class="form-group-item">
                            <div class="form-group">
                                <label>Numero Telephone:</label>
                                <div class="phone">
                                    <span>+243</span>
                                    <input type="text" name="phone" value="<%= action.equals("edit") && editPerson != null && editPerson.getPhoneNumber() != null?editPerson.getPhoneNumber():"" %>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Sexe:</label>
                                <select name="sexe">
                                    <option value="MASCULIN">HOMME</option>
                                    <option value="FEMME">FEMME</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div>
                        <h3>Information Compte Utilisateur</h3>
                        <div class="form-group-item">
                            <div class="form-group">
                                <label>Nom Utilisateur:</label>
                                <% if (action.equals("edit") && editUser != null) { %>
                                    <input type="text" name="username" value="<%= editUser.getUsername() != null ? editUser.getUsername(): "" %>">
                                <% } %>
                                <% if (action.equals("add")){%>
                                    <input type="text" name="username">
                                <%}%>
                            </div>
                            <div class="form-group">
                                <label>Email:</label>
                                <% if (action.equals("edit") && editUser != null){%>
                                    <input type="text" name="email" value="<%= editUser.getEmail() != null ? editUser.getEmail() : "" %>">
                                <%}%>
                                <% if (action.equals("add")){%>
                                    <input type="text" name="email">
                                <%}%>
                            </div>
                        </div>

                        <div class="form-group-item">
                            <div class="form-group">
                                <label>Role:</label>
                                <%
                                    if (action.equals("edit")){
                                %>
                                    <%= editUser!= null && editUser.getRole()!= null && editUser.getRole().getName()!= null ? editUser.getRole().getName(): ""%>
                                <%}%>
                                <% if (action.equals("add")){
                                    if ( roles != null && !roles.isEmpty()){
                                %>
                                <select name="role">
                                    <%
                                        for (Role role : roles) {
                                    %>
                                        <option value="<%=role.getName()%>"><%= role.getName().toLowerCase()%></option>
                                    <%}%>
                                </select>
                                <%
                                    }
                                    }
                                %>
                            </div>
                            <div class="form-group">
                                <label>Departement :</label>
                                <%
                                    if (departements != null && !departements.isEmpty()){
                                %>
                                <select name="departement">
                                    <%
                                        for (Departement departement : departements) {
                                    %>
                                        <option value="<%=departement.getId()%>"><%=departement.getNomDepartement()%></option>
                                    <%}%>
                                </select>
                                <%
                                    }
                                %>
                            </div>
                        </div>

                        <%
                            if (action.equals("add")){
                        %>
                            <div class="form-group-item">
                                <div class="form-group">
                                    <label>Mot de passe *:</label>
                                    <input type="password" name="password">
                                </div>
                                <div class="form-group">
                                    <label>Confirmation Mot de passe *:</label>
                                    <input type="password" name="confirm-password">
                                </div>
                            </div>
                        <%}%>
                    </div>
                     <% if (action.equals("edit")) {%>
                        <input type="hidden" name="editUser" value="<%=editUser.getId()%>">
                        <input type="hidden" value="<%= action %>" name="action">
                    <%}%>
                    <button>Enregistrer</button>
                </form>
            </div>
            <%
                    }
                }
            %>
        </table>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/admin.js"></script>