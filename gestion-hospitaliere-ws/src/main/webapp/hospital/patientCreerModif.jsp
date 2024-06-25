<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.Ville" %>
<%@ page import="com.gestion.hospitaliere.entity.Pays" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<jsp:include page="components/topbar.jsp" />
<%
    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Pays> pays = (List<Pays>) request.getAttribute("pays");
    User user = null;
    String action = request.getParameter("action");
    Person person = null;

    String userId = request.getParameter("userId");
    PersonDao personDao = null;
    UserDao userDao = null;
    try{
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
    }catch (Exception e) {
    }

    if (personDao.findByUserId(Long.parseLong(userId)) != null) {
        person = personDao.findByUserId(Long.parseLong(userId));
    }
    if (userDao.findById(Long.parseLong(userId)) != null) {
        user = userDao.findById(Long.parseLong(userId));
    }
%>

<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <form action="<%= request.getContextPath()%>/hopital/patient/creerEtModifier" method="post">
            <h3>Information Patient</h3>
            <div class="form-group">
                <label>Prenom : </label>
                <input type="text" name="givenname" value="<%= person!= null && person.getGivenName()!= null? person.getGivenName():""%>">
            </div>
            <div class="form-group">
                <label>Nom :</label>
                <input type="text" name="name" value="<%=person!= null && person.getFirstName()!= null? person.getFirstName():""%>">
            </div>
            <div class="form-group">
                <label>Poste-Nom : </label>
                <input type="text" name="postnom" value="<%=person!= null && person.getLastName()!= null? person.getLastName():""%>">
            </div>
            <div class="form-group">
                <label>Date de naissance :</label>
                <input type="date" name="dob" value="<%=person!= null && person.getDateOfBirth()!= null? person.getDateOfBirth():""%>">
            </div>
            <div class="form-group">
                <label>Sexe :</label>
                <select name="sex">
                    <option value="MASCULIN">Masculin</option>
                    <option value="FEMININ">Feminin</option>
                </select>
            </div>
            <div class="form-group">
                <label>Phone Number :</label>
                <input type="text" name="telephone" value="<%=person!= null && person.getPhoneNumber()!= null? person.getPhoneNumber():""%>">
            </div>
            <div class="">
                <h3>Adresse: </h3>
                <div class="form-compose-item">
                    <div class="form-group-item">
                        <label>No :</label>
                        <input type="text" name="streetNumber" value="<%=person!= null && person.getHouseNumber()!= null ? person.getHouseNumber():""%>">
                    </div>
                    <div class="form-group-item">
                        <label>Avenue :</label>
                        <input type="text" name="streetName" value="<%=person!= null && person.getStreetName()!= null ? person.getStreetName():""%>">
                    </div>
                    <div class="form-group-item">
                        <label>Commnune :</label>
                        <input type="text" name="townShip" value="<%=person!= null && person.getTownship() != null ? person.getTownship():""%>">
                    </div>
                </div>
                <div class="form-group">
                    <label>Pays:</label>
                    <select name="pays">
                        <%
                            if (pays != null && pays.size() > 0) {
                                for (Pays p : pays) {
                        %>
                        <option value="<%=p.getId()%>"><%=p.getNom()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Ville: </label>
                    <select name="ville">
                        <%
                            if (villes != null && villes.size() > 0) {
                                for (Ville ville : villes) {
                        %>

                        <option value="<%=ville.getId()%>"><%= ville.getNom()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>
            <% if (action != null && action.equals("edit")) {%>
                <div>
                    <h3>Information d'utilisateur</h3>
                    <div class="form-group">
                        <label>Nom d'utilisateur *: <%= user != null && user.getUsername() != null ? user.getUsername() : "" %></label>
                        <input type="text" name="username">
                    </div>
                    <div class="form-group">
                        <label>Email *: <%= user != null && user.getEmail() != null ? user.getEmail() : "" %></label>
                        <input type="text" name="email">
                    </div>
                    <div class="form-group">
                        <label>Mot de passe</label>
                        <input type="password" name="password">
                    </div>
                </div>
            <%}%>
            <input type="hidden" name="userId" value="<%=userId%>" >
            <button class="btn btn-blue">Enregistrement Patient</button>
        </form>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/admin.js"></script>
