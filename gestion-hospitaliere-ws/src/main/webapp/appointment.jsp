<%@ page import="java.util.List" %>
<%@ page import="com.gestion.hospitaliere.entity.Departement" %>
<%@ page import="com.gestion.hospitaliere.dao.impl.DepartementDaoImpl" %>
<%@ page import="com.gestion.hospitaliere.dao.DepartementDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Departement> departements = null;
    try{
        DepartementDao departementDao =  new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
        departements = departementDao.findAll();
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Rendez-Vous</title>
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <div class="main">
        <div class="main-header">
            <h2>Rendez-vous medical</h2>
        </div>
        <div>
            <form>
                <div class="form-group">
                    <label>Date de rendez-vous * :</label>
                    <input type="date">
                </div>
                <div class="form-group">
                    <label>Selectionner le service :</label>
                    <select>
                        <% if (departements != null) {
                          for (Departement departement:  departements) { %>
                                <option><%=departement.getNomDepartement()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <button type="submit">soumettre</button>
            </form>
        </div>
    </div>
    <jsp:include page="components/footer.jsp"/>
</body>
</html>

