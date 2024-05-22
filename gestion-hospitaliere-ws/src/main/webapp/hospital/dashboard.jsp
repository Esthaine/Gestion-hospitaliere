<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.User" %>
<%@ page import="com.gestion.hospitaliere.dao.UserDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.UserDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.utils.AppConst" %>
<jsp:include page="components/topbar.jsp" />
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <%
        UserDao userDao = null;
        try {
            userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<User> patients = userDao.listOfUserPerRole(AppConst.PATIENT);
        List<User> doctors = userDao.listOfUserPerRole(AppConst.MEDECIN);
        List<User> nurses = userDao.listOfUserPerRole(AppConst.INFIRMIER);
        List<User> users = userDao.findAll();
    %>
    <div class="content">
        <div class="rapid-info">
            <div class="info">
                <div class="header">
                    <h3>Patients</h3>
                    <i class="lni lni-cog"></i>
                </div>
                <h2 class="header">
                    <%=patients.size()%>
                </h2>
            </div>

            <div class="info">
                <div class="header">
                    <h3>Docteurs</h3>
                    <i class="lni lni-cog"></i>
                </div>
                <h2>
                    <%= doctors.size()%>
                </h2>
            </div>

            <div class="info">
                <div class="header">
                    <h3>Infirmiere</h3>
                    <i class="lni lni-cog"></i>
                </div>
                <h2>
                    <%=nurses.size()%>
                </h2>
            </div>

<%--            <div class="info">--%>
<%--                <div class="header">--%>
<%--                    <h3>Rendez-vous Medical</h3>--%>
<%--                    <i class="lni lni-cog"></i>--%>
<%--                </div>--%>
<%--                <h2>--%>
<%--                    500--%>
<%--                </h2>--%>
<%--            </div>--%>
            <div class="info">
                <div class="header">
                    <h3>Utilisateurs</h3>
                    <i class="lni lni-cog"></i>
                </div>
                <h2>
                    <%=users.size()%>
                </h2>
            </div>
        </div>
    </div>
</div>