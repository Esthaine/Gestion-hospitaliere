<%@ page import="com.gestion.hospitaliere.model.UserDto" %>
<%@ page import="com.gestion.hospitaliere.dao.PersonDao" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.PersonDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.entity.Person" %>
<jsp:include page="components/topbar.jsp" />
<%
    UserDto user = null;
    PersonDao personDao = null;
    if (session.getAttribute("authenticated") != null){
        user = (UserDto) session.getAttribute("authenticated");
    }
    try{
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
    }catch (Exception e) {
        e.printStackTrace();
    }
%>
<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <div>
            <h2>Prolile urilisateur</h2>
        </div>
        <div>
            <%
                Person person = null;
                if (user != null){
                    if (personDao != null && personDao.findByUserId(user.getUserId()) != null ) {
                        person = personDao.findByUserId(user.getUserId());
                    }
            %>
                <h3>Infomation:</h3>
                <p>Nom complet: </p>
                <p>Adresse: </p>
                <p>Date de naissance: </p>
            <%}%>
        </div>

    </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>