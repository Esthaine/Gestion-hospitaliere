<%@ page import="com.gestion.hospitaliere.model.UserDto" %>
<%@ page import="com.gestion.hospitaliere.utils.AppConst" %>
<%
    UserDto userDto = null;
    if (session.getAttribute("authenticated") != null){
        userDto = (UserDto) session.getAttribute("authenticated");
    }
%>
<div class="sidebar">
    <nav>
        <ul>
            <%
                if (userDto != null){
                    if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.ADMIN)){
            %>
                <li><i class="fa fa-tachometer" aria-hidden="true"></i><a href="<%= request.getContextPath() %>/hopital/dashboard">Dashboard</a></li>
            <%}}%>
            <% if (userDto!=null){
                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.ADMIN)){%>
                <li><i class="fa fa-user-md" aria-hidden="true"></i><a href="<%= request.getContextPath() %>/hopital/docteurs">Docteurs</a></li>
            <%
                    }
                }
            %>

            <%
                if (userDto!=null){
                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.INFIRMIER)){
            %>
                <li><i class="fa fa-wheelchair" aria-hidden="true"></i><a href="<%= request.getContextPath() %>/hopital/patients">Patients</a></li>
                <li><i class="fa fa-calendar" aria-hidden="true"></i><a href="<%= request.getContextPath() %>/hopital/rendez-vous">Rendez-vous Medical</a></li>
            <%}}%>
            <%
                if (userDto!=null){
                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.MEDECIN)){
            %>
                <li><i class="fa fa-calendar" aria-hidden="true"></i><a href="<%= request.getContextPath() %>/hopital/medecin/consultation">Rendez-vous Medical</a></li>
            <%}
                }
            %>

            <%
                if (userDto != null){
                    if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.LABORATOIRE)){
            %>
                <li><i class="fa fa-calendar" aria-hidden="true"></i><a href="<%= request.getContextPath()%>/hopital/prelevement/laboratoire">Prelevement</a> </li>
            <%}}%>

            <%
                if (userDto != null){
                    if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.PHARMACIE)){
            %>
                <li><i class="fa fa-calendar" aria-hidden="true"></i><a href="<%= request.getContextPath()%>/hopital/prescription/pharmacie">Prescriptions</a> </li>
            <%}}%>

            <li><i class="fa fa-user" aria-hidden="true"></i><a href="<%= request.getContextPath()%>/hopital/profile">Mon Profile</a></li>
        </ul>
        <span><i class="fa fa-sign-out" aria-hidden="true"></i><a href="<%=request.getContextPath()%>/deconnexion">Deconnexion</a></span>
    </nav>
</div>