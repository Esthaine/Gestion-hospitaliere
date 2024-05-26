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
            <li><i class="lni lni-cog"></i><a href="<%= request.getContextPath() %>/hopital/dashboard">Dashboard</a></li>
            <% if (userDto!=null){
                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.ADMIN)){%>
                <li><i class="lni lni-cog"></i><a href="<%= request.getContextPath() %>/hopital/docteurs">Docteurs</a></li>
            <%
                    }
                }
            %>

            <%
                if (userDto!=null){
                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.INFIRMIER)){
            %>
                <li><i class="lni lni-cog"></i><a href="<%= request.getContextPath() %>/hopital/patients">Patients</a></li>
                <li><i class="lni lni-cog"></i><a href="<%= request.getContextPath() %>/hopital/rendez-vous">Rendez-vous Medical</a></li>
            <%}}%>
            <%
                if (userDto!=null){
                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.MEDECIN)){
            %>
                <li><i class="lni lni-cog"></i><a href="<%= request.getContextPath() %>/hopital/medecin/consultation">Rendez-vous Medical</a></li>
            <%}
                }
            %>

            <li><i class="lni lni-cog"></i><a href="<%= request.getContextPath()%>/hopital/profile">Mon Profile</a></li>
            <li><i class="lni lni-cog"></i><a href="<%=request.getContextPath()%>/deconnexion">Deconnexion</a></li>
        </ul>
    </nav>
</div>