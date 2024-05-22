<jsp:include page="components/topbar.jsp" />

<div class="main">
    <jsp:include page="components/sidebar.jsp" />
    <div class="content">
        <h1>Prendre RDV</h1>
        <a href="<%= request.getContextPath() %>/hopital/rendez-vous" class="btn btn-purple">Retour</a>
<%--        <%--%>
<%--            if (action.equals("view")){--%>
<%--                try {--%>
<%--                    if (personDao.findById(personId) != null) {--%>
<%--                        person = personDao.findById(rendezvousId);--%>
<%--                    }--%>
<%--                }catch (Exception e){--%>
<%--                    e.printStackTrace();--%>
<%--                }--%>
<%--        %>--%>

        <form action="<%= request.getContextPath()%>" method="post">
            <div>
                <h3>Information du patient</h3>
                <p>Nom Complet: </p>
                <p>Age:</p>
            </div>
            <div>
                <div class="form-group">
                    <label>Date de rendez-vous</label>
                    <input type="date" name="date_rendezvous">
                </div>
                <div class="form-group">
                    <label>Selectionner un Medecin :</label>
                    <select>

                        <option></option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-blue">Soummettre la demande </button>
        </form>
    </div>
</div>

