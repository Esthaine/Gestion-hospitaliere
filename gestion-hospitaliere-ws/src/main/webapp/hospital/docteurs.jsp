<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<jsp:include page="components/topbar.jsp" />
<div class="main">
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        String action = (String) request.getParameter("action");
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
                    if (action == null) {

                    if (users !=null && !users.isEmpty()){
                        for (User user : users) {
                %>
                        <tr>
                            <td><%=user.getId()%></td>
                            <td></td>
                            <td></td>
                            <td><%=user.getRole().getName()%></td>
                            <td></td>
                            <td>Departement</td>
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
            <a href="<%=request.getContextPath()%>/hopital/docteurs" class="btn btn-red">Retour</a>
            <div class="member">
                <form>
                    <div>
                        <h3>Info De la personne</h3>
                        <div class="form-group">
                            <label>prenom:</label>
                            <input type="text">
                        </div>
                        <div class="form-group">
                            <label>prenom:</label>
                            <input type="text">
                        </div>
                    </div>
                    <div>
                        <h3>Inf connexion</h3>
                        <h3>Info De la personne</h3>
                        <div class="form-group">
                            <label>prenom:</label>
                            <input type="text">
                        </div>
                        <div class="form-group">
                            <label>prenom:</label>
                            <input type="text">
                        </div>
                    </div>
                    <% if (action.equals("edit")) {%>
                        <input type="hidden" value="<%= action %>">
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