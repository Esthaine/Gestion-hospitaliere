<%@ page import="com.gestion.hospitaliere.entity.Medicament" %>
<jsp:include page="components/topbar.jsp" />
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <div>
            <h2>Remise des medicaments au patient</h2>
        </div>
        <div class="patient-info">
            <table>
                <tr>
                    <td>Nom Complet:</td>
                    <td><h4>Jean Bosco Ilunga</h4></td>
                </tr>
                <tr>
                    <td>Date de naissance: </td>
                    <td><h4>26/09/1996</h4></td>
                </tr>
                <tr>
                    <td>Age: </td>
                    <td><h4>28</h4></td>
                </tr>
            </table>
        </div>
        <form>
            <div class="medicaments">
                <div class="medicament-item-container">
<%--                    <%--%>
<%--                        for (Medicament medicament : medicaments) {--%>
<%--                    %>--%>
                    <div class="medicament-item">
                        <input type="checkbox" name="medicaments" value="<%=medicament.getId()%>"><label for="medicaments"><%= medicament.getNom()%></label>
                    </div>
<%--                    <%}%>--%>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
